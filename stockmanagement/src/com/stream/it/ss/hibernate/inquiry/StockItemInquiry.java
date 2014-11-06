package com.stream.it.ss.hibernate.inquiry;

import java.io.Serializable;
import java.util.Date;

import com.stream.it.ss.utils.format.DateUtil;
import com.stream.it.ss.utils.format.NumberType;
import com.stream.it.ss.utils.format.StringType;

public class StockItemInquiry implements Serializable{
	private static final long serialVersionUID = 1L;
	private String no;
	private String stockId;
	private String stockCode;
	private String stockType;
	private Date stockDate;
	private String itemCode;
	private String itemName;
	private String itemType;
	private String supplierName;
	private Integer stockQty;
	private Date createDate;
	private String createBy;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
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
	public Date getStockDate() {
		return stockDate;
	}
	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public Integer getStockQty() {
		return stockQty;
	}
	public void setStockQty(Integer stockQty) {
		this.stockQty = stockQty;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplier) {
		this.supplierName = supplier;
	}
	//*** FORMATTER ***//
	public String getStockTypeDesc(){
		if(stockType.equals("In")) return "เข้า";
		else if(stockType.equals("Out")) return "ออก";

		return "";
	}
	public String getStockQtyStr(){
		return StringType.getIntegerNumberPercentFormatted(NumberType.getDouble(stockQty));
	}	
	public String getStockDateStr(){
		return DateUtil.getStrDate(stockDate);
	}
	public String getCreateDateStr(){
		return DateUtil.getStrDate(createDate);
	}
	
}