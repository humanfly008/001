package com.stream.it.ss.service.webcustom.transaction.stock;

import java.util.List;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.base.databo.SearchBean;

public interface StockItemService {
	public DataBo findStockDetail(Integer supplierId)throws Exception;
    public List listTransaction(SearchBean searchBean)throws Exception;
    public ResultBO createStockTransaction(DataBo dataBo)throws Exception;
}
