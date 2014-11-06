package com.stream.it.ss.service.webcustom.report;

import java.util.ArrayList;
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
import com.stream.it.ss.hibernate.inquiry.SupplierInquiry;
import com.stream.it.ss.utils.format.SQLStringType;
import com.stream.it.ss.view.jsf.form.transaction.stock.*;




@Service("stockItemReportService")
public class StockItemReportServiceImpl implements StockItemReportService{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("stockItemTransactionsInquiry")
	private InquiryDAO inquiryDAO;

	
	@SuppressWarnings("unchecked")
	@Override
	public List<SupplierInquiry> listTransaction(SearchBean searchBean) throws Exception {
		StockItemSearchForm stockSearchForm = (StockItemSearchForm) searchBean;
		List<SupplierInquiry> resultDataList = new ArrayList<SupplierInquiry>();

		try {
			stockSearchForm.setConditionValuesBean(new SearchConditionValuesBean[] {
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"S.STOCK_CODE",		SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(stockSearchForm.getStockCode()) }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"S.STOCK_TYPE",		SQLConstantOperType.EQUALS,new Object[] { stockSearchForm.getStockType() }), 
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"I.SUPPLIER_ID",	SQLConstantOperType.EQUALS,new Object[] { stockSearchForm.getSupplierId() }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"I.ITEM_ID",		SQLConstantOperType.EQUALS,new Object[] { stockSearchForm.getItemId() })
			});
			stockSearchForm.setOrderBy("S.STOCK_ID");
			stockSearchForm.setOrderType("DESC");
			
			resultDataList = inquiryDAO.listAll(stockSearchForm);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			stockSearchForm.getResultBO().setException(e);
			stockSearchForm.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultDataList;
	}	
}
