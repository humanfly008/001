package com.stream.it.ss.view.jsf.form.transaction.salary;


import com.stream.it.ss.base.databo.DataBo;

public class SalaryIncomeForm extends DataBo{
	private String incomeType;
	private String incomeTypeDesc;
	private String id;
	private String userId;
	private Integer month;
	private Integer year;
	private Double income;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
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
	public String getIncomeTypeDesc() {
		return incomeTypeDesc;
	}
	public void setIncomeTypeDesc(String incomeTypeDesc) {
		this.incomeTypeDesc = incomeTypeDesc;
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
}
