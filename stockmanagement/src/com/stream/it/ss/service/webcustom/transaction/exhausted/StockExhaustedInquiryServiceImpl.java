package com.stream.it.ss.service.webcustom.transaction.exhausted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stream.it.ss.base.constant.SQLConstantOperType;
import com.stream.it.ss.base.constant.SQLConstantWhereType;
import com.stream.it.ss.base.databo.SearchBean;
import com.stream.it.ss.base.databo.SearchConditionValuesBean;
import com.stream.it.ss.hibernate.dao.InquiryDAO;
import com.stream.it.ss.hibernate.domain.MItem;
import com.stream.it.ss.hibernate.inquiry.StockExhaustedInquiry;
import com.stream.it.ss.view.jsf.form.transaction.exhausted.StockExhaustedSearchForm;



@Service("stockExhaustedInquiryService")
public class StockExhaustedInquiryServiceImpl implements StockExhaustedInquiryService{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("stockExhaustedTransactionsInquiry")
	private InquiryDAO inquiryDAO; 
	
	@Autowired
	@Qualifier("stockExhaustedTransactionsSummaryInquiry")
	private InquiryDAO summaryInquiryDAO; 

	@Autowired
	@Qualifier("productItemGroupInquiry")
	private InquiryDAO productItemGroupInquiryDAO;

	
	@SuppressWarnings("unchecked")
	@Override
	public List<StockExhaustedInquiry> listTransaction(SearchBean searchBean) throws Exception {
		StockExhaustedSearchForm searchFormBO = (StockExhaustedSearchForm) searchBean;
		List<StockExhaustedInquiry> resultDataList = new ArrayList<StockExhaustedInquiry>();

		try {
			searchFormBO.setConditionValuesBean(new SearchConditionValuesBean[] {
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"TRANSACTION_TYPE",		SQLConstantOperType.EQUALS,	new Object[] { searchFormBO.getTransactionType() }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"OBJECT_ID",			SQLConstantOperType.EQUALS,	new Object[] { searchFormBO.getProductId()}),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"OBJECT_ID",			SQLConstantOperType.EQUALS,	new Object[] { searchFormBO.getItemId()}),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"SUPPLIER_ID",			SQLConstantOperType.EQUALS,	new Object[] { searchFormBO.getSupplier() })
			});
			searchFormBO.setOrderBy("STATUS, SUPPLIER, TRANSACTION_TYPE, QTY");
			searchFormBO.setOrderType("DESC");
			
			resultDataList = inquiryDAO.listByPage(searchFormBO);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			searchFormBO.getResultBO().setException(e);
			searchFormBO.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultDataList;
	}

	@SuppressWarnings("unchecked")
	public List<MItem> findItemProductGroup(String productId)throws Exception{
		SearchBean searchBean = new SearchBean();
		searchBean.setSqlParameter(Arrays.asList(productId));
		List<MItem> productItemGroupList = productItemGroupInquiryDAO.listAll(searchBean);
		
		return productItemGroupList;
	}
	
	@Override
	public Integer findSummaryTransaction(SearchBean searchBean) throws Exception {
		StockExhaustedSearchForm searchFormBO = (StockExhaustedSearchForm) searchBean;
		Integer resultSummary = new Integer(0);

		try {
			resultSummary = inquiryDAO.sum(searchFormBO).intValue();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			searchFormBO.getResultBO().setException(e);
			searchFormBO.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultSummary;
	}
	
}
