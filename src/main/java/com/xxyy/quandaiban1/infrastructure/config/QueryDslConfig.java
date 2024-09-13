package com.xxyy.quandaiban1.infrastructure.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * @author xy
 * @date 2023-11-23 13:41
 */
@Configuration
public class QueryDslConfig{

    @Bean
    public JPAQueryFactory jpaRepositoryFactory(EntityManager manager) {
        return new JPAQueryFactory(manager);
    }

}
