package com.stream.it.ss.view.jsf.form.setting.users;

import java.util.ArrayList;
import java.util.List;

import com.stream.it.ss.base.databo.DataBo;

public class UserForm extends DataBo{
	private int id;
	private String userId;
	private String password;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	private String department;
	private String phone;
	private String payType;
	private Double salary=0.0;
	private Integer socialInsurance=5;
	private Integer tax=7;
	private String status = "enable";	
	private String functionUsed;
	
	private List<?> functionSelected = new ArrayList();
	private List<?> menuSelected = new ArrayList();
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public List<?> getFunctionSelected() {
		return functionSelected;
	}
	public void setFunctionSelected(List<?> functionSelected) {
		this.functionSelected = functionSelected;
	}
	public List<?> getMenuSelected() {
		return menuSelected;
	}
	public void setMenuSelected(List<?> menuSelected) {
		this.menuSelected = menuSelected;
	}
	public String getFunctionUsed() {
		return functionUsed;
	}
	public void setFunctionUsed(String functionUsed) {
		this.functionUsed = functionUsed;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
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
	public void setTax(Integer tax) {
		this.tax = tax;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
