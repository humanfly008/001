package com.stream.it.ss.service.webcustom.transaction.stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stream.it.ss.base.constant.SQLConstantOperType;
import com.stream.it.ss.base.constant.SQLConstantWhereType;
import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.base.databo.SearchBean;
import com.stream.it.ss.base.databo.SearchConditionValuesBean;
import com.stream.it.ss.hibernate.dao.InquiryDAO;
import com.stream.it.ss.hibernate.dao.MItemDAO;
import com.stream.it.ss.hibernate.dao.TStockItemDAO;
import com.stream.it.ss.hibernate.domain.MItem;
import com.stream.it.ss.hibernate.domain.TStockItem;
import com.stream.it.ss.hibernate.inquiry.StockItemInquiry;
import com.stream.it.ss.hibernate.inquiry.SupplierInquiry;
import com.stream.it.ss.utils.format.SQLStringType;
import com.stream.it.ss.view.jsf.form.transaction.stock.*;




@Service("stockItemService")
public class StockItemServiceImpl implements StockItemService{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("stockItemTransactionsInquiry")
	private InquiryDAO inquiryDAO;
	
	@Autowired
	@Qualifier("TStockItemDAO")
	private TStockItemDAO stockDAO;
	
	@Autowired
	@Qualifier("MItemDAO")
	private MItemDAO itemDAO;
		
	
	@Override
	public DataBo findStockDetail(Integer stockId) throws Exception {
		StockItemForm StockItemForm = new StockItemForm();
		
		try {
//			TStockItem stock = stockDAO.findById(stockId);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			StockItemForm.getResultBO().setException(e);
			StockItemForm.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return StockItemForm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockItemInquiry> listTransaction(SearchBean searchBean) throws Exception {
		StockItemSearchForm stockSearchForm = (StockItemSearchForm) searchBean;
		List<StockItemInquiry> resultDataList = new ArrayList<StockItemInquiry>();

		try {
			stockSearchForm.setConditionValuesBean(new SearchConditionValuesBean[] {
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"S.STOCK_CODE",		SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(stockSearchForm.getStockCode()) }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"S.STOCK_TYPE",		SQLConstantOperType.EQUALS,new Object[] { stockSearchForm.getStockType() }), 
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"I.SUPPLIER_ID",	SQLConstantOperType.EQUALS,new Object[] { stockSearchForm.getSupplierId() }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"I.ITEM_ID",		SQLConstantOperType.EQUALS,new Object[] { stockSearchForm.getItemId() })
			});
			stockSearchForm.setOrderBy("S.STOCK_ID");
			stockSearchForm.setOrderType("DESC");
			
			resultDataList = inquiryDAO.listByPage(stockSearchForm);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			stockSearchForm.getResultBO().setException(e);
			stockSearchForm.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultDataList;
	}

	@Override
	public ResultBO createStockTransaction(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		StockItemForm formDataBO = (StockItemForm)dataBo;
		TStockItem stock = new TStockItem();
		MItem item = new MItem();
		
		try {
			stock.setStockCode(			formDataBO.getStockCode());
			stock.setStockQty(			formDataBO.getStockQty());
			stock.setItemId(			new MItem(Integer.parseInt(formDataBO.getItemId())));
			stock.setStockType(			formDataBO.getStockType());
			stock.setStockDate(			new Date());
			stock.setCreateBy(			formDataBO.getSecuriyBO().getUserAuthentication().getUserLogin());
			stock.setCreateDate(		new Date());
			stockDAO.create(stock);

			
			item = itemDAO.findById(Integer.parseInt(formDataBO.getItemId()));
			if(formDataBO.getStockType().equals("In"))
				item.setItemQty(item.getItemQty()+formDataBO.getStockQty());
			
			else if(formDataBO.getStockType().equals("Out"))
				item.setItemQty(item.getItemQty()-formDataBO.getStockQty());
			
			
			
			itemDAO.update(item);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}




	
}
