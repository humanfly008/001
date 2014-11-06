package com.stream.it.ss.service.webcustom.setting;

import java.util.List;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.base.databo.SearchBean;
import com.stream.it.ss.hibernate.inquiry.ProductInquiry;

public interface ProductService {
	public DataBo findProduct(Integer productId)throws Exception;
    public List<ProductInquiry> listTransaction(SearchBean searchBean)throws Exception;
    public ResultBO createProduct(DataBo dataBo)throws Exception;
    public ResultBO updateProduct(DataBo dataBo)throws Exception;
    public ResultBO deleteProduct(String[] productId)throws Exception;
}
