package com.stream.it.ss.service.webcustom.transaction.exhausted;

import java.util.List;

import com.stream.it.ss.base.databo.SearchBean;

public interface StockExhaustedInquiryService {
	public List<?> listTransaction(SearchBean searchBean)throws Exception;
	public Integer findSummaryTransaction(SearchBean searchBean)throws Exception;
	public List<?> findItemProductGroup(String productId)throws Exception;
}
