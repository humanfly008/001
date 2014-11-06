package com.stream.it.ss.service.combo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stream.it.ss.hibernate.dao.InquiryDAO;
import com.stream.it.ss.hibernate.inquiry.Dropdown;

@Service("functionComboDropdownService")
public class FunctionComboDropdownService {
	
	@Autowired
	@Qualifier("functionMenuDropdownInquiry")
	private InquiryDAO inquiryDAO;
	
	@SuppressWarnings("unchecked")
	public List<Dropdown> listAll()throws Exception{
		List<Dropdown> result = inquiryDAO.listAll();
				
		return result;
	}
	
	//**** setter *****//
	public void setInquiryDAO(InquiryDAO inquiryDAO) {
		this.inquiryDAO = inquiryDAO;
	}
}
