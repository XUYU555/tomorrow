package com.xxyy.quandaiban1.domain.specs;

import com.xxyy.quandaiban1.domain.model.User;
import com.xxyy.quandaiban1.domain.model.User_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @author xy
 * @date 2023-09-25 15:34
 */
public class UserSpecs {

    public static Specification<User> username(String value) {
        return (root, query, criteriaBuilder) -> {
            Path<String> stringPath = root.get(User_.USERNAME);
            Predicate equal = criteriaBuilder.equal(stringPath, value);
            return equal;
        };
    }

    public static Specification<User> userId(List<Long> ids) {
        return (root, query, criteriaBuilder) -> {
            Predicate inId = root.get(User_.ID).in(ids);
            return inId;
        };
    }

}
