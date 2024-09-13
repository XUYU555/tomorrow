package com.xxyy.quandaiban1.domain.repository;

import com.xxyy.quandaiban1.domain.model.Task;
import com.xxyy.quandaiban1.infrastructure.common.BaseRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


/**
 * @author xy
 * @date 2023-09-25 16:01
 */
public interface TaskRepository extends BaseRepository<Task>, QuerydslPredicateExecutor<Task> {

}
