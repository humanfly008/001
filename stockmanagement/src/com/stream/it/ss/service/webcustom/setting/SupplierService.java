package com.stream.it.ss.service.webcustom.setting;

import java.util.List;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.base.databo.SearchBean;

public interface SupplierService {
	public DataBo findSupplier(Integer supplierId)throws Exception;
    public List listTransaction(SearchBean searchBean)throws Exception;
    public ResultBO createSupplier(DataBo dataBo)throws Exception;
    public ResultBO updateSupplier(DataBo dataBo)throws Exception;
    public ResultBO deleteSupplier(String[] holiDayId)throws Exception;
}
