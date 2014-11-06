package com.stream.it.ss.view.jsf.form.setting.supplier;


import com.stream.it.ss.base.databo.SearchBean;


public class SupplierSearchForm extends SearchBean{
	private String supplierName;
	private String supplierContract;
	
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierContract() {
		return supplierContract;
	}
	public void setSupplierContract(String supplierContract) {
		this.supplierContract = supplierContract;
	}
}
