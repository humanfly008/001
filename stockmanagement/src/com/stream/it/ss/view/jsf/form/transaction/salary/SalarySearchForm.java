package com.stream.it.ss.view.jsf.form.transaction.salary;

import com.stream.it.ss.base.databo.SearchBean;

public class SalarySearchForm extends SearchBean{
	private String id;
	private String userId;
	private String userName;
	private int month;
	private int year;
	
	private String totalSalary;
	private String totalDaily;
	private String totalFare;
	private String totalDiligence;
	private String totalBonus;
	private String totalOtherIncome;
	private Integer totalOtDate;
	private Integer totalOtHour;
	private String totalOtSummary;
	
	private String totalSubtractTax;
	private String totalSubtractSocial;
	private String totalSubtractOther;
	
	private String totalSalaryIncome;
	private String totalSalaryIncomeNet;
	
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTotalSalary() {
		return totalSalary;
	}
	public void setTotalSalary(String totalSalary) {
		this.totalSalary = totalSalary;
	}
	public String getTotalDaily() {
		return totalDaily;
	}
	public void setTotalDaily(String totalDaily) {
		this.totalDaily = totalDaily;
	}
	public String getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(String totalFare) {
		this.totalFare = totalFare;
	}
	public String getTotalDiligence() {
		return totalDiligence;
	}
	public void setTotalDiligence(String totalDiligence) {
		this.totalDiligence = totalDiligence;
	}
	public String getTotalBonus() {
		return totalBonus;
	}
	public void setTotalBonus(String totalBonus) {
		this.totalBonus = totalBonus;
	}
	public Integer getTotalOtDate() {
		return totalOtDate;
	}
	public void setTotalOtDate(Integer totalOtDate) {
		this.totalOtDate = totalOtDate;
	}
	public Integer getTotalOtHour() {
		return totalOtHour;
	}
	public void setTotalOtHour(Integer totalOtHour) {
		this.totalOtHour = totalOtHour;
	}
	public String getTotalOtSummary() {
		return totalOtSummary;
	}
	public void setTotalOtSummary(String totalOtSummary) {
		this.totalOtSummary = totalOtSummary;
	}
	public String getTotalSalaryIncome() {
		return totalSalaryIncome;
	}
	public void setTotalSalaryIncome(String totalSalaryIncome) {
		this.totalSalaryIncome = totalSalaryIncome;
	}
	public String getTotalSalaryIncomeNet() {
		return totalSalaryIncomeNet;
	}
	public void setTotalSalaryIncomeNet(String totalSalaryIncomeNet) {
		this.totalSalaryIncomeNet = totalSalaryIncomeNet;
	}
	public String getTotalSubtractTax() {
		return totalSubtractTax;
	}
	public void setTotalSubtractTax(String totalSubtractTax) {
		this.totalSubtractTax = totalSubtractTax;
	}
	public String getTotalSubtractSocial() {
		return totalSubtractSocial;
	}
	public void setTotalSubtractSocial(String totalSubtractSocial) {
		this.totalSubtractSocial = totalSubtractSocial;
	}
	public String getTotalOtherIncome() {
		return totalOtherIncome;
	}
	public void setTotalOtherIncome(String totalOtherIncome) {
		this.totalOtherIncome = totalOtherIncome;
	}
	public String getTotalSubtractOther() {
		return totalSubtractOther;
	}
	public void setTotalSubtractOther(String totalSubtractOther) {
		this.totalSubtractOther = totalSubtractOther;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
