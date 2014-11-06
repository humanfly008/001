package com.stream.it.ss.service.webcustom.report;

import java.util.List;

import com.stream.it.ss.base.databo.SearchBean;
import com.stream.it.ss.hibernate.inquiry.ProductInquiry;

public interface ProductReportService {
    public List<ProductInquiry> listTransaction(SearchBean searchBean)throws Exception;
}
