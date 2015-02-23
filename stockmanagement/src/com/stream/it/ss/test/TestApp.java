package com.stream.it.ss.test;

import com.stream.it.ss.base.databo.SearchBean;
import com.stream.it.ss.hibernate.dao.UsersProfileDAO;
import com.stream.it.ss.hibernate.domain.UsersProfile;
import com.stream.it.ss.service.webcustom.setting.UserProfileService;
import com.stream.it.ss.service.webcustom.setting.UserProfileServiceImpl;
import com.stream.it.ss.view.jsf.form.setting.users.UserSearchForm;

public class TestApp {
	
	public static void main(String[]s) throws Exception{
		UsersProfileDAO usersProfileDAO = (UsersProfileDAO)ApplicationContext.getInstance().getBeanFactory().getBean("usersProfileDAO");
		System.out.println(usersProfileDAO.listAll().size());
	
//		for(int i=0; i<1000; i++){
//			System.out.println("I : "+i);
//			
//			usersProfileDAO.create(new UsersProfile());
//		}
	}	
}
