package com.stream.it.ss.base.databo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class UserAuthentication implements Serializable{
	private String userLogin;
	private String userAccountName;
	private String departmentDesc;
	private String roleId;
	private String roleDesc;
	private String ipAddress;
	private String functionsCanUse;
	
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getUserAccountName() {
		return userAccountName;
	}
	public void setUserAccountName(String userAccountName) {
		this.userAccountName = userAccountName;
	}
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getFunctionsCanUse() {
		return functionsCanUse;
	}
	public void setFunctionsCanUse(String functionsCanUse) {
		this.functionsCanUse = functionsCanUse;
	}
}
