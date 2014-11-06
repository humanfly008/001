package com.stream.it.ss.service.findservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stream.it.ss.hibernate.dao.MItemDAO;
import com.stream.it.ss.hibernate.domain.MItem;


@Service("itemFindService")
public class ItemFindService {
	@Autowired
	@Qualifier("MItemDAO")
	private MItemDAO mItemDAO;
	
	public MItem findItemById(Integer itemId) throws Exception{
		MItem item = mItemDAO.findById(itemId);
		
		return item;
	}
	
	public Integer findSupplierIdByItem(Integer itemId) throws Exception{
		MItem mItem = mItemDAO.findById(itemId);
		
		return mItem.getSupplierId().getSupplierId();
	}
	
}
