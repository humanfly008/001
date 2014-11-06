package com.stream.it.ss.view.jsf.form.setting.product;


import java.util.List;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.hibernate.domain.MItem;

public class ProductForm extends DataBo{
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private String productCode;
	private String productName;
	private Double unitPrice;
	private Integer qty;
	
	private String productType;
	private String unit;
	private String supplierId;
	
	private String productSupplierCode;
	private Integer setPerUse;
	private Integer notificateLevel1Unit;
	private Integer notificateLevel2Unit;
	private Integer notificateLevel3Unit;
	private String picName;
	
	private List<MItem>productItemGroupList;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getProductSupplierCode() {
		return productSupplierCode;
	}
	public void setProductSupplierCode(String productSupplierCode) {
		this.productSupplierCode = productSupplierCode;
	}
	public Integer getSetPerUse() {
		return setPerUse;
	}
	public void setSetPerUse(Integer setPerUse) {
		this.setPerUse = setPerUse;
	}
	public Integer getNotificateLevel1Unit() {
		return notificateLevel1Unit;
	}
	public void setNotificateLevel1Unit(Integer notificateLevel1Unit) {
		this.notificateLevel1Unit = notificateLevel1Unit;
	}
	public Integer getNotificateLevel2Unit() {
		return notificateLevel2Unit;
	}
	public void setNotificateLevel2Unit(Integer notificateLevel2Unit) {
		this.notificateLevel2Unit = notificateLevel2Unit;
	}
	public Integer getNotificateLevel3Unit() {
		return notificateLevel3Unit;
	}
	public void setNotificateLevel3Unit(Integer notificateLevel3Unit) {
		this.notificateLevel3Unit = notificateLevel3Unit;
	}
	public List<MItem> getProductItemGroupList() {
		return productItemGroupList;
	}
	public void setProductItemGroupList(List<MItem> productItemGroupList) {
		this.productItemGroupList = productItemGroupList;
	}
	public String getPicName() {
		if(picName==null || "".equals(picName))
			picName = "notfound.jpg";
		
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
}
