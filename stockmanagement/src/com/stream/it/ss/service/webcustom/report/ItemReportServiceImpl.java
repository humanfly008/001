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
import com.stream.it.ss.hibernate.inquiry.ItemInquiry;
import com.stream.it.ss.utils.format.SQLStringType;
import com.stream.it.ss.view.jsf.form.setting.item.ItemSearchForm;



@Service("itemReportService")
public class ItemReportServiceImpl implements ItemReportService{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("itemTransactionInquiry")
	private InquiryDAO inquiryDAO;

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemInquiry> listTransaction(SearchBean searchBean) throws Exception {
		ItemSearchForm itemSearchForm = (ItemSearchForm) searchBean;
		List<ItemInquiry> resultDataList = new ArrayList<ItemInquiry>();

		try {
			itemSearchForm.setConditionValuesBean(new SearchConditionValuesBean[] {
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"UPPER(I.ITEM_CODE)",			SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(itemSearchForm.getItemCode())}),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"UPPER(I.ITEM_NAME_TH)",		SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(itemSearchForm.getItemName()) }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"UNIT",							SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(itemSearchForm.getUnit()) }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"ITEM_TYPE",					SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(itemSearchForm.getItemType()) }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"S.SUPPLIER_ID",				SQLConstantOperType.EQUALS,new Object[] { itemSearchForm.getSupplier() })
			});

			resultDataList = inquiryDAO.listAll(itemSearchForm);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			itemSearchForm.getResultBO().setException(e);
			itemSearchForm.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultDataList;
	}
}
