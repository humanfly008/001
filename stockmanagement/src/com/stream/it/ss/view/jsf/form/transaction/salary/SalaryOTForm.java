package com.stream.it.ss.view.jsf.form.transaction.salary;

import java.util.Date;

import com.stream.it.ss.base.databo.DataBo;

public class SalaryOTForm extends DataBo{
	private String id;
	private String userId;
	private String userFullName;
	private String payType;
	
	private Double otRate;
	private Integer otHour;
	private Date otDate;
	
	private Double income;
	private Double salary;
	private Double daily;
	private Double fare;
	private Double diligence;
	private Double bonus;
	private Double socialInsurance;
	private Double tax;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Double getOtRate() {
		return otRate;
	}
	public void setOtRate(Double otRate) {
		this.otRate = otRate;
	}
	public Integer getOtHour() {
		return otHour;
	}
	public void setOtHour(Integer otHour) {
		this.otHour = otHour;
	}
	public Date getOtDate() {
		return otDate;
	}
	public void setOtDate(Date otDate) {
		this.otDate = otDate;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Double getDaily() {
		return daily;
	}
	public void setDaily(Double daily) {
		this.daily = daily;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public Double getDiligence() {
		return diligence;
	}
	public void setDiligence(Double diligence) {
		this.diligence = diligence;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public Double getSocialInsurance() {
		return socialInsurance;
	}
	public void setSocialInsurance(Double socialInsurance) {
		this.socialInsurance = socialInsurance;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
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
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
}
