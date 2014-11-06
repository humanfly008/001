package com.stream.it.ss.service.combo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stream.it.ss.hibernate.inquiry.Dropdown;
import com.stream.it.ss.utils.format.DateUtil;

@Service("yearComboDropdownService")
public class YearComboDropdownService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List listCurrent10Year()throws Exception{
		List resultList  = new ArrayList();
		Dropdown dropdown = null;
		int currentYear = Integer.parseInt(DateUtil.getCurrentYear());
		int index = 0;
		while(index<10){
			dropdown = new Dropdown();
			dropdown.setLabel(Integer.toString(currentYear));
			dropdown.setValue(Integer.toString(currentYear--));

			resultList.add(dropdown);
			index++;
		}
		
		return resultList;
	}
	
	

}
