package com.stream.it.ss.hibernate.inquiry;

import java.io.Serializable;

import com.stream.it.ss.utils.format.NumberType;
import com.stream.it.ss.utils.format.StringType;

public class ProductInquiry implements Serializable{
	private static final long serialVersionUID = 1L;
	private String no;
	private String productId;
	private String productCode;
	private String productName;
	
	private String productUnitPrice;
	private String productQty;
	
	private String unit;
	private String productType;
	private String supplier;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
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
	public String getProductUnitPrice() {
		return productUnitPrice;
	}
	public void setProductUnitPrice(String productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}
	public String getProductQty() {
		return productQty;
	}
	public void setProductQty(String productQty) {
		this.productQty = productQty;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//*** FORMATTER ***//
	public String getProductUnitPriceStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(productUnitPrice));
	}
	public String getProductQtyStr(){
		return StringType.getIntegerNumberPercentFormatted(NumberType.getDouble(productQty));
	}	
}
