package com.stream.it.ss.base.databo;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class SecuriyBO implements Serializable{
	private UserAuthentication userAuthentication;
	
	public SecuriyBO(){
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		HttpSession session = request.getSession();
		userAuthentication = (UserAuthentication)session.getAttribute("userAuthentication");
	}
	
	public UserAuthentication getUserAuthentication() {
		return userAuthentication;
	}
	public void setUserAuthentication(UserAuthentication userAuthentication) {
		this.userAuthentication = userAuthentication;
	}
}
