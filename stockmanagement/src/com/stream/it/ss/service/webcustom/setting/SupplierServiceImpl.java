package com.stream.it.ss.service.webcustom.setting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.stream.it.ss.hibernate.dao.MSupplierDAO;
import com.stream.it.ss.hibernate.domain.MSupplier;
import com.stream.it.ss.hibernate.domain.MSupplierType;
import com.stream.it.ss.hibernate.inquiry.SupplierInquiry;
import com.stream.it.ss.utils.format.SQLStringType;
import com.stream.it.ss.view.jsf.form.setting.supplier.SupplierForm;
import com.stream.it.ss.view.jsf.form.setting.supplier.SupplierSearchForm;




@Service("supplierService")
public class SupplierServiceImpl implements SupplierService{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("supplierTransactionInquiry")
	private InquiryDAO inquiryDAO;
	
	@Autowired
	@Qualifier("MSupplierDAO")
	private MSupplierDAO supplierDAO;
	
	@Override
	public DataBo findSupplier(Integer supplierId) throws Exception {
		SupplierForm supplierForm = new SupplierForm();
		
		try {
			MSupplier supplier = supplierDAO.findById(supplierId);
			supplierForm.setSupplierId(			supplier.getSupplierId());
			supplierForm.setSupplierCode(		supplier.getSupplierCode());
			supplierForm.setSupplierName(		supplier.getSupplierNameTh());
			supplierForm.setSupplierType(		supplier.getSupplierType());
			supplierForm.setAddress(			supplier.getSupplierAddress());
			supplierForm.setContract(			supplier.getSupplierContract());
			supplierForm.setPhone(				supplier.getSupplierPhone());
			supplierForm.setFax(				supplier.getSupplierFax());
			supplierForm.setEmail(				supplier.getSupplierEmail());
			supplierForm.setDetails(			supplier.getSupplierDetails());
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			supplierForm.getResultBO().setException(e);
			supplierForm.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return supplierForm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SupplierInquiry> listTransaction(SearchBean searchBean) throws Exception {
		SupplierSearchForm supplierSearchForm = (SupplierSearchForm) searchBean;
		List<SupplierInquiry> resultDataList = new ArrayList<SupplierInquiry>();

		try {
			supplierSearchForm.setConditionValuesBean(new SearchConditionValuesBean[] {
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"SUPPLIER_NAME_TH",		SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(supplierSearchForm.getSupplierName()) }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"SUPPLIER_CONTRACT",	SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(supplierSearchForm.getSupplierContract()) })

			});

			resultDataList = inquiryDAO.listByPage(supplierSearchForm);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			supplierSearchForm.getResultBO().setException(e);
			supplierSearchForm.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultDataList;
	}

	@Override
	public ResultBO createSupplier(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		SupplierForm supplierForm = (SupplierForm)dataBo;
		
		try {
			MSupplier supplier = new MSupplier();
			supplier.setSupplierCode(		supplierForm.getSupplierCode());
			supplier.setSupplierNameTh(		supplierForm.getSupplierName());
			supplier.setSupplierType(		supplierForm.getSupplierType());
			supplier.setSupplierContract(	supplierForm.getContract());
			supplier.setSupplierPhone(		supplierForm.getPhone());
			supplier.setSupplierFax(		supplierForm.getFax());
			supplier.setSupplierEmail(		supplierForm.getEmail());
			supplier.setSupplierDetails(	supplierForm.getDetails());
			supplier.setSupplierAddress(	supplierForm.getAddress());
			supplier.setCreateBy(			"Humanfly 008");
			supplier.setCreateDate(			new Date());
			supplierDAO.create(supplier);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}

	@Override
	public ResultBO updateSupplier(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		SupplierForm supplierForm = (SupplierForm)dataBo;
		
		try {
			MSupplier supplier = new MSupplier();
			supplier.setSupplierId(			supplierForm.getSupplierId());
			supplier.setSupplierCode(		supplierForm.getSupplierCode());
			supplier.setSupplierNameTh(		supplierForm.getSupplierName());
			supplier.setSupplierType(		supplierForm.getSupplierType());
			supplier.setSupplierContract(	supplierForm.getContract());
			supplier.setSupplierPhone(		supplierForm.getPhone());
			supplier.setSupplierFax(		supplierForm.getFax());
			supplier.setSupplierEmail(		supplierForm.getEmail());
			supplier.setSupplierDetails(	supplierForm.getDetails());
			supplier.setSupplierAddress(	supplierForm.getAddress());
			supplier.setUpdateBy(			"Humanfly	008");
			supplier.setUppdateDate(		new Date());
			supplierDAO.update(supplier);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}

	@Override
	public ResultBO deleteSupplier(String[] supplierId) throws Exception {
		ResultBO resultBO = new ResultBO();

		try {
			for (int i = 0; i < supplierId.length; i++) {
				MSupplier supplier = supplierDAO.findById(Integer.parseInt(supplierId[i]));
				supplierDAO.delete(supplier);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}


	
}
