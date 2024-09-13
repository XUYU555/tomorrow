package com.xxyy.quandaiban1.domain.repository;

import com.xxyy.quandaiban1.domain.model.Member;
import com.xxyy.quandaiban1.infrastructure.common.BaseRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xy
 * @date 2023-11-22 20:18
 */
public interface MemberRepository extends BaseRepository<Member>, QuerydslPredicateExecutor<Member> {
}
