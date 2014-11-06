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
import com.stream.it.ss.hibernate.dao.MProductDAO;
import com.stream.it.ss.hibernate.inquiry.ProductInquiry;
import com.stream.it.ss.utils.format.SQLStringType;
import com.stream.it.ss.view.jsf.form.setting.product.ProductSearchForm;



@Service("productReportService")
public class ProductReportServiceImpl implements ProductReportService{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("productTransactionInquiry")
	private InquiryDAO inquiryDAO;
	
	@Autowired
	@Qualifier("MProductDAO")
	private MProductDAO productDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductInquiry> listTransaction(SearchBean searchBean) throws Exception {
		ProductSearchForm productSearchForm = (ProductSearchForm) searchBean;
		List<ProductInquiry> resultDataList = new ArrayList<ProductInquiry>();

		try {
			productSearchForm.setConditionValuesBean(new SearchConditionValuesBean[] {
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"UPPER(I.ITEM_CODE)",			SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(productSearchForm.getProductCode())}),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"UPPER(I.ITEM_NAME_TH)",		SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(productSearchForm.getProductName()) }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"UNIT",							SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(productSearchForm.getUnit()) }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"ITEM_TYPE",					SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(productSearchForm.getProductType()) }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"S.SUPPLIER_ID",				SQLConstantOperType.EQUALS,new Object[] { productSearchForm.getSupplier() })
			});

			resultDataList = inquiryDAO.listByPage(productSearchForm);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			productSearchForm.getResultBO().setException(e);
			productSearchForm.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultDataList;
	}
}
