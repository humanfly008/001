package com.stream.it.ss.view.jsf.action.setting.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.DualListModel;

import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.service.combo.FunctionMenuComboDropdownService;
import com.stream.it.ss.service.findservice.MenuFindService;
import com.stream.it.ss.service.webcustom.setting.FunctionsService;
import com.stream.it.ss.service.webcustom.setting.MenuService;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.setting.menu.MenuForm;
import com.stream.it.ss.view.jsf.form.setting.menu.MenuSearchForm;


@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class MenuAction extends BaseAction{
	//*** service *****//
	@ManagedProperty(value="#{menuService}")
	private MenuService menuService;
	
	@ManagedProperty(value="#{menuFindService}")
	private MenuFindService menuFindService;
	
	@ManagedProperty(value="#{functionsService}")
	private FunctionsService functionsService;
	
	@ManagedProperty(value="#{functionMenuComboDropdownService}")
	private FunctionMenuComboDropdownService functionsMenuService;
	
	//***** form ******//
	private List<?> dataList;
	private MenuForm formBean;
	private MenuSearchForm searchForm;
	private DualListModel<?> functions; 
	private DefaultMenuModel menu;
	private List<?> comboFunctionMenu;
	private String menuName;
	
	public MenuAction() {
		formBean = new MenuForm();
		searchForm = new MenuSearchForm();
	}
	
	//******** Action **********//
	public String listPage()throws Exception{
		dataList =  menuService.searchList(searchForm);
		menuName = null;
		
		return "menu-list";
	}

	public String addPage()throws Exception{
		menu = new DefaultMenuModel();
		this.refreshDualList();
		formBean = new MenuForm();
		formBean.setEventFunc("none");
		String maxId = menuService.findMenuId();
		formBean.setMenuId(maxId);
		comboFunctionMenu = functionsMenuService.listFunctionMenuAll();
		
		return "menu-add";
	}
	
	public String editPage()throws Exception{
		menu = new DefaultMenuModel();
		String menuId = getHttpServletRequest().getParameter("menuId");
		formBean = (MenuForm) menuService.findMenu(menuId);
		comboFunctionMenu = functionsMenuService.listFunctionMenuAll();
		List<?> dataFind = menuFindService.transformEditFunctionList(formBean.getMenuSourceId());
		List<?> funcListR = functionsService.findFunctionsInMapping(dataFind);
	    List<?> funcListL = functionsService.findFunctionsNotInMapping(dataFind);
	    List<Object> source = Arrays.asList(funcListL.toArray());
	    List<Object> target = Arrays.asList(funcListR.toArray());
	    functions = new DualListModel<Object>(source, target);
	    menuName = formBean.getMenuName();
	    
		return "menu-edit";
	}
	

	public String doListMenu() throws Exception{
		
		dataList = menuService.searchList(searchForm);
		DisplayMessages.showMessage("Search", searchForm); 
		
		return "menu-list";
	
	}
		
	public String doAddMenu()throws Exception{
		boolean checkmenuName = menuFindService.checkMenuName(formBean.getMenuName());
		if(checkmenuName){
		formBean.setFunctionSelected((List<?>) functions.getTarget());
		menuService.createMenu(formBean);
		DisplayMessages.showMessage("Create ", formBean); 
		
		return this.listPage();
		}

		this.refreshDualList();
		DisplayMessages.showErrorMessage("the menu name is not available!"); 
		return "";
	}
	
	public String doEditMenu()throws Exception{
		if(menuName.equals(formBean.getMenuName())){
			formBean.setFunctionSelected((List<?>) functions.getTarget());
			menuService.updateMenu(formBean);
			DisplayMessages.showMessage("Update ", formBean); 
			
			return this.listPage();	
		}else{
			boolean checkmenuName = menuFindService.checkMenuName(formBean.getMenuName());
			if(checkmenuName){
					formBean.setFunctionSelected((List<?>) functions.getTarget());
					menuService.updateMenu(formBean);
					DisplayMessages.showMessage("Update ", formBean); 
					
					return this.listPage();	
			}else{
				List<?> dataFind = menuFindService.transformEditFunctionList(formBean.getMenuSourceId());
				List<?> funcListR = functionsService.findFunctionsInMapping(dataFind);
			    List<?> funcListL = functionsService.findFunctionsNotInMapping(dataFind);
				List<Object> source = Arrays.asList(funcListL.toArray());
			    List<Object> target = Arrays.asList(funcListR.toArray());
			    functions = new DualListModel<Object>(source, target);
				DisplayMessages.showErrorMessage("the menu name is not available!"); 
				return "";
			}
		}
			
	}

	public String doDeleteMenu() throws Exception{
		String[]checkDelete = getHttpServletRequest().getParameterValues("checkDelete");
		
		@SuppressWarnings("unused")
		ResultBO resultBO = menuService.deleteMenu(checkDelete);
		
		return this.listPage();
	}
	
	private void refreshDualList() throws Exception{
	    List<Object> source = (Arrays.asList(this.functionList().toArray()));
	    List<Object> target = new ArrayList<Object>();

	    functions = new DualListModel<Object>(source, target);
	    
	}
	
	@SuppressWarnings("unused")
	public List<?> functionList() throws Exception{
		List<?> dataFind = menuFindService.transformFunctionId(); 
		List<?> funcList = functionsService.listAllFunctions();	
		
		return funcList;
	}

	@SuppressWarnings("rawtypes")
	public void processMenu(ActionEvent actionEvent) throws Exception{
		String addFunc = formBean.getEventFunc();
		List<?> tagetList = ((List<?>) functions.getTarget());
		MenuItem item = new MenuItem();
		Submenu submenu = new Submenu();
		
		try{
			String sourceId = "";
			String sourceDesc = "";
			menu = new DefaultMenuModel();

			if(!addFunc.equals("none")){
				tagetList = new ArrayList();
			}
			
			if(tagetList.isEmpty()){
				item = new MenuItem();
				item.setValue(formBean.getMenuName());
		        item.setId(formBean.getMenuId());
		       
		        menu.addMenuItem(item);
			}else{
				submenu = new Submenu();
		        submenu.setLabel(formBean.getMenuName());
		        
					for(Object funcList : tagetList){
						String[] splitfunc  = ((String) funcList).split(",");
						sourceId= splitfunc[0].trim().toString();
						sourceDesc= splitfunc[1].trim().toString();
						
						item = new MenuItem();
				        item.setValue(sourceDesc);
				        item.setId(sourceId);
				        submenu.getChildren().add(item);
					}
					menu.addSubmenu(submenu);		
			}
	        
			
				this.refreshDualList();
				
		}catch(Exception e){
			e.printStackTrace();
		}
    }
	
	public void menuNameValidator() throws Exception {
		String menu_name = formBean.getMenuName();
			boolean checkmenuName = menuFindService.checkMenuName(menu_name);
			if(menuName != null){
				if(!checkmenuName && !menuName.equalsIgnoreCase(formBean.getMenuName())){
					DisplayMessages.showErrorMessage("the menu name is not available!"); 
		        }
			}else{
				if(!checkmenuName){
					DisplayMessages.showErrorMessage("the menu name is not available!"); 
		        }
			}
			
	}
	
	//*********** getter setter ***********//
	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public MenuForm getFormBean() {
		return formBean;
	}

	public void setFormBean(MenuForm formBean) {
		this.formBean = formBean;
	}

	public MenuSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(MenuSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	public void setMenuFindService(MenuFindService menuFindService) {
		this.menuFindService = menuFindService;
	}

	public DualListModel<?> getFunctions() {
		return functions;
	}

	public void setFunctions(DualListModel<?> functions) {
		this.functions = functions;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	public DefaultMenuModel getMenu() {
		return menu;
	}

	public void setMenu(DefaultMenuModel menu) {
		this.menu = menu;
	}

	public List<?> getComboFunctionMenu() {
		return comboFunctionMenu;
	}

	public void setComboFunctionMenu(List<?> comboFunctionMenu) {
		this.comboFunctionMenu = comboFunctionMenu;
	}

	public void setFunctionsMenuService(
			FunctionMenuComboDropdownService functionsMenuService) {
		this.functionsMenuService = functionsMenuService;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	
}
