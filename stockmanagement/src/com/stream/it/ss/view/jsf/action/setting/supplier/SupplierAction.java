package com.stream.it.ss.view.jsf.action.setting.supplier;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.hibernate.inquiry.Dropdown;
import com.stream.it.ss.hibernate.inquiry.MenuInquery;
import com.stream.it.ss.service.combo.SupplierTypeComboDropdownService;
import com.stream.it.ss.service.webcustom.setting.SupplierService;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.setting.supplier.SupplierForm;
import com.stream.it.ss.view.jsf.form.setting.supplier.SupplierSearchForm;



@ManagedBean
@SessionScoped
public class SupplierAction extends BaseAction{
	//****** SERVICE *********//
	@ManagedProperty(value="#{supplierService}")
    private SupplierService supplierService;
	
	@ManagedProperty(value="#{supplierTypeComboDropdownService}")
	private SupplierTypeComboDropdownService supplierTypeComboDropdownService;
	
	//****** FORM *******//
	private SupplierSearchForm searchForm = new SupplierSearchForm();
	private List<MenuInquery>transactionList;
	private List<Dropdown> supplierDropdown;
	
	private SupplierForm supplierForm;
	
	
	public SupplierAction(){}
	
	//**** ACTION *****//
	@SuppressWarnings("unchecked")
	public void doListTransaction() throws Exception{
		transactionList = supplierService.listTransaction(searchForm);
		DisplayMessages.showMessage("Search", searchForm);   		
		
	}
	
	public String doCreateSupplier() throws Exception{
		ResultBO resultBO = supplierService.createSupplier(supplierForm);
		
		return listPage();
	}
	
	public String doEditSupplier() throws Exception{
		ResultBO resultBO = supplierService.updateSupplier(supplierForm);
		
		return listPage();
	}
	
	public String doDeleteSupplier() throws Exception{
		String[]checkDelete = getHttpServletRequest().getParameterValues("checkDelete");
		ResultBO resultBO = supplierService.deleteSupplier(checkDelete);
		DisplayMessages.showMessage("Delete ", resultBO); 
		
		return listPage();
	}
	
	
	//**** PAGE NAVIGATOR ****//
	public String listPage() throws Exception{
		searchForm = new SupplierSearchForm();
		transactionList = supplierService.listTransaction(searchForm);
		
		return "supplier.list";
	}
	
	public String addPage() throws Exception{
		supplierForm = new SupplierForm();
		supplierDropdown = supplierTypeComboDropdownService.listAll();
		
		return "supplier.add";
	}
	
	public String editPage() throws Exception{
		supplierDropdown = supplierTypeComboDropdownService.listAll();
		
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
	public List<Dropdown> getSupplierDropdown() {
		return supplierDropdown;
	}
	public void setSupplierDropdown(List<Dropdown> supplierDropdown) {
		this.supplierDropdown = supplierDropdown;
	}
	public void setSupplierTypeComboDropdownService(SupplierTypeComboDropdownService supplierTypeComboDropdownService) {
		this.supplierTypeComboDropdownService = supplierTypeComboDropdownService;
	}
	
}
