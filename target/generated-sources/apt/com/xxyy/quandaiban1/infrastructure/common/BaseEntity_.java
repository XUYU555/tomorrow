package com.xxyy.quandaiban1.infrastructure.common;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BaseEntity.class)
public abstract class BaseEntity_ {

	public static volatile SingularAttribute<BaseEntity, String> createBy;
	public static volatile SingularAttribute<BaseEntity, Boolean> deleted;
	public static volatile SingularAttribute<BaseEntity, String> updateBy;
	public static volatile SingularAttribute<BaseEntity, LocalDateTime> updateAt;
	public static volatile SingularAttribute<BaseEntity, Long> id;
	public static volatile SingularAttribute<BaseEntity, LocalDateTime> createAt;

	public static final String CREATE_BY = "createBy";
	public static final String DELETED = "deleted";
	public static final String UPDATE_BY = "updateBy";
	public static final String UPDATE_AT = "updateAt";
	public static final String ID = "id";
	public static final String CREATE_AT = "createAt";

}

