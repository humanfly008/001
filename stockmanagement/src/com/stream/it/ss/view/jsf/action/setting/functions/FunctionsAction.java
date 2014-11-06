package com.stream.it.ss.view.jsf.action.setting.functions;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.service.findservice.FunctionFindService;
import com.stream.it.ss.service.webcustom.setting.FunctionsService;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.setting.functions.FunctionsForm;
import com.stream.it.ss.view.jsf.form.setting.functions.FunctionsSearchForm;


@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class FunctionsAction extends BaseAction{
	//****** service *********//
	@ManagedProperty(value="#{functionsService}")
    private FunctionsService functionsService;
	
	@ManagedProperty(value="#{functionFindService}")
    private FunctionFindService functionFindService;

	//****** form ******//
	private List<?> functionsDataList;
	private FunctionsForm functionsForm;
	private FunctionsSearchForm functionsSearchForm;
	
	public FunctionsAction(){
		functionsForm = new FunctionsForm();
		functionsSearchForm = new FunctionsSearchForm();
	}
	
	//****** action ******//	
	public String doListFunctions() throws Exception{
		functionsDataList = functionsService.listFunctions(functionsSearchForm);
		DisplayMessages.showMessage("Search", functionsSearchForm);
		
		return "functions-list";
	}
	
	public String doCreateFunction()throws Exception{	
		functionsService.createFunctions(functionsForm);
		DisplayMessages.showMessage("Create ", functionsForm); 
		
		return listPage();
	}

	public String doUpdateFunction()throws Exception{	
		functionsService.updateFunctions(functionsForm);
		DisplayMessages.showMessage("Update ", functionsForm);      
			
		return listPage();
	}
	
	public String doDeleteFunction() throws Exception{
		String[]checkDelete = getHttpServletRequest().getParameterValues("checkDelete");	
		for(int i=0;i<checkDelete.length;i++){
			boolean checkMenu = functionFindService.checkMenuToDelete(checkDelete[i]);
			if(checkMenu){
				ResultBO resultBO = functionsService.deleteFunctions(checkDelete[i]);
				DisplayMessages.showMessage("Delete ", resultBO); 
			}else{
				DisplayMessages.showErrorMessage("Function " + checkDelete[i] +" can't delete , this menu are using");
			}
		}
		return this.listPage();
	}

	//*** page navigator ****//
	public String listPage()throws Exception{
		functionsSearchForm = new FunctionsSearchForm();	
		functionsDataList = functionsService.listFunctions(functionsSearchForm);
		
		return "functions-list";
	}
	
	public String addPage()throws Exception{
		functionsDataList = new ArrayList();
		functionsSearchForm = new FunctionsSearchForm();	
		functionsForm = new FunctionsForm();
		functionsForm.setFuncId(functionsService.findMaxFnId());
		
		return "functions-add";
	}
	
	public String editPage()throws Exception{
		String funcId = getHttpServletRequest().getParameter("funcId");		
		functionsForm = (FunctionsForm) functionsService.findFunctions(funcId);
		
		return "functions-edit";
	}
	
	//***** ajax *****//
	public void checkDupFnName() throws Exception{
		if(functionsForm.getFuncName()!=null && !functionsForm.getFuncName().trim().equals("")){
			if(functionsService.findFnName(functionsForm.getFuncName())){
				DisplayMessages.showErrorMessage("the FunctionsName is not available!");
			}else{
				DisplayMessages.showInfoMessage("the FunctionsName is available");
			}
		}
	}
	
	//****** get set ******//
	public List<?> getFunctionsDataList() {
		return functionsDataList;
	}

	public void setFunctionsDataList(List<?> functionsDataList) {
		this.functionsDataList = functionsDataList;
	}

	public FunctionsForm getFunctionsForm() {
		return functionsForm;
	}

	public void setFunctionsForm(FunctionsForm functionsForm) {
		this.functionsForm = functionsForm;
	}

	public FunctionsSearchForm getFunctionsSearchForm() {
		return functionsSearchForm;
	}

	public void setFunctionsSearchForm(FunctionsSearchForm functionsForm) {
		this.functionsSearchForm = functionsForm;
	}

	public void setFunctionsService(FunctionsService functionsService) {
		this.functionsService = functionsService;
	}

	public void setFunctionFindService(FunctionFindService functionFindService) {
		this.functionFindService = functionFindService;
	}
	
}
