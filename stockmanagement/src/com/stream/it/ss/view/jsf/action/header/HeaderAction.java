//package com.stream.it.ss.view.jsf.action.header;
//
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.apache.log4j.Logger;
//import org.primefaces.component.menuitem.MenuItem;
//import org.primefaces.component.submenu.Submenu;
//import org.primefaces.model.DefaultMenuModel;
//
//import com.stream.it.ss.base.databo.UserAuthentication;
//import com.stream.it.ss.hibernate.dao.FunctionsDAO;
//import com.stream.it.ss.hibernate.domain.Functions;
//import com.stream.it.ss.hibernate.inquiry.GroupMenuInquiry;
//import com.stream.it.ss.hibernate.inquiry.MenuInquery;
//import com.stream.it.ss.hibernate.inquiry.SubMenuInquiry;
//
//import com.stream.it.ss.service.webcustom.setting.RenderMenuService;
//import com.stream.it.ss.utils.Context;
//import com.stream.it.ss.view.jsf.base.BaseAction;
//import com.stream.it.ss.view.jsf.base.DisplayMessages;
//import com.stream.it.ss.view.jsf.base.FacesAccessor;
//
//
//
//@SuppressWarnings("serial")
//@ManagedBean
//@SessionScoped
//public class HeaderAction extends BaseAction {	
//	private final Logger log = Logger.getLogger(getClass());
//	
//	private RenderMenuService renderMenuService;
//	
//	private FunctionsDAO functionsDAO;
//	
//	private List<GroupMenuInquiry> dataMenuList;
//	private List<SubMenuInquiry> dataSubMenuList;
//	private DefaultMenuModel  menuBarModel;
//	private String username = "";
//	private String department = "";
//	private String booth = "";
//	private String branchname = "";
//	private String displayDepartment = "";
//	private String role;
//	private String functions[];
//	private String clientIPAddress;
//	
//	public HeaderAction(){
//		this.renderMenuService = (RenderMenuService)Context.getInstance().applicationContext.getBean("renderMenuService");
//		this.functionsDAO = (FunctionsDAO)Context.getInstance().applicationContext.getBean("functionsDAO");
//		
//		FacesContext facesContext = FacesContext.getCurrentInstance(); 
//		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
//			 
//		HttpSession session = request.getSession();
////		role = session.getAttribute("Role").toString()+",";
////	    username =  session.getAttribute("UserName").toString();
////	    branchname = session.getAttribute("Branch").toString();
////	    department =  session.getAttribute("Department").toString();
////		clientIPAddress = session.getAttribute("Ip").toString();			 
////		functions = new String(role).split(",");
//		
//		role = "H2H-FC-01, H2H-FC-02, H2H-FC-03, H2H-FC-04, H2H-FC-05, H2H-FC-06, H2H-FC-07, H2H-FC-08, H2H-FC-09, H2H-FC-10, H2H-FC-16, H2H-FC-17, H2H-M01, H2H-M02, H2H-M03, H2H-FC-12, H2H-FC-14, H2H-FC-18, H2H-FC-19, H2H-FC-20, H2H-M04, H2H-FC-11";
//		role = "H2H-FC-01, H2H-FC-02, H2H-FC-03, H2H-FC-04, H2H-FC-05, H2H-FC-07, H2H-FC-09, H2H-FC-10, H2H-FC-11, H2H-FC-26, H2H-FC-27, H2H-FC-28, H2H-FC-29, H2H-FC-30, H2H-FC-31, H2H-M01, H2H-M03, H2H-FC-22, H2H-FC-23, H2H-FC-32, H2H-FC-33, H2H-FC-37, H2H-FC-38";
//		functions = new String(role).split(",");
//	    username = "auth01";
//	    branchname = "";
//	    department =  "";
//		clientIPAddress = "127.0.0.1";
//		
//		try {
//			if(department != "") displayDepartment = "Department :" + department;
//			if(branchname != "") displayDepartment = "Branch :" + branchname;
//		    
//			//*** Session User Logon ***//
//			UserAuthentication userAuthentication = new UserAuthentication();
//			userAuthentication.setUserLogin(username);
//			userAuthentication.setDepartmentDesc(department);
//			userAuthentication.setIpAddress(clientIPAddress);
//			userAuthentication.setRoleDesc(role);
//			session.setAttribute("userAuthentication", userAuthentication);
//			
//			this.genarateMenuByFunctionList(functions);
////			this.genarateMenuByUserGroup(role);
//			
//		}catch(Exception e){
//			e.printStackTrace();
//			DisplayMessages.showErrorMessage(e.getMessage());
//		}finally{
//			//**** LOGOUT Menu *****//
//			MenuItem  menuItem = new MenuItem ();  
//			menuItem.setValue("Log out");
//			menuItem.setActionExpression(FacesAccessor.createMethodExpression("#{headerAction.logout}", String.class, new Class[] {}));
//			menuItem.setIcon("ui-icon-power");
//			menuItem.setAjax(false);
//			menuBarModel.addMenuItem(menuItem);		
//		}
//		
//	}
//	
//	private void genarateMenuByFunctionList(String[] functionFromUCAList) throws Exception{
//		log.info("genarateMenuByFunctionList....");
//		
//		menuBarModel = new DefaultMenuModel();
//		
//		List<MenuInquery> menuInqueryList = renderMenuService.renderMenuByFunction(functionFromUCAList);
//		
//		log.info("Gennarate Menu and Submenu....");
//		
//		for(MenuInquery menuInquery: menuInqueryList){
//			String menuId	= menuInquery.getMenuId();
//			String[] menuSourceId = menuInquery.getMenuSourceId().split(",");
//			
//			List checkFunctionList = Arrays.asList(menuSourceId);
//			
//			Submenu submenu = new Submenu();   	 
//			submenu.setLabel(menuInquery.getMenuName());
//			
//			for(int i=0; i<functionFromUCAList.length; i++){
//				
//				if(functionFromUCAList[i]!=null){
//					String functionFromUCAL = functionFromUCAList[i].trim();
//					log.info("Functions By UCA.."+functionFromUCAL);
//					
//					if(Arrays.asList(menuSourceId).contains(functionFromUCAL) && !menuId.equals(functionFromUCAList[i])){
//						log.info("Add Sub Menu "+menuId+"  :  "+functionFromUCAL);
//
//						Functions functions = functionsDAO.findById(functionFromUCAL);
//						if(functions!=null){
//							MenuItem item = new MenuItem();  
//							item.setValue(functions.getFuncName());
//							item.setActionExpression(FacesAccessor.createMethodExpression("#{"+functions.getFuncUrl()+"}", String.class, new Class[] {}));
//							item.setAjax(false);
//							
//							submenu.getChildren().add(item);
//							functionFromUCAList[i] = null;
//						}
//					}else if(menuId.equals(functionFromUCAL)){
//						functionFromUCAList[i] = null;
//					}
//				}		
//			}
//			
//			menuBarModel.addSubmenu(submenu);				
//		}
//		
//		log.info("Gennarate Menu and Submenu....");
//		for(int i=0;i<functionFromUCAList.length; i++){
//			if(functionFromUCAList[i]!=null){
//				log.info("functionFromUCA : "+functionFromUCAList[i]);
//				
//				Functions functions = functionsDAO.findById(functionFromUCAList[i].trim());
//				if(functions!=null){
//					MenuItem item = new MenuItem();  
//					item.setValue(functions.getFuncName());
//					item.setActionExpression(FacesAccessor.createMethodExpression("#{"+functions.getFuncUrl()+"}", String.class, new Class[] {}));
//					item.setAjax(false);
//					
//					menuBarModel.addMenuItem(item);
//				}
//			}
//			
//			
//		}
//
//		
//	}
//	
//	private void genarateMenuByUserGroup(String role) throws Exception{
//		menuBarModel = new DefaultMenuModel();
//		dataMenuList = renderMenuService.renderMenuByGroup(role);
//     	
//		System.out.println("User Group : "+role);
//		
//		for(int i = 0 ; i< dataMenuList.size() ; i++){
//			Submenu submenu = new Submenu();   	 
//			submenu.setLabel(dataMenuList.get(i).getMenuName());
//		    
//			dataSubMenuList = renderMenuService.renderSubMenuByGroup(dataMenuList.get(i).getMenuSourceId());
//		     	
//			if(dataMenuList.get(i).getMenuSourceId().toString().startsWith("[")){
//		    	for(int x = 0 ; x< dataSubMenuList.size() ; x++){
//					MenuItem item = new MenuItem();  
//					item.setValue(dataSubMenuList.get(x).getFuncName());
//					item.setActionExpression(FacesAccessor.createMethodExpression("#{"+dataSubMenuList.get(x).getFuncUrl()+"}", String.class, new Class[] {}));
//					item.setAjax(false);
//					
//					menuBarModel.addMenuItem(item);				    
//				}
//			
//			}else{
//			
//				for(int x = 0 ; x< dataSubMenuList.size() ; x++){
//					MenuItem item = new MenuItem();  
//					item.setValue(dataSubMenuList.get(x).getFuncName());
//					item.setActionExpression(FacesAccessor.createMethodExpression("#{"+dataSubMenuList.get(x).getFuncUrl()+"}", String.class, new Class[] {}));
//					item.setAjax(false);
//					
//					submenu.getChildren().add(item);
//				}
//				
//				menuBarModel.addSubmenu(submenu);
//			}			 
//		}
//		
//		
//	}
//	
//	
//	public String logout() throws IOException{
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();   
//
//        return "logout";
//    }
//
//	public DefaultMenuModel getMenuBarModel() {
//		return menuBarModel;
//	}
//
//	public void setMenuBarModel(DefaultMenuModel menuBarModel) {
//		this.menuBarModel = menuBarModel;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public List<GroupMenuInquiry> getDataMenuList() {
//		return dataMenuList;
//	}
//
//	public void setDataMenuList(List<GroupMenuInquiry> dataMenuList) {
//		this.dataMenuList = dataMenuList;
//	}
//
//	public List<SubMenuInquiry> getDataSubMenuList() {
//		return dataSubMenuList;
//	}
//
//	public void setDataSubMenuList(List<SubMenuInquiry> dataSubMenuList) {
//		this.dataSubMenuList = dataSubMenuList;
//	}
//
//	public RenderMenuService getRenderMenuService() {
//		return renderMenuService;
//	}
//
//	public void setRenderMenuService(RenderMenuService renderMenuService) {
//		this.renderMenuService = renderMenuService;
//	}
//
//	public String getBranchname() {
//		return branchname;
//	}
//
//	public void setBranchname(String branchname) {
//		this.branchname = branchname;
//	}
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//
//	public String getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(String department) {
//		this.department = department;
//	}
//
//	public String getBooth() {
//		return booth;
//	}
//
//	public void setBooth(String booth) {
//		this.booth = booth;
//	}
//
//	public String getDisplayDepartment() {
//		return displayDepartment;
//	}
//	
//	public void setDisplayDepartment(String displayDepartment) {
//		this.displayDepartment = displayDepartment;
//	} 
//		
//}
