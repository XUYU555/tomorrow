package com.xxyy.quandaiban1.domain.model;

import com.xxyy.quandaiban1.domain.repository.MemberRepository;
import com.xxyy.quandaiban1.infrastructure.common.BaseEntity;
import com.xxyy.quandaiban1.infrastructure.common.Ioc;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author xy
 * @date 2023-11-22 19:37
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {


    public Member(User user, Group group) {
        this.user = user;
        this.group = group;
    }

    /**
     * 所属群组
     */
    @ManyToOne
    private User user;

    /**
     * 成员
     */
    @ManyToOne
    private Group group;

    /**
     * 管理员
     */
    @Column
    private boolean isAdmin=false;

    public void withRole(boolean role) {
        this.isAdmin = role;
        Ioc.getBean(MemberRepository.class).save(this);
    }


}
