package com.stream.it.ss.view.jsf.form.setting.users;

import com.stream.it.ss.base.databo.SearchBean;

public class UserSearchForm extends SearchBean{
	private String userId;
	private String firstName;
	private String lastName;
	
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
}
