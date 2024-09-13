package com.xxyy.quandaiban1.domain.model;

import cn.hutool.core.util.RandomUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xxyy.quandaiban1.infrastructure.common.BaseEntity;
import com.xxyy.quandaiban1.infrastructure.common.Ioc;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xy
 * @date 2023-11-22 19:31
 */
@Entity(name = "tb_group")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group extends BaseEntity {

    public Group(String name, User user) {
        this.name = name;
        this.invited = RandomUtil.randomNumbers(6);
        this.creator = user;
    }

    /**
     * 群组名
     */
    @Column( unique = true )
    private String name;

    /**
     * 邀请码
     */
    @Column
    private String invited;

    /**
     * 群主
     */
    @OneToOne
    private User creator;

    public List<Member> addMember(List<User> users) {
        return users.stream().map(user -> new Member(user, this)).collect(Collectors.toList());
    }

    public Member addMember(User user) {
        return new Member(user, this);
    }

    public void removeMembers(Set<Long> usersId) {
        Ioc.getBean(JPAQueryFactory.class)
                .delete(QMember.member)
                .where(QMember.member.user.id.in(usersId))
                .where(QMember.member.group.eq(this))
                .execute();
    }

    public Group removeAllMembers() {
        Ioc.getBean(JPAQueryFactory.class)
                .delete(QMember.member)
                .where(QMember.member.group.eq(this))
                .execute();
        return this;
    }

}
