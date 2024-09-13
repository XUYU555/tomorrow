package com.xxyy.quandaiban1.application.service;

import com.xxyy.quandaiban1.application.dtos.GroupInput;
import com.xxyy.quandaiban1.application.dtos.GroupOutput;
import com.xxyy.quandaiban1.application.dtos.MemberOutput;
import com.xxyy.quandaiban1.infrastructure.common.PageOutput;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * @author xy
 * @date 2023-11-22 19:48
 */
public interface GroupService {

    GroupOutput createBy(GroupInput input);

    Boolean withRoleBy(boolean adminRole, Long memberId);

    Boolean deleteMembers(Set<Long> usersId, Long groupId);

    Boolean deleteGroup(Long groupId);

    PageOutput<GroupOutput> pageGroupBy(Pageable pageable);

    PageOutput<MemberOutput> pageMemberBy(Pageable pageable, Long groupId);
}
