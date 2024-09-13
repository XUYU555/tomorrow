package com.xxyy.quandaiban1.domain.repository;

import com.xxyy.quandaiban1.domain.model.User;
import com.xxyy.quandaiban1.infrastructure.common.BaseRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


/**
 * @author xy
 * @date 2023-09-25 15:11
 */
public interface UserRepository extends BaseRepository<User>, QuerydslPredicateExecutor<User> {

}
