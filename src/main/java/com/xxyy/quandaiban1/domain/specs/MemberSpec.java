package com.xxyy.quandaiban1.domain.specs;

import com.querydsl.core.BooleanBuilder;
import com.xxyy.quandaiban1.domain.model.Group;
import com.xxyy.quandaiban1.domain.model.QMember;
import com.xxyy.quandaiban1.domain.model.User;

/**
 * @author xy
 * @date 2023-11-22 21:23
 */
public class MemberSpec {

    public static BooleanBuilder havingPower(User user, Group group) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QMember.member.user.eq(user));
        builder.and(QMember.member.group.eq(group));
        builder.and(QMember.member.isAdmin.eq(true));
        return builder;
    }

}
