package com.stream.it.ss.test;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ApplicationContext {
	private static ApplicationContext instance = null;
	private static ClassPathXmlApplicationContext applicationContext = null;
	private static BeanFactory beanFactory = null;
		
	static{
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
				"applicationContext.xml",
				"sessionFactory.xml",
				"inquery-dropdown.xml",
				"inquery-master.xml",
				"inquery-transactions.xml",
				"logging.xml",
				"transaction.xml"
				
		});
        beanFactory = (BeanFactory) applicationContext;        
	}
	
	public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }        
        return instance;
    }

	public static ClassPathXmlApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static BeanFactory getBeanFactory() {
		return beanFactory;
	}    

}
