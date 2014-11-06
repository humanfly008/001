package com.stream.it.ss.view.jsf.form.setting.item;


import com.stream.it.ss.base.databo.DataBo;

public class ItemForm extends DataBo{
	private Integer itemId;
	private String itemCode;
	private String itemName;
	private Double unitPrice;
	private Integer qty;
	
	private String itemType;
	private String unit;
	private String supplierId;
	
	private String itemSupplierCode;
	private Integer setPerUse;
	private Integer notificateLevel1Unit;
	private Integer notificateLevel2Unit;
	private Integer notificateLevel3Unit;
	private String picName;
	
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
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
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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
	public String getItemSupplierCode() {
		return itemSupplierCode;
	}
	public void setItemSupplierCode(String itemSupplierCode) {
		this.itemSupplierCode = itemSupplierCode;
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
	public String getPicName() {
		if(picName==null || "".equals(picName))
			picName = "notfound.jpg";
		
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
}
