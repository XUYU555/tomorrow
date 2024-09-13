package com.xxyy.quandaiban1.domain.specs;

import com.querydsl.core.BooleanBuilder;
import com.xxyy.quandaiban1.domain.model.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

/**
 * @author xy
 * @date 2023-09-25 20:23
 */
public class TaskSPecs {

    public static BooleanBuilder findTask(User user) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QTask.task.user.eq(user));
        builder.and(QTask.task.readStatus.eq(true));
        builder.and(QTask.task.followed.eq(true));
        return builder;
    }


    public static Specification<Task> already(User user,Notice notice) {
        return (root, query, criteriaBuilder) -> {
            Predicate userEqual = criteriaBuilder.equal(root.get(Task_.USER), user);
            Predicate noticeEqual = criteriaBuilder.equal(root.get(Task_.NOTICE), notice);
            return criteriaBuilder.and(userEqual,noticeEqual);
        };
    }

    public static Specification<Task> peopleList(Notice notice, boolean followed, boolean finished) {
        return (root, query, criteriaBuilder) -> {
            Predicate noticeEqual = criteriaBuilder.equal(root.get(Task_.NOTICE), notice);
            Predicate followEqual = criteriaBuilder.equal(root.get(Task_.FOLLOWED), followed);
            Predicate finishedEqual = criteriaBuilder.equal(root.get(Task_.FINISHED), finished);
            Predicate readEqual = criteriaBuilder.equal(root.get(Task_.READ_STATUS), true);
            if (followed && finished) {
                return criteriaBuilder.and(noticeEqual, followEqual, finishedEqual, readEqual);
            } else if (followed) {
                return criteriaBuilder.and(noticeEqual, followEqual, readEqual);
            } else if (finished) {
                return criteriaBuilder.and(noticeEqual, finishedEqual, readEqual);
            } else {
                return criteriaBuilder.and(noticeEqual, readEqual);
            }
        };
    }

}
