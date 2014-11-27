package com.stream.it.ss.service.webcustom.setting;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
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
import com.stream.it.ss.hibernate.dao.MProductItemGroupDAO;
import com.stream.it.ss.hibernate.domain.MItem;
import com.stream.it.ss.hibernate.domain.MProduct;
import com.stream.it.ss.hibernate.domain.MProductItemGroup;
import com.stream.it.ss.hibernate.domain.MSupplier;
import com.stream.it.ss.hibernate.inquiry.ProductInquiry;
import com.stream.it.ss.utils.format.SQLStringType;
import com.stream.it.ss.view.jsf.form.setting.product.ProductForm;
import com.stream.it.ss.view.jsf.form.setting.product.ProductSearchForm;





@Service("productService")
public class ProductServiceImpl implements ProductService{
	private static final ResourceBundle systemProperties = ResourceBundle.getBundle("system");
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("productTransactionInquiry")
	private InquiryDAO inquiryDAO;
	
	@Autowired
	@Qualifier("productItemGroupInquiry")
	private InquiryDAO productItemGroupInquiryDAO;
	
	@Autowired
	@Qualifier("MProductDAO")
	private MProductDAO productDAO;
	
	@Autowired
	@Qualifier("MProductItemGroupDAO")
	private MProductItemGroupDAO productItemGroupDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public DataBo findProduct(Integer productId) throws Exception {
		ProductForm productForm = new ProductForm();
		
		try {
			MProduct product = productDAO.findById(productId);
			productForm.setProductId(			product.getProductId());
			productForm.setProductCode(			product.getProductCode());
			productForm.setProductName(			product.getProductNameTh());
			
			productForm.setQty(					product.getProductQty());
			productForm.setUnitPrice(			product.getProductUnitPrice());
			
			productForm.setProductType(			product.getProductType());
			productForm.setSupplierId(			product.getSupplierId().getSupplierId()+"");
			productForm.setProductSupplierCode(	product.getProductSupplierCode());
			productForm.setUnit(				product.getUnit());
			productForm.setSetPerUse(			product.getSetPerUse());
			productForm.setNotificateLevel1Unit(product.getNotiftcateLevel1Unit());
			productForm.setNotificateLevel2Unit(product.getNotiftcateLevel2Unit());
			productForm.setNotificateLevel3Unit(product.getNotiftcateLevel3Unit());
			
			if(product.getPicName()==null)
				productForm.setPicName(			 "notfound.jpg");
			else			
				productForm.setPicName(			 "Product/"+product.getPicName());
			
			//**** PRODUCT ITEM GROUP ****//
			SearchBean productItemGroupSearchBean = new SearchBean();
			productItemGroupSearchBean.setSqlParameter(Arrays.asList(productId));
			List<MItem> productItemGroupList = productItemGroupInquiryDAO.listAll(productItemGroupSearchBean);
			productForm.setProductItemGroupList(productItemGroupList);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			productForm.getResultBO().setException(e);
			productForm.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return productForm;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductInquiry> listTransaction(SearchBean searchBean) throws Exception {
		ProductSearchForm productSearchForm = (ProductSearchForm) searchBean;
		List<ProductInquiry> resultDataList = new ArrayList<ProductInquiry>();

		try {
			productSearchForm.setConditionValuesBean(new SearchConditionValuesBean[] {
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"UPPER(I.PRODUCT_CODE)",			SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(productSearchForm.getProductCode())}),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"UPPER(I.PRODUCT_NAME_TH)",		SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(productSearchForm.getProductName()) }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"UNIT",							SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(productSearchForm.getUnit()) }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"PRODUCT_TYPE",					SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(productSearchForm.getProductType()) }),
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

