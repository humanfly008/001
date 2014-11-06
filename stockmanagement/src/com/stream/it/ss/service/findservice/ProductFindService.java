package com.stream.it.ss.service.findservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stream.it.ss.hibernate.dao.MProductDAO;
import com.stream.it.ss.hibernate.domain.MProduct;


@Service("productFindService")
public class ProductFindService {
	@Autowired
	@Qualifier("MProductDAO")
	private MProductDAO mProductDAO;
	
	public Integer findSupplierIdByProduct(Integer productId) throws Exception{
		MProduct mProduct = mProductDAO.findById(productId);
		
		return mProduct.getSupplierId().getSupplierId();
	}
	
}
