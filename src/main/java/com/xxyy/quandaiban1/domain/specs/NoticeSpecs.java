package com.xxyy.quandaiban1.domain.specs;

import com.querydsl.core.BooleanBuilder;
import com.xxyy.quandaiban1.domain.model.QTask;
import com.xxyy.quandaiban1.domain.model.User;

/**
 * @author xy
 * @date 2023-11-21 22:27
 */
public class NoticeSpecs {

    public static BooleanBuilder newNotice(User user) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QTask.task.user.eq(user));
        builder.and(QTask.task.readStatus.eq(false));
        builder.and(QTask.task.followed.eq(false));
        return builder;
    }
}
