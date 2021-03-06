package com.stream.it.ss.view.jsf.action.transaction.stock;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.hibernate.inquiry.Dropdown;
import com.stream.it.ss.hibernate.inquiry.MenuInquery;
import com.stream.it.ss.service.combo.ItemComboDropdownService;
import com.stream.it.ss.service.combo.SupplierComboDropdownService;
import com.stream.it.ss.service.findservice.ItemFindService;
import com.stream.it.ss.service.webcustom.setting.ItemService;
import com.stream.it.ss.service.webcustom.transaction.stock.StockItemService;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.setting.item.ItemForm;
import com.stream.it.ss.view.jsf.form.transaction.stock.StockItemForm;
import com.stream.it.ss.view.jsf.form.transaction.stock.StockItemSearchForm;


@ViewScoped
@ManagedBean(name="stockItemAction")
public class StockItemManageAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	//****** SERVICE *********//
	@ManagedProperty(value="#{stockItemService}")
    private StockItemService stockService;
	
	@ManagedProperty(value="#{itemService}")
    private ItemService itemService;
	
	@ManagedProperty(value="#{supplierComboDropdownService}")
	private SupplierComboDropdownService supplierComboDropdownService;
	
	@ManagedProperty(value="#{itemComboDropdownService}")
	private ItemComboDropdownService itemComboDropdownService;

	@ManagedProperty(value="#{itemFindService}")
	private ItemFindService itemFindService;
	
	
	//****** FORM *******//
	private List<MenuInquery> transactionList;
	private List<Dropdown> supplierDropdown;
	private List<Dropdown> itemDropdown;
	private StreamedContent fileTransactionsDataExport;
	
	private StockItemSearchForm searchForm = new StockItemSearchForm();
	private StockItemForm dataFormBO;
	private ItemForm itemDataFormBO;
	
	@PostConstruct
	public void init(){
		try{
			itemDropdown = itemComboDropdownService.listAll();
			supplierDropdown = supplierComboDropdownService.listAll();
			
			dataFormBO = new StockItemForm();
			itemDataFormBO = new ItemForm();
			
		}catch(Exception e){
			DisplayMessages.showMessage("Stock Item", searchForm);   	
		}
    }
	
	//**** ACTION *****//
	public void doCreateStock() throws Exception{
		ResultBO resultBO = stockService.createStockTransaction(dataFormBO);
		
		DisplayMessages.showInfoMessage("วัตถุดิบ "+itemDataFormBO.getItemSupplierCode()+" "+itemDataFormBO.getItemName()+" จำนวน "+dataFormBO.getStockQty()+" ถูกสร้างรายการเรียบร้อย");
		
		dataFormBO.setResultBO(resultBO);
		dataFormBO.setStockQty(null);		
	}
	
	//**** AJAX *****//
	public void doSelectItem() throws NumberFormatException, Exception{
		String itemId = searchForm.getItemId();
		Integer supplierId = itemFindService.findSupplierIdByItem(Integer.parseInt(itemId));
		
		searchForm.setSupplierId(supplierId.toString());
	}
	
	public void doSelectSupplierAddForm() throws NumberFormatException, Exception{
		itemDataFormBO = new ItemForm();
		dataFormBO.setItemId(null);

		
		String supplierId = dataFormBO.getSupplierId();
		if(supplierId.equals(""))
			itemDropdown = itemComboDropdownService.listAll();
		else
			itemDropdown = itemComboDropdownService.listBySupplierId(Integer.parseInt(supplierId));
		
	}
	
	public void doFindItemDetail() throws NumberFormatException, Exception{
		itemDataFormBO = new ItemForm();
		dataFormBO.setSupplierId(null);
		
		String itemId = dataFormBO.getItemId();
		
		if(!itemId.equals("")){
			itemDataFormBO = (ItemForm) itemService.findItem(Integer.parseInt(itemId));
			
			Integer supplierId = itemFindService.findSupplierIdByItem(Integer.parseInt(itemId));
			dataFormBO.setSupplierId(supplierId.toString());
		}
	}
	

	public String addPage() throws Exception{
		
		return "stock.item.add";
	}


	//****** SETTER, GETTER *******//
	public List<MenuInquery> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<MenuInquery> transactionList) {
		this.transactionList = transactionList;
	}
	public StockItemForm getDataFormBO() {
		return dataFormBO;
	}
	public void setDataFormBO(StockItemForm dataFormBO) {
		this.dataFormBO = dataFormBO;
	}
	public StockItemSearchForm getSearchForm() {
		return searchForm;
	}
	public void setSearchForm(StockItemSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	public List<Dropdown> getItemDropdown() {
		return itemDropdown;
	}
	public void setItemDropdown(List<Dropdown> itemDropdown) {
		this.itemDropdown = itemDropdown;
	}
	public void setItemComboDropdownService(ItemComboDropdownService itemComboDropdownService) {
		this.itemComboDropdownService = itemComboDropdownService;
	}
	public ItemForm getItemDataFormBO() {
		return itemDataFormBO;
	}
	public void setItemDataFormBO(ItemForm itemDataFormBO) {
		this.itemDataFormBO = itemDataFormBO;
	}
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	public void setStockService(StockItemService stockService) {
		this.stockService = stockService;
	}
	public void setSupplierComboDropdownService(SupplierComboDropdownService supplierComboDropdownService) {
		this.supplierComboDropdownService = supplierComboDropdownService;
	}
	public List<Dropdown> getSupplierDropdown() {
		return supplierDropdown;
	}
	public void setSupplierDropdown(List<Dropdown> supplierDropdown) {
		this.supplierDropdown = supplierDropdown;
	}
	public void setItemFindService(ItemFindService itemFindService) {
		this.itemFindService = itemFindService;
	}
	public StreamedContent getFileTransactionsDataExport() {
		return fileTransactionsDataExport;
	}
	public void setFileTransactionsDataExport(StreamedContent fileTransactionsDataExport) {
		this.fileTransactionsDataExport = fileTransactionsDataExport;
	}

}
