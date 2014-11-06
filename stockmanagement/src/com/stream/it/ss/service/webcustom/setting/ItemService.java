package com.stream.it.ss.service.webcustom.setting;

import java.util.List;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.base.databo.SearchBean;
import com.stream.it.ss.hibernate.inquiry.ItemInquiry;

public interface ItemService {
	public DataBo findItem(Integer itemId)throws Exception;
    public List<ItemInquiry> listTransaction(SearchBean searchBean)throws Exception;
    public ResultBO createItem(DataBo dataBo)throws Exception;
    public ResultBO updateItem(DataBo dataBo)throws Exception;
    public ResultBO deleteItem(String[] itemId)throws Exception;
}
