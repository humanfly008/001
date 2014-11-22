package com.stream.it.ss.service.webcustom.transaction.salary;

import java.util.List;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.base.databo.SearchBean;

public interface FinanceTransactionService {
	public List listTrasnaction(SearchBean searchBean)throws Exception;
	public ResultBO createTrasnaction(List transactionList, int month, int year)throws Exception;
	public ResultBO updateTrasnaction(List transactionList, int month, int year)throws Exception;
}
