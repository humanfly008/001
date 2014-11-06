package com.stream.it.ss.hibernate.inquiry;

import java.io.Serializable;
import java.util.Date;

public class SupplierInquiry implements Serializable{
	private static final long serialVersionUID = 1L;
	private String no;
	private String supplierId;
	private String supplierCode;
	private String supplierName;
	private String contract;
	private String phone;
	private String details;
	
	private Date createDate;
	private String createBy;
	private Date uppdateDate;
	private String updateBy;	
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getUppdateDate() {
		return uppdateDate;
	}
	public void setUppdateDate(Date uppdateDate) {
		this.uppdateDate = uppdateDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
