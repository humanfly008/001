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


@Service("itemComboDropdownService")
public class ItemComboDropdownService {
	
	@Autowired
	@Qualifier("itemDropdownInquiry")
	private InquiryDAO inquiryDAO;
	
	
	@SuppressWarnings("unchecked")
	public List<Dropdown> listAll()throws Exception{
		List<Dropdown> result = inquiryDAO.listAll();
				
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Dropdown> listBySupplierId(Integer supplierId)throws Exception{
		SearchBean searchBean = new SearchBean();
		searchBean.setConditionValuesBean(new SearchConditionValuesBean[] {
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"SUPPLIER_ID",	SQLConstantOperType.EQUALS,	new Object[] { supplierId }),
			});
		
		List<Dropdown> result = inquiryDAO.listAll(searchBean);
				
		return result;
	}
}
