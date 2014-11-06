package com.stream.it.ss.hibernate.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BaseDAO extends HibernateDaoSupport{
    @Autowired
    public void MyHibernateDaoSupport(@Qualifier("sessionFactory")SessionFactory factory)
    {
        setSessionFactory(factory);
    }
    
}
