package com.stream.it.ss.service.findservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stream.it.ss.hibernate.dao.MenuDAO;


@Service("menuFindService")
public class MenuFindService {
	@Autowired
	@Qualifier("menuDAO")
	private MenuDAO menuDAO;
	
	public boolean checkMenuName(String menuname)throws Exception{
		boolean result = false;
		//if menu name valid
		if(menuDAO.findMenuName(menuname))
			result = true;
			
		return result;
	}
	
	
	public List<?> transformFunctionId() throws Exception{
		List<?> resultList = menuDAO.listMenuSource();
		String sourceId = "";
		String [] splitfunc = null;
		List<String> funcList = new ArrayList();
		
		for(int i=0;i<resultList.size();i++){
			if(resultList.get(i) != null && !resultList.get(i).equals("")){
				splitfunc  = ((String) resultList.get(i)).split(",");
				for(int j=0;j<splitfunc.length;j++){
					sourceId+= splitfunc[j].trim().toString();
					funcList.add(splitfunc[j].trim().toString());
				}
			}
		}
		
		return funcList;
	}
	
	public List<?> transformEditFunctionList(String menuSourceId) throws Exception{
		String sourceId = "";
		String [] splitfunc = null;
		List<String> funcList = new ArrayList();
		
		if(menuSourceId != null && !menuSourceId.equals("")){
			splitfunc  = menuSourceId.split(",");
			for(int j=0;j<splitfunc.length;j++){
				sourceId+= splitfunc[j].trim().toString();
				funcList.add(splitfunc[j].trim().toString());
			}
		}
		
		return funcList;
	}

}
