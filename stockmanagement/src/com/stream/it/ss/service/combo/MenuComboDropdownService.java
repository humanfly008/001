package com.stream.it.ss.service.combo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stream.it.ss.base.constant.SQLConstantOperType;
import com.stream.it.ss.base.constant.SQLConstantWhereType;
import com.stream.it.ss.base.databo.SearchBean;
import com.stream.it.ss.base.databo.SearchConditionValuesBean;
import com.stream.it.ss.hibernate.dao.InquiryDAO;
import com.stream.it.ss.hibernate.inquiry.Dropdown;

@Service("menuComboDropdownService")
public class MenuComboDropdownService {
	
	@Autowired
	@Qualifier("menuDropdownInquiry")
	private InquiryDAO inquiryDAO;
	
	@SuppressWarnings("unchecked")
	public List<Dropdown> listAll()throws Exception{
		List<Dropdown> result = inquiryDAO.listAll();
				
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Dropdown> listInMenuId(String menuList) throws Exception{
		SearchBean searchBean = new SearchBean();
		searchBean.setConditionValuesBean(new SearchConditionValuesBean[] {
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"MENU_ID",	SQLConstantOperType.EQUALS,	menuList.split(",")),
			});

		List<Dropdown> result = inquiryDAO.listAll(searchBean);
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Dropdown> listNotInMenuId(String menuList) throws Exception{
		SearchBean searchBean = new SearchBean();
		searchBean.setConditionValuesBean(new SearchConditionValuesBean[] {
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"MENU_ID",	SQLConstantOperType.NOT_EQUALS,	menuList.split(",")),
			});

		List<Dropdown> result = inquiryDAO.listAll(searchBean);
		
		return result;
	}
	
}
