package com.xxyy.quandaiban1.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Task.class)
public abstract class Task_ extends com.xxyy.quandaiban1.infrastructure.common.BaseEntity_ {

	public static volatile SingularAttribute<Task, Boolean> readStatus;
	public static volatile SingularAttribute<Task, Boolean> finished;
	public static volatile SingularAttribute<Task, User> user;
	public static volatile SingularAttribute<Task, Boolean> followed;
	public static volatile SingularAttribute<Task, Notice> notice;

	public static final String READ_STATUS = "readStatus";
	public static final String FINISHED = "finished";
	public static final String USER = "user";
	public static final String FOLLOWED = "followed";
	public static final String NOTICE = "notice";

}

