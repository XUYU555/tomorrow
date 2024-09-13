package com.xxyy.quandaiban1.domain.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Group.class)
public abstract class Group_ extends com.xxyy.quandaiban1.infrastructure.common.BaseEntity_ {

	public static volatile SingularAttribute<Group, User> creator;
	public static volatile SingularAttribute<Group, String> name;
	public static volatile SingularAttribute<Group, String> invited;

	public static final String CREATOR = "creator";
	public static final String NAME = "name";
	public static final String INVITED = "invited";

}

