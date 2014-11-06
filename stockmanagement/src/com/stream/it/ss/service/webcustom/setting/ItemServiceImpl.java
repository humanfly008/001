package com.stream.it.ss.service.webcustom.setting;

import java.io.File;
import java.util.ArrayList;
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
import com.stream.it.ss.hibernate.dao.MItemDAO;
import com.stream.it.ss.hibernate.domain.MItem;
import com.stream.it.ss.hibernate.domain.MSupplier;
import com.stream.it.ss.hibernate.inquiry.ItemInquiry;
import com.stream.it.ss.utils.format.SQLStringType;
import com.stream.it.ss.view.jsf.form.setting.item.ItemForm;
import com.stream.it.ss.view.jsf.form.setting.item.ItemSearchForm;



@Service("itemService")
public class ItemServiceImpl implements ItemService{
	private static final ResourceBundle systemProperties = ResourceBundle.getBundle("system");
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("itemTransactionInquiry")
	private InquiryDAO inquiryDAO;
	
	@Autowired
	@Qualifier("MItemDAO")
	private MItemDAO itemDAO;
	
	@Override
	public DataBo findItem(Integer itemId) throws Exception {
		ItemForm itemForm = new ItemForm();
		
		try {
			MItem item = itemDAO.findById(itemId);
			itemForm.setItemId(				item.getItemId());
			itemForm.setItemCode(			item.getItemCode());
			itemForm.setItemName(			item.getItemNameTh());
			
			itemForm.setQty(				item.getItemQty());
			itemForm.setUnitPrice(			item.getItemUnitPrice());
			
			itemForm.setItemType(			item.getItemType());
			itemForm.setSupplierId(			item.getSupplierId().getSupplierId()+"");
			itemForm.setItemSupplierCode(	item.getItemSupplierCode());
			itemForm.setUnit(				item.getUnit());
			itemForm.setSetPerUse(			item.getSetPerUse());
			itemForm.setNotificateLevel1Unit(item.getNorificateLevel1Unit());
			itemForm.setNotificateLevel2Unit(item.getNorificateLevel2Unit());
			itemForm.setNotificateLevel3Unit(item.getNorificateLevel3Unit());

			if(item.getPicName()==null)
				itemForm.setPicName(			 "notfound.jpg");
			else			
				itemForm.setPicName(			 "Item/"+item.getPicName());
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			itemForm.getResultBO().setException(e);
			itemForm.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return itemForm;
	}

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

			resultDataList = inquiryDAO.listByPage(itemSearchForm);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			itemSearchForm.getResultBO().setException(e);
			itemSearchForm.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultDataList;
	}

	@Override
	public ResultBO createItem(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		ItemForm itemForm = (ItemForm)dataBo;
		
		try {
			//*** MOVE FILE ***//
			String newFile = itemForm.getPicName().replaceAll("temp/", "");
			
			if(!newFile.equals("notfound.jpg")){
				String filePicPath = systemProperties.getString("pic.store.file");
				String filePicTempPath = filePicPath+itemForm.getPicName();
			
				File fileTemp =new File(filePicTempPath);
				fileTemp.renameTo(new File(filePicPath+"Item/"+newFile));

				System.out.println("filePicTempPath : "+filePicTempPath);
				System.out.println("new File : "+filePicPath+"Item/"+newFile);
			
			}else
				newFile = null;
			
			
			MItem item = new MItem();
			item.setItemId(					itemForm.getItemId());
			item.setItemCode(				itemForm.getItemCode());
			item.setItemNameTh(				itemForm.getItemName());
			item.setItemQty(				itemForm.getQty());
			item.setItemUnitPrice(			itemForm.getUnitPrice());
			item.setItemType(				itemForm.getItemType());
			item.setSupplierId(				new MSupplier(Integer.parseInt(itemForm.getSupplierId())));
			item.setUnit(					itemForm.getUnit());
			item.setSetPerUse(				itemForm.getSetPerUse());
			item.setItemSupplierCode(		itemForm.getItemSupplierCode());
			item.setNorificateLevel1Unit(	itemForm.getNotificateLevel1Unit());
			item.setNorificateLevel2Unit(	itemForm.getNotificateLevel2Unit());
			item.setNorificateLevel3Unit(	itemForm.getNotificateLevel3Unit());
			item.setPicName(				newFile);
			itemDAO.create(item);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}

	@Override
	public ResultBO updateItem(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		ItemForm itemForm = (ItemForm)dataBo;
		String filePicPath = systemProperties.getString("pic.store.file");
				
		try {
			MItem item = itemDAO.findById(itemForm.getItemId());
			
			//*** MOVE FILE ***//
			String newFileName = itemForm.getPicName().replaceAll("temp/", "");
			String oldFileName = item.getPicName();
			
			if(!newFileName.equals("notfound.jpg")){
				if(oldFileName==null){
					File source = new File(filePicPath+"temp/"+newFileName);
					File dest = new File(filePicPath+"Item/");
					FileUtils.copyFileToDirectory(source, dest);
					source.delete();
					
					item.setPicName(				newFileName);
				}else{
					String chkOldPic = newFileName.replaceAll("Item/", "");
					
					if(!chkOldPic.equals(oldFileName)){
						File source = new File(filePicPath+"temp/"+newFileName);
						File dest = new File(filePicPath+"Item/"+oldFileName);
						dest.delete();
					
						source.renameTo(new File(filePicPath+"Item/"+oldFileName));
					
						item.setPicName(				oldFileName);
					}
				}
				
			}else
				item.setPicName(				null);
			

			item.setItemId(					itemForm.getItemId());
			item.setItemCode(				itemForm.getItemCode());
			item.setItemNameTh(				itemForm.getItemName());
			item.setItemUnitPrice(			itemForm.getUnitPrice());
			item.setItemType(				itemForm.getItemType());
			item.setSupplierId(				new MSupplier(Integer.parseInt(itemForm.getSupplierId())));
			item.setUnit(					itemForm.getUnit());
			item.setItemQty(				itemForm.getQty());
			item.setSetPerUse(				itemForm.getSetPerUse());
			item.setItemSupplierCode(		itemForm.getItemSupplierCode());
			item.setNorificateLevel1Unit(	itemForm.getNotificateLevel1Unit());
			item.setNorificateLevel2Unit(	itemForm.getNotificateLevel2Unit());
			item.setNorificateLevel3Unit(	itemForm.getNotificateLevel3Unit());
			
			itemDAO.update(item);
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}

	@Override
	public ResultBO deleteItem(String[] itemId) throws Exception {
		ResultBO resultBO = new ResultBO();

		try {
			for (int i = 0; i < itemId.length; i++) {
				MItem item = itemDAO.findById(Integer.parseInt(itemId[i]));
				itemDAO.delete(item);
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
