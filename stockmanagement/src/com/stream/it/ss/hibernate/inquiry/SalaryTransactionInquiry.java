package com.stream.it.ss.hibernate.inquiry;

import java.io.Serializable;

import com.stream.it.ss.utils.format.NumberType;
import com.stream.it.ss.utils.format.StringType;

public class SalaryTransactionInquiry implements Serializable{
	private static final long serialVersionUID = 1L;
	private String no;
	private String id;
	private String userId;
	private String firstName;
	private String lastName;
	private String fullName;
	private String idCard;
	private String position;
	private String payType;
	private Double salary;
	private Double daily;
	private Double fare;
	private Double diligence;
	private Double bonus;
	private Double otherIncome;
	private Double otPerHour;
	private Integer otDate;
	private Integer otHour;
	private Double otRate;
	private Double otSummary;
	private Integer socialInsurance;
	private Integer tax;
	
	private Double leaveSubtract;
	private Double accumulateSubtract;
	private Double otherSubtract;	
	private Double subtractTax;
	private Double subtractSocial;
		
	private Double totalSalaryIncome;
	private Double totalSalaryIncomeNet;
	
	private String details;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public Double getOtPerHour() {
		return otPerHour;
	}
	public void setOtPerHour(Double otPerHour) {
		this.otPerHour = otPerHour;
	}
	public Integer getOtDate() {
		return otDate;
	}
	public void setOtDate(Integer otDate) {
		this.otDate = otDate;
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
	public Double getOtSummary() {
		return otSummary;
	}
	public void setOtSummary(Double otSummary) {
		this.otSummary = otSummary;
	}
	public Integer getSocialInsurance() {
		return socialInsurance;
	}
	public void setSocialInsurance(Integer socialInsurance) {
		this.socialInsurance = socialInsurance;
	}
	public Integer getTax() {
		return tax;
	}
	public Double getSubtractTax() {
		return subtractTax;
	}
	public void setSubtractTax(Double subtractTax) {
		this.subtractTax = subtractTax;
	}
	public Double getSubtractSocial() {
		return subtractSocial;
	}
	public void setSubtractSocial(Double subtractSocial) {
		this.subtractSocial = subtractSocial;
	}
	public void setTax(Integer tax) {
		this.tax = tax;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Double getTotalSalaryIncomeNet() {
		return totalSalaryIncomeNet;
	}
	public void setTotalSalaryIncomeNet(Double totalSalaryIncomeNet) {
		this.totalSalaryIncomeNet = totalSalaryIncomeNet;
	}
	public Double getTotalSalaryIncome() {
		return totalSalaryIncome;
	}
	public void setTotalSalaryIncome(Double totalSalaryIncome) {
		this.totalSalaryIncome = totalSalaryIncome;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Double getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}
	public Double getOtherSubtract() {
		return otherSubtract;
	}
	public void setOtherSubtract(Double otherSubtract) {
		this.otherSubtract = otherSubtract;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getLeaveSubtract() {
		return leaveSubtract;
	}
	public void setLeaveSubtract(Double leaveSubtract) {
		this.leaveSubtract = leaveSubtract;
	}
	public Double getAccumulateSubtract() {
		return accumulateSubtract;
	}
	public void setAccumulateSubtract(Double accumulateSubtract) {
		this.accumulateSubtract = accumulateSubtract;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getFullName() {
		return "".concat(firstName).concat(" ").concat(lastName).toString();
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	//*** FORMATTER ***//
	public String getSalaryStr(){
		if("DAY".equals(payType)) return "";
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(salary));
	}
	public String getDailyStr(){
		if("MONTH".equals(payType)) return "";
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(daily));
	}
	public String getFareStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(fare));
	}
	public String getDiligenceStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(diligence));
	}
	public String getBonusStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(bonus));
	}
	public String getOtPerHourStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(otPerHour));
	}
	public String getOtDateStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(otDate));
	}
	public String getOtHourStr(){
		return StringType.getIntegerNumberPercentFormatted(NumberType.getDouble(otHour));
	}
	public String getOtRateStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(otRate));
	}
	public String getOtSummaryStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(otSummary));
	}
	public String getSocialInsuranceStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(socialInsurance));
	}
	public String getTaxStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(tax));
	}
	public String getOtherIncomeStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(otherIncome));
	}
	public String getLeaveSubtractStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(leaveSubtract));
	}
	public String getAccumulateSubtractStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(accumulateSubtract));
	}	
	public String getSubtractSocialStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(subtractSocial));
	}
	public String getSubtractTaxStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(subtractTax));
	}
	public String getOtherSubtractStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(otherSubtract));
	}	
	public String getTotalSalaryIncomeStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(totalSalaryIncome));
	}
	public String getTotalSalaryIncomeNetStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(totalSalaryIncomeNet));
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Integer getDailyDay(){
		if(daily.intValue()==0 || salary.intValue()==0)
			return 0;
		
		return daily.intValue()/salary.intValue();
	}
	public String getTotalSubtractStr(){
		return StringType.getDoubleNumberMoneyFormatted(NumberType.getDouble(subtractSocial+subtractTax));
	}
	public String getPayTypeDesc(){
		if(payType.equals("DAY"))return "รายวัน";
		else if(payType.equals("MONTH")) return "รายเดือน";
			
		return "";
	}
}
