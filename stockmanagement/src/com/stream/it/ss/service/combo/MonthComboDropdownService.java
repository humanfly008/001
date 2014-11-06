package com.stream.it.ss.service.combo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stream.it.ss.hibernate.inquiry.Dropdown;
import com.stream.it.ss.utils.format.DateUtil;


@Service("monthComboDropdownService")
public class MonthComboDropdownService {
	
	public List<Dropdown> listAllMonth(){
		List<Dropdown> resultList = new ArrayList<Dropdown>();
		Dropdown dropdown = null;
		for(int i=1;i<=12;i++){
			dropdown = new Dropdown();
			dropdown.setValue(Integer.toString(i));
			dropdown.setLabel(DateUtil.getMonthFullNameByMounthNo(i));
			resultList.add(dropdown);
		}
		
		return resultList;
	}
	
	public List<Dropdown> listAllMonthTh(){
		List<Dropdown> resultList = new ArrayList<Dropdown>();
		Dropdown dropdown = null;
		for(int i=1;i<=12;i++){
			dropdown = new Dropdown();
			dropdown.setValue(Integer.toString(i));
			dropdown.setLabel(DateUtil.getMonthFullNameThaiByMounthNo(i));
			resultList.add(dropdown);
		}
		
		return resultList;
	}
	
}
