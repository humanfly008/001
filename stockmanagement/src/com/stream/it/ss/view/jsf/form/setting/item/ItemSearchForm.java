package com.stream.it.ss.view.jsf.form.setting.item;

import java.util.Date;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.SearchBean;

public class ItemSearchForm extends SearchBean{
	private String itemCode;
	private String itemName;
	
	private String supplier;
	private String itemType;
	private String unit;
	
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
