package com.stream.it.ss.view.jsf.form.transaction.stock;


import java.util.Date;

import com.stream.it.ss.base.databo.SearchBean;


public class StockItemSearchForm extends SearchBean{
	private String stockCode;
	private Date stockFromDate;
	private Date stockToDate;	
	private String stockType;
	private String supplierId;
	private String itemId;	
	
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockType() {
		return stockType;
	}
	public void setStockType(String stockType) {
		this.stockType = stockType;
	}
	public Date getStockFromDate() {
		return stockFromDate;
	}
	public void setStockFromDate(Date stockFromDate) {
		this.stockFromDate = stockFromDate;
	}
	public Date getStockToDate() {
		return stockToDate;
	}
	public void setStockToDate(Date stockToDate) {
		this.stockToDate = stockToDate;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
}
