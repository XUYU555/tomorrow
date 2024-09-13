package com.xxyy.quandaiban1.domain.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Notice.class)
public abstract class Notice_ extends com.xxyy.quandaiban1.infrastructure.common.BaseEntity_ {

	public static volatile SingularAttribute<Notice, User> creator;
	public static volatile SingularAttribute<Notice, String> source;
	public static volatile SingularAttribute<Notice, Boolean> completed;
	public static volatile SingularAttribute<Notice, LocalDateTime> deadline;
	public static volatile SingularAttribute<Notice, String> content;

	public static final String CREATOR = "creator";
	public static final String SOURCE = "source";
	public static final String COMPLETED = "completed";
	public static final String DEADLINE = "deadline";
	public static final String CONTENT = "content";

}

