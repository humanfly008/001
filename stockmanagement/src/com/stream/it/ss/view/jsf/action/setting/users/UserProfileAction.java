package com.stream.it.ss.view.jsf.action.setting.users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DualListModel;

import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.hibernate.inquiry.Dropdown;
import com.stream.it.ss.hibernate.inquiry.MenuInquery;
import com.stream.it.ss.service.combo.FunctionMenuComboDropdownService;
import com.stream.it.ss.service.combo.MenuComboDropdownService;
import com.stream.it.ss.service.webcustom.setting.UserProfileService;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.setting.users.UserForm;
import com.stream.it.ss.view.jsf.form.setting.users.UserSearchForm;


@SessionScoped
@ManagedBean
public class UserProfileAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	//****** SERVICE *********//
	@ManagedProperty(value="#{userProfileService}")
    private UserProfileService userProfileService;
	
	@ManagedProperty(value="#{functionMenuComboDropdownService}")
	private FunctionMenuComboDropdownService functionMenuComboDropdownService;
	
	@ManagedProperty(value="#{menuComboDropdownService}")
	private MenuComboDropdownService menuComboDropdownService;
	
	
	//****** FORM *******//
	private UserForm userFormBO;
	private UserSearchForm searchFormBO;
	private List<?> transactionList;
	private DualListModel<?> functionsList = new DualListModel<Dropdown>(new ArrayList<Dropdown>(), new ArrayList<Dropdown>());
	private DualListModel<?> menusList = new DualListModel<Dropdown>(new ArrayList<Dropdown>(), new ArrayList<Dropdown>());
	
	
	public void doListUserProfile()throws Exception{
		transactionList = userProfileService.listTransaction(searchFormBO);
		DisplayMessages.showMessage("Search", searchFormBO);   	
	}
	
	public void doCreateUserProfile() throws Exception{
		userFormBO.setFunctionSelected((List<?>) functionsList.getTarget());
		userFormBO.setMenuSelected((List<?>) menusList.getTarget());

		ResultBO resultBO = userProfileService.createUserProfile(userFormBO);
		DisplayMessages.showMessage("Create", resultBO);   	
		transactionList = userProfileService.listTransaction(searchFormBO);		
		
		functionsList = new DualListModel<Dropdown>(new ArrayList<Dropdown>(), new ArrayList<Dropdown>());
		menusList = new DualListModel<Dropdown>(new ArrayList<Dropdown>(), new ArrayList<Dropdown>());
	}

	public void doUpdateUserProfile() throws Exception{
		userFormBO.setFunctionSelected((List<?>) functionsList.getTarget());
		userFormBO.setMenuSelected((List<?>) menusList.getTarget());
		
		ResultBO resultBO = userProfileService.updateUserProfile(userFormBO);
		DisplayMessages.showMessage("Create", resultBO);   	
		transactionList = userProfileService.listTransaction(searchFormBO);
		
		functionsList = new DualListModel<Dropdown>(new ArrayList<Dropdown>(), new ArrayList<Dropdown>());
		menusList = new DualListModel<Dropdown>(new ArrayList<Dropdown>(), new ArrayList<Dropdown>());
	}
	
	public void doDeleteUserProfile() throws Exception{
		String[]checkDelete = getHttpServletRequest().getParameterValues("checkDelete");
		ResultBO resultBO = userProfileService.deleteUserProfile(checkDelete);
		DisplayMessages.showMessage("Delete ", resultBO); 
		
		transactionList = userProfileService.listTransaction(searchFormBO);
	}
	
	private String userInUse;
	public void verifyUserId()throws Exception{
		userInUse = "";
		String userId = userFormBO.getUserId().trim();
		ResultBO resultBO = userProfileService.verifyUserId(userId);
		
		if(resultBO.getException()!=null){
			userInUse = "User Id is Used";
		}		
	}
	
	//**** PAGE NAVIGATOR ****//
	public String listPage() throws Exception{
		searchFormBO = new UserSearchForm();
		transactionList = userProfileService.listTransaction(searchFormBO);
		
		return "user-list";
	}
	
	public String addPage() throws Exception{
		userFormBO = new UserForm();
		
		List<?> functionList = functionMenuComboDropdownService.listFunctionMenuAll();	
		List<Object> functionSource = (Arrays.asList(functionList.toArray()));
	    List<Object> functionTarget = new ArrayList<Object>();
	    functionsList = new DualListModel<Object>(functionSource, functionTarget);

	    List<?> munuList = menuComboDropdownService.listAll();
	    List<Object> menuSource = (Arrays.asList(munuList.toArray()));
	    List<Object> menuTarget = new ArrayList<Object>();
	    menusList = new DualListModel<Object>(menuSource, menuTarget);
	    
		return "user-add";
	}
	
	public String editPage() throws Exception{
		String id = getHttpServletRequest().getParameter("id");
		String userd = getHttpServletRequest().getParameter("userId");
		userFormBO = (UserForm)userProfileService.findUserProfile(id, userd);

		if(userFormBO.getFunctionUsed()!=null && !userFormBO.getFunctionUsed().equals("")){
			List<Dropdown> menuSelected = menuComboDropdownService.listInMenuId(userFormBO.getFunctionUsed());
			List<Dropdown> menuNotSelected = menuComboDropdownService.listNotInMenuId(userFormBO.getFunctionUsed());
			List<Object> menuSource = (Arrays.asList(menuNotSelected.toArray()));
		    List<Object> menuTarget = (Arrays.asList(menuSelected.toArray()));
		    menusList = new DualListModel<Object>(menuSource, menuTarget);
		    
			List<Dropdown> functionSelected = functionMenuComboDropdownService.listInFunctionId(userFormBO.getFunctionUsed());
			List<Dropdown> functionNotSelected = functionMenuComboDropdownService.listNotInfunctionId(userFormBO.getFunctionUsed());
			List<Object> functionSource = (Arrays.asList(functionNotSelected.toArray()));
		    List<Object> functionTarget = (Arrays.asList(functionSelected.toArray()));
		    functionsList = new DualListModel<Object>(functionSource, functionTarget);
			
		}else{
			List<?> functionList = functionMenuComboDropdownService.listFunctionMenuAll();	
			List<Object> functionSource = (Arrays.asList(functionList.toArray()));
		    List<Object> functionTarget = new ArrayList<Object>();
		    functionsList = new DualListModel<Object>(functionSource, functionTarget);

		    List<?> munuList = menuComboDropdownService.listAll();
		    List<Object> menuSource = (Arrays.asList(munuList.toArray()));
		    List<Object> menuTarget = new ArrayList<Object>();
		    menusList = new DualListModel<Object>(menuSource, menuTarget);
		
		}
	    
		return "user-edit";
	}

	
	//***** SETTER,GETTER ******//
	public UserForm getUserFormBO() {
		return userFormBO;
	}
	public void setUserFormBO(UserForm userFormBO) {
		this.userFormBO = userFormBO;
	}
	public UserSearchForm getSearchFormBO() {
		return searchFormBO;
	}
	public void setSearchFormBO(UserSearchForm searchFormBO) {
		this.searchFormBO = searchFormBO;
	}
	public List<?> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<MenuInquery> transactionList) {
		this.transactionList = transactionList;
	}
	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}
	public String getUserInUse() {
		return userInUse;
	}
	public void setUserInUse(String userInUse) {
		this.userInUse = userInUse;
	}
	public DualListModel<?> getFunctionsList() {
		return functionsList;
	}
	public void setFunctionsList(DualListModel<?> functionsList) {
		this.functionsList = functionsList;
	}
	public DualListModel<?> getMenusList() {
		return menusList;
	}
	public void setMenusList(DualListModel<?> menusList) {
		this.menusList = menusList;
	}
	public UserProfileService getUserProfileService() {
		return userProfileService;
	}
	public void setFunctionMenuComboDropdownService(
			FunctionMenuComboDropdownService functionMenuComboDropdownService) {
		this.functionMenuComboDropdownService = functionMenuComboDropdownService;
	}
	public void setMenuComboDropdownService(
			MenuComboDropdownService menuComboDropdownService) {
		this.menuComboDropdownService = menuComboDropdownService;
	}
}


