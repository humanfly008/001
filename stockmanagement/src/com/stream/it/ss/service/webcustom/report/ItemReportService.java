package com.stream.it.ss.service.webcustom.report;

import java.util.List;

import com.stream.it.ss.base.databo.SearchBean;
import com.stream.it.ss.hibernate.inquiry.ItemInquiry;

public interface ItemReportService {
	public List<ItemInquiry> listTransaction(SearchBean searchBean) throws Exception;
}
