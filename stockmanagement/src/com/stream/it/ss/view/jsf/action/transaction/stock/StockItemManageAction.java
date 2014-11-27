package com.stream.it.ss.view.jsf.action.transaction.stock;

import java.util.Date;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.StreamedContent;

import com.lowagie.text.Cell;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.hibernate.inquiry.Dropdown;
import com.stream.it.ss.hibernate.inquiry.MenuInquery;
import com.stream.it.ss.service.combo.ItemComboDropdownService;
import com.stream.it.ss.service.combo.SupplierComboDropdownService;
import com.stream.it.ss.service.findservice.ItemFindService;
import com.stream.it.ss.service.webcustom.report.StockItemReportService;
import com.stream.it.ss.service.webcustom.setting.ItemService;
import com.stream.it.ss.service.webcustom.transaction.stock.StockItemService;
import com.stream.it.ss.utils.format.DateUtil;
import com.stream.it.ss.utils.primefaces.report.Report2PDF;
import com.stream.it.ss.utils.primefaces.report.Report2PDFExporter;
import com.stream.it.ss.utils.primefaces.report.ReportExporter;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.setting.item.ItemForm;
import com.stream.it.ss.view.jsf.form.transaction.stock.StockItemForm;
import com.stream.it.ss.view.jsf.form.transaction.stock.StockItemSearchForm;



@ManagedBean(name="stockItemAction")
@SessionScoped
public class StockItemManageAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	//****** SERVICE *********//
	@ManagedProperty(value="#{stockItemService}")
    private StockItemService stockService;
	
	@ManagedProperty(value="#{stockItemReportService}")
    private StockItemReportService stockItemReportService;
	
	@ManagedProperty(value="#{itemService}")
    private ItemService itemService;
	
	@ManagedProperty(value="#{supplierComboDropdownService}")
	private SupplierComboDropdownService supplierComboDropdownService;
	
	@ManagedProperty(value="#{itemComboDropdownService}")
	private ItemComboDropdownService itemComboDropdownService;

	@ManagedProperty(value="#{itemFindService}")
	private ItemFindService itemFindService;
	
	@ManagedProperty(value="#{reportExporter}")
	private ReportExporter reportExporter;
	
	
	//****** FORM *******//
	private List<MenuInquery> transactionList;
	private List<Dropdown> supplierDropdown;
	private List<Dropdown> itemDropdown;
	private StreamedContent fileTransactionsDataExport;
	
	private StockItemSearchForm searchForm = new StockItemSearchForm();
	private StockItemForm dataFormBO;
	private ItemForm itemDataFormBO;
	
	public StockItemManageAction(){
		
	}
	
	//**** ACTION *****//
	@SuppressWarnings("unchecked")
	public void doListTransaction() throws Exception{
		transactionList = stockService.listTransaction(searchForm);
		DisplayMessages.showMessage("Search", searchForm);   		
		
	}	
	
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
	
	public void doSelectSupplier() throws NumberFormatException, Exception{
		String supplierId = searchForm.getSupplierId();
		if(supplierId.equals(""))
			itemDropdown = itemComboDropdownService.listAll();
		else
			itemDropdown = itemComboDropdownService.listBySupplierId(Integer.parseInt(supplierId));
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
	
	@SuppressWarnings("unchecked")
	public void doExportReport() throws Exception{
		transactionList = stockItemReportService.listTransaction(searchForm);
		String reportType = getHttpServletRequest().getParameter("listForm:reportType_input");

		Report2PDF report2pdfExporter = new Report2PDFExporter();
		report2pdfExporter.setColumnWidths(new float[]{0.3f, 0.5f, 1f, 0.5f, 1f, 3.5f, 3f, 1f});
		report2pdfExporter.setCellValueAlign(new int[]{Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_RIGHT, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER});
		 
		reportExporter.setReportName("stock item report");
		reportExporter.setReport2pdfExporter(report2pdfExporter);
		
		fileTransactionsDataExport = reportExporter.genReportData(reportType, "สรุป รายการเข้า/ออก วัตถุดิบ", transactionList, 
				new String[]{"no",	"stockTypeDesc",	"stockDateStr",	"stockQtyStr",	"itemCode",	"itemName",	"supplierName", "createBy"}, 
				new String[]{"No.",	"ประเภทรายการ",			"วันที่บันทึกข้อมูล",		"จำนวน",			"รหัสวัตถุดิบ",	"ชื่อวัตถุดิบ",		"ตัวแทนจำหน่าย",		"คนสร้างรายการ"},
				null,
				null,
				searchForm.getSecuriyBO().getUserAuthentication().getUserLogin());
	}
	
	
	//**** PAGE NAVIGATOR ****//
	@SuppressWarnings("unchecked")
	public String listPage() throws Exception{
		itemDropdown = itemComboDropdownService.listAll();
		supplierDropdown = supplierComboDropdownService.listAll();
		
		searchForm = new StockItemSearchForm();
		searchForm.setStockFromDate(DateUtil.getDateBeforeByMonth(3));
		searchForm.setStockToDate(new Date());
		
		transactionList = stockService.listTransaction(searchForm);
		
		return "stock.item.list";
	}
	
	public String addPage() throws Exception{
		dataFormBO = new StockItemForm();
		itemDataFormBO = new ItemForm();
		itemDropdown = itemComboDropdownService.listAll();
		supplierDropdown = supplierComboDropdownService.listAll();
		
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
	public void setStockItemReportService(StockItemReportService stockItemReportService) {
		this.stockItemReportService = stockItemReportService;
	}
	public void setReportExporter(ReportExporter reportExporter) {
		this.reportExporter = reportExporter;
	}
	
}
