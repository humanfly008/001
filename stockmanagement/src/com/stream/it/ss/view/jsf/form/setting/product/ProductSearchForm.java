package com.stream.it.ss.view.jsf.form.setting.product;

import com.stream.it.ss.base.databo.SearchBean;


public class ProductSearchForm extends SearchBean{
	private String productCode;
	private String productName;
	
	private String supplier;
	private String productType;
	private String unit;
	
	public String getItemCode() {
		return productCode;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
}
