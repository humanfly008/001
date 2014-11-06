package com.stream.it.ss.service.findservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stream.it.ss.hibernate.dao.MenuDAO;


@Service("functionFindService")
public class FunctionFindService {
	@Autowired
	@Qualifier("menuDAO")
	private MenuDAO menuDAO;
	
	public boolean checkMenuToDelete(String menuId)throws Exception{
		boolean result = menuDAO.findFunction(menuId);
			
		return result;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
	
}
