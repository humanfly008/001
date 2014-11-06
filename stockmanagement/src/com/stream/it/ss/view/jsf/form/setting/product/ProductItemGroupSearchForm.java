package com.stream.it.ss.view.jsf.form.setting.product;

import com.stream.it.ss.base.databo.SearchBean;


public class ProductItemGroupSearchForm extends SearchBean{
	private String supplierId;
	private String itemId;

	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
}
