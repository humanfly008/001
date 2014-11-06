package com.stream.it.ss.hibernate.inquiry;

import java.io.Serializable;


public class SalaryOtDetailInquery implements Serializable{
	private String transactionId;
	private Integer date;
	private Integer month;
	private Integer year;
	private Integer otHour;
	private Double otRate;
	
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getOtHour() {
		return otHour;
	}
	public void setOtHour(Integer otHour) {
		this.otHour = otHour;
	}
	public Double getOtRate() {
		return otRate;
	}
	public void setOtRate(Double otRate) {
		this.otRate = otRate;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
