package com.stream.it.ss.service.combo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stream.it.ss.hibernate.dao.InquiryDAO;
import com.stream.it.ss.hibernate.inquiry.Dropdown;

@Service("supplierTypeComboDropdownService")
public class SupplierTypeComboDropdownService {
	
	@Autowired
	@Qualifier("supplierTypeDropdownInquiry")
	private InquiryDAO inquiryDAO;
	
	@SuppressWarnings("unchecked")
	public List<Dropdown> listAll()throws Exception{
		List<Dropdown> result = inquiryDAO.listAll();
				
		return result;
	}
}
