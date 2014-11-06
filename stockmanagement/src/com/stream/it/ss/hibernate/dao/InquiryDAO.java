package com.stream.it.ss.hibernate.dao;


import java.util.List;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.SearchBean;


public interface InquiryDAO {
	public List listAll()throws Exception;
    public List listAll(SearchBean searchBean)throws Exception;
    public List listByPage(SearchBean searchBean)throws Exception;
    public DataBo find(SearchBean searchBean) throws Exception;
    public Long sum(SearchBean searchBean) throws Exception;    
}