	@Override
	public ResultBO createProduct(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		ProductForm productForm = (ProductForm)dataBo;
		
		try {
			//*** MOVE FILE ***//
			String newFile = productForm.getPicName().replaceAll("temp/", "");
			
			if(!newFile.equals("notfound.jpg")){
				String filePicPath = systemProperties.getString("pic.store.file");
				String filePicTempPath = filePicPath+productForm.getPicName();
			
				File fileTemp =new File(filePicTempPath);
				fileTemp.renameTo(new File(filePicPath+"Product/"+newFile));

				System.out.println("filePicTempPath : "+filePicTempPath);
				System.out.println("new File : "+filePicPath+"Product/"+newFile);
			
			}else
				newFile = null;
			
			MProduct product = new MProduct();
			product.setProductId(					productForm.getProductId());
			product.setProductCode(				productForm.getProductCode());
			product.setProductNameTh(				productForm.getProductName());
			product.setProductQty(				productForm.getQty());
			product.setProductUnitPrice(			productForm.getUnitPrice());
			product.setProductType(				productForm.getProductType());
			product.setSupplierId(				new MSupplier(Integer.parseInt(productForm.getSupplierId())));
			product.setUnit(					productForm.getUnit());
			product.setSetPerUse(				productForm.getSetPerUse());
			product.setProductSupplierCode(		productForm.getProductSupplierCode());
			product.setNotiftcateLevel1Unit(	productForm.getNotificateLevel1Unit());
			product.setNotiftcateLevel2Unit(	productForm.getNotificateLevel2Unit());
			product.setNotiftcateLevel3Unit(	productForm.getNotificateLevel3Unit());
			product.setPicName(					newFile);
			
			for(int i=0; i<10000; i++){
				productDAO.create(product);
			}
			
			for(MItem item : productForm.getProductItemGroupList()){
				productItemGroupDAO.create(new MProductItemGroup(product.getProductId(), item.getItemId()));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}

	@Override
	public ResultBO updateProduct(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		ProductForm productForm = (ProductForm)dataBo;
		String filePicPath = systemProperties.getString("pic.store.file");
		
		try {
			MProduct product = productDAO.findById(productForm.getProductId());
			
			//*** MOVE FILE ***//
			String newFileName = productForm.getPicName().replaceAll("temp/", "");
			String oldFileName = product.getPicName();
			
			if(!newFileName.equals("notfound.jpg")){
				if(oldFileName==null){
					File source = new File(filePicPath+"temp/"+newFileName);
					File dest = new File(filePicPath+"Product/");
					FileUtils.copyFileToDirectory(source, dest);
					source.delete();
					
					product.setPicName(				newFileName);
					
				}else{
					String chkOldPic = newFileName.replaceAll("Product/", "");
					
					if(!chkOldPic.equals(oldFileName)){
						File source = new File(filePicPath+"temp/"+newFileName);
						File dest = new File(filePicPath+"Product/"+oldFileName);
						dest.delete();
						
						source.renameTo(new File(filePicPath+"Product/"+oldFileName));
						
						product.setPicName(				oldFileName);
					}
				}
				
			}else
				product.setPicName(				null);
			
			
			product.setProductId(				productForm.getProductId());
			product.setProductCode(				productForm.getProductCode());
			product.setProductNameTh(			productForm.getProductName());
			product.setProductUnitPrice(		productForm.getUnitPrice());
			product.setProductType(				productForm.getProductType());
			product.setSupplierId(				new MSupplier(Integer.parseInt(productForm.getSupplierId())));
			product.setUnit(					productForm.getUnit());
			product.setProductQty(				productForm.getQty());
			product.setSetPerUse(				productForm.getSetPerUse());
			product.setProductSupplierCode(		productForm.getProductSupplierCode());
			product.setNotiftcateLevel1Unit(	productForm.getNotificateLevel1Unit());
			product.setNotiftcateLevel2Unit(	productForm.getNotificateLevel2Unit());
			product.setNotiftcateLevel3Unit(	productForm.getNotificateLevel3Unit());
			productDAO.update(product);
			
			
			//**** PRODUCT ITEM GROUP *****//
			productItemGroupDAO.delete(productForm.getProductId());
			
			for(MItem item : productForm.getProductItemGroupList()){
				productItemGroupDAO.create(new MProductItemGroup(product.getProductId(), item.getItemId()));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}

	@Override
	public ResultBO deleteProduct(String[] productId) throws Exception {
		ResultBO resultBO = new ResultBO();

		try {
			for (int i = 0; i < productId.length; i++) {
				MProduct product = productDAO.findById(Integer.parseInt(productId[i]));
				productDAO.delete(product);
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
