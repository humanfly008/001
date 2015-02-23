package com.stream.it.ss.view.jsf.action.setting.users;



import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.stream.it.ss.hibernate.dao.UsersProfileDAO;
import com.stream.it.ss.hibernate.domain.UsersProfile;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.setting.users.UserForm;


@ViewScoped
@ManagedBean
public class ChangePasswordAction extends BaseAction{
	@ManagedProperty(value="#{usersProfileDAO}")
	private UsersProfileDAO usersProfileDAO;
	
	private UserForm userFormBO;
	
	private String newPassword;	
	
	public ChangePasswordAction(){}
	
	
	@PostConstruct
	public void init(){
		try{
			newPassword = "";
			userFormBO = new UserForm();
			
			UsersProfile usersProfile = usersProfileDAO.findByUserId(userFormBO.getSecuriyBO().getUserAuthentication().getUserLogin());
			userFormBO.setUserId(usersProfile.getUserId());
			userFormBO.setFirstName(usersProfile.getFirstName());
			userFormBO.setLastName(usersProfile.getLastName());

		}catch(Exception e){
			DisplayMessages.showErrorMessage("Display Exception");   	
		}
    }
			
	public String doChangePassword()throws Exception{
		UsersProfile usersProfile = usersProfileDAO.findByUserId(userFormBO.getSecuriyBO().getUserAuthentication().getUserLogin());
		usersProfile.setPassword(newPassword);
		
		usersProfileDAO.update(usersProfile);
		
		return "index";
	}
	
//	public String changePasswordPage() throws Exception{
//		
//		return "change.password";
//	}


	public UserForm getUserFormBO() {
		return userFormBO;
	}
	public void setUserFormBO(UserForm userFormBO) {
		this.userFormBO = userFormBO;
	}
	public void setUsersProfileDAO(UsersProfileDAO usersProfileDAO) {
		this.usersProfileDAO = usersProfileDAO;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
