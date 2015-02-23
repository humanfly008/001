package com.stream.it.ss.view.jsf.action.setting.supplier;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.hibernate.inquiry.MenuInquery;
import com.stream.it.ss.service.webcustom.setting.SupplierService;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.setting.supplier.SupplierForm;
import com.stream.it.ss.view.jsf.form.setting.supplier.SupplierSearchForm;



@ManagedBean
@ViewScoped
public class SupplierAction extends BaseAction{
	//****** SERVICE *********//
	@ManagedProperty(value="#{supplierService}")
    private SupplierService supplierService;
	
	
	//****** FORM *******//
	private SupplierSearchForm searchForm;
	private List<MenuInquery>transactionList;
	
	private SupplierForm supplierForm;
	
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){
		try{
			searchForm = new SupplierSearchForm();
			transactionList = supplierService.listTransaction(searchForm);

		}catch(Exception e){
			DisplayMessages.showMessage("Supplier", searchForm);   	
		}
    }
	
	//**** ACTION *****//
	@SuppressWarnings("unchecked")
	public void doListTransaction() throws Exception{
		transactionList = supplierService.listTransaction(searchForm);
		DisplayMessages.showMessage("Search", searchForm);   		
		
	}
	
	@SuppressWarnings("unchecked")
	public String doCreateSupplier() throws Exception{
		ResultBO resultBO = supplierService.createSupplier(supplierForm);
		
		transactionList = supplierService.listTransaction(searchForm);
		DisplayMessages.showMessage("Create", resultBO);   		
		
		return "supplier.list";
	}
	
	@SuppressWarnings("unchecked")
	public String doEditSupplier() throws Exception{
		ResultBO resultBO = supplierService.updateSupplier(supplierForm);
		
		transactionList = supplierService.listTransaction(searchForm);
		DisplayMessages.showMessage("Update", resultBO);   	
		
		return "supplier.list";
	}
	
	@SuppressWarnings("unchecked")
	public String doDeleteSupplier() throws Exception{
		String[]checkDelete = getHttpServletRequest().getParameterValues("checkDelete");
		ResultBO resultBO = supplierService.deleteSupplier(checkDelete);
		DisplayMessages.showMessage("Delete ", resultBO); 
		
		transactionList = supplierService.listTransaction(searchForm);
		
		return "supplier.list";
	}
	
	
	//**** PAGE NAVIGATOR ****//
	public String listPage() throws Exception{
		
		return "supplier.list";
	}
	
	public String addPage() throws Exception{
		supplierForm = new SupplierForm();
		
		return "supplier.add";
	}
	
	public String editPage() throws Exception{
		
		String supplierId = getHttpServletRequest().getParameter("supplierId");
		supplierForm = (SupplierForm) supplierService.findSupplier(Integer.parseInt(supplierId));
		
		return "supplier.edit";
	}
	

	//****** SETTER, GETTER *******//
	public List<MenuInquery> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<MenuInquery> transactionList) {
		this.transactionList = transactionList;
	}
	public SupplierSearchForm getSearchForm() {
		return searchForm;
	}
	public void setSearchForm(SupplierSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	public SupplierForm getSupplierForm() {
		return supplierForm;
	}
	public void setSupplierForm(SupplierForm supplierForm) {
		this.supplierForm = supplierForm;
	}
	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}	
}
