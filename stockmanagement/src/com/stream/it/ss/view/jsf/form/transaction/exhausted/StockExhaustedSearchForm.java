package com.stream.it.ss.view.jsf.form.transaction.exhausted;

import com.stream.it.ss.base.databo.SearchBean;

public class StockExhaustedSearchForm extends SearchBean implements Cloneable{
	private String transactionType;
	private String itemId;
	private String productId;
	private String supplier;
	private String status;
	
	@Override
    protected Object clone() throws CloneNotSupportedException {
    	return super.clone();
    }
	
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
}
