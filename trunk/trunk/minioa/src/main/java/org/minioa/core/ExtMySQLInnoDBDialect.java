package org.minioa.core;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLInnoDBDialect;

public class ExtMySQLInnoDBDialect extends MySQLInnoDBDialect {
	public ExtMySQLInnoDBDialect()
	{
		super();
		registerHibernateType(-1,Hibernate.TEXT.getName());
	}
}
