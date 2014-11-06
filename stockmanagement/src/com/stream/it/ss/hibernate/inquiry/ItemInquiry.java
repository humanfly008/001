package com.stream.it.ss.hibernate.inquiry;

import java.io.Serializable;

import com.stream.it.ss.utils.format.NumberType;
import com.stream.it.ss.utils.format.StringType;

public class ItemInquiry implements Serializable{
	private static final long serialVersionUID = 1L;
	private String no;
	private String itemId;
	private String itemCode;
	private String itemName;
	
	private String itemUnitPrice;
	private String itemQty;
	
	private String unit;
	private String itemType;
	private String supplier;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
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
	public String getItemUnitPrice() {
		return itemUnitPrice;
	}
	public void setItemUnitPrice(String itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}
	public String getItemQty() {
		return itemQty;
	}
	public void setItemQty(String itemQty) {
		this.itemQty = itemQty;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
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
	
	//*** FORMATTER ***//
	public String getItemUnitPriceStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(itemUnitPrice));
	}
	public String getItemQtyStr(){
		return StringType.getIntegerNumberPercentFormatted(NumberType.getDouble(itemQty));
	}	
}
