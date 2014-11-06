package com.stream.it.ss.view.jsf.action.authentification;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import com.stream.it.ss.base.databo.UserAuthentication;
import com.stream.it.ss.hibernate.dao.FunctionsDAO;
import com.stream.it.ss.hibernate.dao.UsersProfileDAO;
import com.stream.it.ss.hibernate.domain.Functions;
import com.stream.it.ss.hibernate.domain.UsersProfile;
import com.stream.it.ss.hibernate.inquiry.GroupMenuInquiry;
import com.stream.it.ss.hibernate.inquiry.MenuInquery;
import com.stream.it.ss.hibernate.inquiry.SubMenuInquiry;
import com.stream.it.ss.service.webcustom.setting.RenderMenuService;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.base.FacesAccessor;

@ManagedBean
@SessionScoped
public class AuthenficationAction extends BaseAction{
	//**** AUTHNFICATION ****//
	@ManagedProperty(value="#{usersProfileDAO}")
	private UsersProfileDAO usersProfileDAO;
	
	private String user;
	private String password;
	private String errorMessage;
	private UserAuthentication userAuthentication;
	
	//*** GENARATE MENU ***//
	@ManagedProperty(value = "#{renderMenuService}")
	private RenderMenuService renderMenuService;	

	@ManagedProperty(value = "#{functionsDAO}")
	private FunctionsDAO functionsDAO;	
	
	private List<GroupMenuInquiry> dataMenuList;
	private List<SubMenuInquiry> dataSubMenuList;
	private MenuModel  menuBarModel;
	
	
	public String doLogin() throws Exception{
		UsersProfile userAuthfication = usersProfileDAO.authentificationUserLogon(user, password);
		
		if(userAuthfication == null){
			user = null;
			password = null;
			errorMessage = "Please Check User and Password";
			getHttpServletRequest().setAttribute("errorMessage", errorMessage);
			
			DisplayMessages.addErrorMessage("Login Fail!!","Please Check User and Password");
			
			return null;
		
		}else if(userAuthfication.getStatus().equalsIgnoreCase("disable")){
			user = null;
			password = null;
			errorMessage = "User is Disable";

			DisplayMessages.addErrorMessage("Login Fail!!","User is Disable");

			return null;
			
		}else if(userAuthfication.getStatus().equalsIgnoreCase("enable")){
			
			userAuthentication = new UserAuthentication();
			userAuthentication.setUserLogin(userAuthfication.getUserId());
			userAuthentication.setUserAccountName(userAuthfication.getFirstName()+" "+userAuthfication.getLastName());
			userAuthentication.setDepartmentDesc(userAuthfication.getDepartment());
			userAuthentication.setIpAddress(getHttpServletRequest().getRemoteAddr());
			userAuthentication.setFunctionsCanUse(userAuthfication.getFunctionsUse());
			
			getHttpServletRequest().getSession().setAttribute("userAuthentication", userAuthentication);
			
			if(userAuthentication.getFunctionsCanUse()!=null && !userAuthentication.getFunctionsCanUse().equals("")){
				String[]functions = userAuthentication.getFunctionsCanUse().split(",");
				this.genarateMenuByFunctionList(functions);
			}
			
			userAuthfication.setLastLogin(new Date());
			usersProfileDAO.update(userAuthfication);
			
			return "index";
			
		}
		
		return null;
	}
	
	public String doLogout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();   
		
		return "login";
	}
	
	
	private void genarateMenuByFunctionList(String[] functionFromUCAList) throws Exception{
		menuBarModel = new DefaultMenuModel();
		
		List<MenuInquery> menuInqueryList = renderMenuService.renderMenuByFunction(functionFromUCAList);
		
		for(MenuInquery menuInquery: menuInqueryList){
			String menuId	= menuInquery.getMenuId();
			String[] menuSourceId = menuInquery.getMenuSourceId().split(",");
			
			List checkFunctionList = Arrays.asList(menuSourceId);
			
			Submenu submenu = new Submenu();   	 
			submenu.setLabel(menuInquery.getMenuName());
			
			for(int i=0; i<functionFromUCAList.length; i++){
				
				if(functionFromUCAList[i]!=null){
					String functionFromUCAL = functionFromUCAList[i].trim();
					
					if(Arrays.asList(menuSourceId).contains(functionFromUCAL) && !menuId.equals(functionFromUCAList[i])){
						Functions functions = functionsDAO.findById(functionFromUCAL);
						
						if(functions!=null){
							MenuItem item = new MenuItem();  
							item.setValue(functions.getFuncName());
							item.setActionExpression(FacesAccessor.createMethodExpression("#{"+functions.getFuncUrl()+"}", String.class, new Class[] {}));
							item.setAjax(false);
							
							submenu.getChildren().add(item);
							functionFromUCAList[i] = null;
						}
					}else if(menuId.equals(functionFromUCAL)){
						functionFromUCAList[i] = null;
					}
				}		
			}
			
			menuBarModel.addSubmenu(submenu);				
		}
		
		for(int i=0;i<functionFromUCAList.length; i++){
			if(functionFromUCAList[i]!=null){
				Functions functions = functionsDAO.findById(functionFromUCAList[i].trim());
				if(functions!=null){
					MenuItem item = new MenuItem();  
					item.setValue(functions.getFuncName());
					item.setActionExpression(FacesAccessor.createMethodExpression("#{"+functions.getFuncUrl()+"}", String.class, new Class[] {}));
					item.setAjax(false);
					
					menuBarModel.addMenuItem(item);
				}
			}	
		}
	}
	
	public void setUsersProfileDAO(UsersProfileDAO usersProfileDAO) {
		this.usersProfileDAO = usersProfileDAO;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public UserAuthentication getUserAuthentication() {
		return userAuthentication;
	}
	public void setUserAuthentication(UserAuthentication userAuthentication) {
		this.userAuthentication = userAuthentication;
	}
	public RenderMenuService getRenderMenuService() {
		return renderMenuService;
	}
	public void setRenderMenuService(RenderMenuService renderMenuService) {
		this.renderMenuService = renderMenuService;
	}
	public FunctionsDAO getFunctionsDAO() {
		return functionsDAO;
	}
	public void setFunctionsDAO(FunctionsDAO functionsDAO) {
		this.functionsDAO = functionsDAO;
	}
	public List<GroupMenuInquiry> getDataMenuList() {
		return dataMenuList;
	}
	public void setDataMenuList(List<GroupMenuInquiry> dataMenuList) {
		this.dataMenuList = dataMenuList;
	}
	public List<SubMenuInquiry> getDataSubMenuList() {
		return dataSubMenuList;
	}
	public void setDataSubMenuList(List<SubMenuInquiry> dataSubMenuList) {
		this.dataSubMenuList = dataSubMenuList;
	}
	public UsersProfileDAO getUsersProfileDAO() {
		return usersProfileDAO;
	}
	public MenuModel getMenuBarModel() {
		return menuBarModel;
	}
	public void setMenuBarModel(MenuModel menuBarModel) {
		this.menuBarModel = menuBarModel;
	}
}
