package com.xxyy.quandaiban1.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Member.class)
public abstract class Member_ extends com.xxyy.quandaiban1.infrastructure.common.BaseEntity_ {

	public static volatile SingularAttribute<Member, Boolean> isAdmin;
	public static volatile SingularAttribute<Member, User> user;
	public static volatile SingularAttribute<Member, Group> group;

	public static final String IS_ADMIN = "isAdmin";
	public static final String USER = "user";
	public static final String GROUP = "group";

}

