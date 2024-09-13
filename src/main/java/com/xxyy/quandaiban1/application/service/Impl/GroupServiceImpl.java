package com.xxyy.quandaiban1.application.service.Impl;

import com.xxyy.quandaiban1.application.dtos.GroupInput;
import com.xxyy.quandaiban1.application.dtos.GroupOutput;
import com.xxyy.quandaiban1.application.dtos.MemberOutput;
import com.xxyy.quandaiban1.application.service.GroupService;
import com.xxyy.quandaiban1.domain.model.*;
import com.xxyy.quandaiban1.domain.repository.GroupRepository;
import com.xxyy.quandaiban1.domain.repository.MemberRepository;
import com.xxyy.quandaiban1.domain.repository.UserRepository;
import com.xxyy.quandaiban1.domain.specs.MemberSpec;
import com.xxyy.quandaiban1.infrastructure.AppException;
import com.xxyy.quandaiban1.infrastructure.SecurityUtil;
import com.xxyy.quandaiban1.infrastructure.common.PageOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author xy
 * @date 2023-11-22 19:49
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository, MemberRepository memberRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
    }

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public GroupOutput createBy(GroupInput input) {
        if(groupRepository.exists(QGroup.group.name.eq(input.getGroupName()))) {
            throw new AppException("群组已存在");
        }
        Group group = groupRepository.save(new Group(input.getGroupName(), SecurityUtil.getUser()));
        List<User> users = new ArrayList<>();
        userRepository.findAll(QUser.user.id.in(input.getUserId())).forEach(users::add);
        memberRepository.saveAll(group.addMember(users));
        Member member = memberRepository.findOne(QMember.member.group.eq(group).and(QMember.member.user.eq(group.getCreator())))
                .orElseThrow(() -> new AppException("成员不存在"));
        member.withRole(true);
        return GroupOutput.of(group, null);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean withRoleBy(boolean adminRole, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new AppException("成员不存在"));
        User user = SecurityUtil.getUser();
        isAdmin(user, member.getGroup());
        member.withRole(adminRole);
        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteMembers(Set<Long> usersId, Long groupId) {
        Group group = SecurityUtil.getGroup(groupId);
        User user = SecurityUtil.getUser();
        isAdmin(user, group);
        usersId.remove(group.getCreator().getId());   // 如果有群主id则移除
        group.removeMembers(usersId);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteGroup(Long groupId) {
        Group group = SecurityUtil.getGroup(groupId);
        User user = SecurityUtil.getUser();
        if( !group.getCreator().equals(user) ) {
            throw new AppException("指定群组不存在,或你无权管理该群组!");
        }
        groupRepository.delete(group.removeAllMembers());
        return true;
    }

    @Override
    public PageOutput<GroupOutput> pageGroupBy(Pageable pageable) {
        Page<GroupOutput> page = groupRepository.findAll(pageable).map(group -> {
            PageOutput<MemberOutput> members = pageMemberBy(pageable, group.getId());
            return GroupOutput.of(group, members);
        });
        return PageOutput.of(page);
    }

    @Override
    public PageOutput<MemberOutput> pageMemberBy(Pageable pageable, Long groupId) {
        Group group = SecurityUtil.getGroup(groupId);
        Page<MemberOutput> page = memberRepository.findAll(QMember.member.group.eq(group), pageable).map(MemberOutput::of);
        return PageOutput.of(page);
    }


    public void isAdmin(User user, Group group) {
        if( group.getCreator().equals(user) ) {
            return;
        }
        if( !memberRepository.exists(MemberSpec.havingPower(user, group)) ) {
            throw new AppException("指定群组不存在,或你无权管理该群组!");
        }
    }

}
