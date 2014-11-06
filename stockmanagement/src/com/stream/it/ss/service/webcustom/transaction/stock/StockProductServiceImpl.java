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
import com.stream.it.ss.hibernate.dao.MProductDAO;
import com.stream.it.ss.hibernate.dao.TStockProductDAO;
import com.stream.it.ss.hibernate.domain.MProduct;
import com.stream.it.ss.hibernate.domain.TStockProduct;
import com.stream.it.ss.hibernate.inquiry.SupplierInquiry;
import com.stream.it.ss.utils.format.SQLStringType;
import com.stream.it.ss.view.jsf.form.transaction.stock.*;




@Service("stockProductService")
public class StockProductServiceImpl implements StockProductService{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("stockProductTransactionsInquiry")
	private InquiryDAO inquiryDAO;
	
	@Autowired
	@Qualifier("TStockProductDAO")
	private TStockProductDAO stockDAO;
	
	@Autowired
	@Qualifier("MProductDAO")
	private MProductDAO productDAO;
		
	
	@Override
	public DataBo findStockDetail(Integer stockId) throws Exception {
		StockProductForm StockProductForm = new StockProductForm();
		
		try {
			TStockProduct stock = stockDAO.findById(stockId);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			StockProductForm.getResultBO().setException(e);
			StockProductForm.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return StockProductForm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SupplierInquiry> listTransaction(SearchBean searchBean) throws Exception {
		StockProductSearchForm stockSearchForm = (StockProductSearchForm) searchBean;
		List<SupplierInquiry> resultDataList = new ArrayList<SupplierInquiry>();

		try {
			stockSearchForm.setConditionValuesBean(new SearchConditionValuesBean[] {
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"S.STOCK_CODE",		SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(stockSearchForm.getStockCode()) }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"S.STOCK_TYPE",		SQLConstantOperType.EQUALS,new Object[] { stockSearchForm.getStockType() }), 
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"I.SUPPLIER_ID",	SQLConstantOperType.EQUALS,new Object[] { stockSearchForm.getSupplierId() }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"I.PRODUCT_ID",		SQLConstantOperType.EQUALS,new Object[] { stockSearchForm.getProductId() })
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
		StockProductForm formDataBO = (StockProductForm)dataBo;
		TStockProduct stock = new TStockProduct();
		MProduct product = new MProduct();
		
		try {
			stock.setStockCode(			formDataBO.getStockCode());
			stock.setStockQty(			formDataBO.getStockQty());
			stock.setProductId(			new MProduct(Integer.parseInt(formDataBO.getProductId())));
			stock.setStockType(			formDataBO.getStockType());
			stock.setStockDate(			new Date());
			stock.setCreateBy(			formDataBO.getSecuriyBO().getUserAuthentication().getUserLogin());
			stock.setCreateDate(		new Date());
			stockDAO.create(stock);

			
			product = productDAO.findById(Integer.parseInt(formDataBO.getProductId()));
			if(formDataBO.getStockType().equals("In"))
				product.setProductQty(product.getProductQty()+formDataBO.getStockQty());
			
			else if(formDataBO.getStockType().equals("Out"))
				product.setProductQty(product.getProductQty()-formDataBO.getStockQty());
			
			
			productDAO.update(product);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}




	
}
