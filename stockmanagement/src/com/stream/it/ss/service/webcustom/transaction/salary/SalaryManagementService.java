package com.stream.it.ss.service.webcustom.transaction.salary;

import java.util.List;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.base.databo.SearchBean;

public interface SalaryManagementService {
	public List listTrasnaction(SearchBean searchBean)throws Exception;
	public List listTrasnactionByUser(SearchBean searchBean)throws Exception;
	public List listOtByUserTrasnaction(SearchBean searchBean) throws Exception;
	public List listSalaryDailyByUserTrasnaction(SearchBean searchBean)throws Exception;	
	public ResultBO createOt(DataBo searchBean)throws Exception;
	public ResultBO deleteOtTrasnactionByUser(String[] transactionId) throws Exception;
	
	public ResultBO createDaily(DataBo searchBean)throws Exception;
	public ResultBO deleteDailyTrasnactionByUser(String[] transactionId) throws Exception;	
	
	public ResultBO updateIncome(DataBo searchBean)throws Exception;
	public ResultBO deleteIncome(DataBo searchBean)throws Exception;	
}
