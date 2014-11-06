package com.stream.it.ss.hibernate.inquiry;

import java.io.Serializable;

public class StockExhaustedInquiry implements Serializable{
	private static final long serialVersionUID = 1L;
	private String no;
	private String transactionType;
	private String objectId;
	private String objectCode;
	private String objectName;
	private Double unitPrice;
	private Integer qty;
	private String supplier;
	private String objectSupplierCode;
	private String status;
	
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getObjectCode() {
		return objectCode;
	}
	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
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
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getObjectSupplierCode() {
		return objectSupplierCode;
	}
	public void setObjectSupplierCode(String objectSupplierCode) {
		this.objectSupplierCode = objectSupplierCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getStatusDesc(){
		if(status.equals("LEVEL1"))return "น้อยมาก";
		else if(status.equals("LEVEL2"))return "น้อย";
		else return "ปานกลาง";
	}
}
