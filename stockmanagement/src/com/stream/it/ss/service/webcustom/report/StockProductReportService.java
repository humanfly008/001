package com.stream.it.ss.service.webcustom.report;

import java.util.List;

import com.stream.it.ss.base.databo.SearchBean;

public interface StockProductReportService {
    @SuppressWarnings("rawtypes")
	public List listTransaction(SearchBean searchBean)throws Exception;
}
