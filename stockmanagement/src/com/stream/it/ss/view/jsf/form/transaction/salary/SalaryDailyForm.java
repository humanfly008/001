package com.stream.it.ss.view.jsf.form.transaction.salary;

import java.util.Date;

import com.stream.it.ss.base.databo.DataBo;

public class SalaryDailyForm extends DataBo{
	private Double income;
	private String incomeType;
	private String incomeTypeDesc;
	private String id;
	private String userId;
	private Date date;
	private String userFullName;
	
	public String getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}
	public String getIncomeTypeDesc() {
		return incomeTypeDesc;
	}
	public void setIncomeTypeDesc(String incomeTypeDesc) {
		this.incomeTypeDesc = incomeTypeDesc;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
}
