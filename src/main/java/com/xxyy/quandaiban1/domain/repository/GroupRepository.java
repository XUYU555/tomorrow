package com.xxyy.quandaiban1.domain.repository;

import com.xxyy.quandaiban1.domain.model.Group;
import com.xxyy.quandaiban1.infrastructure.common.BaseRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xy
 * @date 2023-11-22 19:59
 */
public interface GroupRepository extends BaseRepository<Group>, QuerydslPredicateExecutor<Group> {



}
