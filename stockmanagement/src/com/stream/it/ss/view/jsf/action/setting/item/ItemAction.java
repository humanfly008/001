package com.stream.it.ss.view.jsf.action.setting.item;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import com.lowagie.text.Cell;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.hibernate.inquiry.Dropdown;
import com.stream.it.ss.hibernate.inquiry.ItemInquiry;
import com.stream.it.ss.service.combo.SupplierComboDropdownService;
import com.stream.it.ss.service.webcustom.report.ItemReportService;
import com.stream.it.ss.service.webcustom.setting.ItemService;
import com.stream.it.ss.service.webcustom.transaction.stock.StockItemService;
import com.stream.it.ss.utils.primefaces.report.Report2PDF;
import com.stream.it.ss.utils.primefaces.report.Report2PDFExporter;
import com.stream.it.ss.utils.primefaces.report.ReportExporter;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.setting.item.ItemForm;
import com.stream.it.ss.view.jsf.form.setting.item.ItemSearchForm;
import com.stream.it.ss.view.jsf.form.transaction.stock.StockItemForm;



@ManagedBean
@SessionScoped
public class ItemAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	//****** SERVICE *********//
	@ManagedProperty(value="#{itemService}")
    private ItemService itemService;
	
	@ManagedProperty(value="#{itemReportService}")
	private ItemReportService itemReportService;
	
	@ManagedProperty(value="#{stockItemService}")
    private StockItemService stockService;
	
	
	@ManagedProperty(value="#{supplierComboDropdownService}")
	private SupplierComboDropdownService supplierComboDropdownService;
		
	@ManagedProperty(value="#{reportExporter}")
	private ReportExporter reportExporter;
	
	
	//****** FORM *******//
	private ItemSearchForm searchForm = new ItemSearchForm();
	private ItemForm itemForm = new ItemForm();
	private StockItemForm stockItemFormBO;
	
	private List<ItemInquiry>transactionList;
	private List<Dropdown> supplierDropdown;
	
	private StreamedContent fileTransactionsDataExport;

	public ItemAction(){
		
	}
	
	//**** ACTION *****//
	public void doListTransaction() throws Exception{
		transactionList = itemService.listTransaction(searchForm);
		DisplayMessages.showMessage("Search", searchForm);   		
		
	}
	
	public void doCreateItem() throws Exception{
		itemService.createItem(itemForm);
		DisplayMessages.showMessage("Create", itemForm);   		
		
		transactionList = itemService.listTransaction(searchForm);		
	}
	
	public void doEditItem() throws Exception{
		itemService.updateItem(itemForm);
		DisplayMessages.showMessage("Edit", itemForm);   		
		
		transactionList = itemService.listTransaction(searchForm);		
	}
	
	public String doDeleteItem() throws Exception{
		String[]checkDelete = getHttpServletRequest().getParameterValues("checkDelete");
		ResultBO resultBO = itemService.deleteItem(checkDelete);
		DisplayMessages.showMessage("Delete ", resultBO); 
		
		return listPage();
	}
	
	public void doCreateStock() throws Exception{
		ResultBO resultBO = stockService.createStockTransaction(stockItemFormBO);
		
		stockItemFormBO.setResultBO(resultBO);
		DisplayMessages.showMessage("Create Stock Transaction", stockItemFormBO);   				

		transactionList = itemService.listTransaction(searchForm);
	}
	
	public void doExportReport() throws Exception{
		transactionList = itemReportService.listTransaction(searchForm);
		String reportType = getHttpServletRequest().getParameter("listForm:reportType_input");

		Report2PDF report2pdfExporter = new Report2PDFExporter();
		report2pdfExporter.setColumnWidths(new float[]{0.3f, 1.8f, 3f, 1.3f, 1f, 1f, 1f, 2.5f});
		report2pdfExporter.setCellValueAlign(new int[]{Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_CENTER});
		 
		reportExporter.setReportName("item report");
		reportExporter.setReport2pdfExporter(report2pdfExporter);
		
		fileTransactionsDataExport = reportExporter.genReportData(reportType, "รายการวัตถุดิบ", transactionList, 
				new String[]{"no",	"itemCode",	"itemName",	"itemType",	"unit",		"itemUnitPriceStr",	"itemQtyStr",	"supplier"}, 
				new String[]{"No.",	"รหัสวัตถุดิบ",	"ชื่อวัตถุดิบ",		"ประเภท",		"หน่วย",		"ราคาต่อหน่วย",			"จำนวนคงเหลือ",		"ตัวแทนจำหน่าย"},
				null,
				null,
				searchForm.getSecuriyBO().getUserAuthentication().getUserLogin());
	}
	
	
	//**** AJAX EVENT HANDLE **//
	private static final ResourceBundle systemProperties = ResourceBundle.getBundle("system");
	
	private static final String itemPathStorePic = "temp/";
	private static final int BUFFER_SIZE = 612400;
	
	public void handleFileUpload(FileUploadEvent event) throws IOException { 
	    System.out.println("handleFileUpload.....");
	    System.out.println("EVENT FILE NAME : "+event.getFile().getFileName());
	    
	    String fileName = "item_"+new Date().getTime()+".jpg";
	    String picStore = systemProperties.getString("pic.store.file");
	    
	    File fileResult = new File(picStore+itemPathStorePic+fileName);
        
        try {
            if (event.getFile().getFileName() != null) {
                
                FileOutputStream fileOutputStream = new FileOutputStream(fileResult);
                byte[] buffer = new byte[BUFFER_SIZE];
                int bulk;
                InputStream inputStream = event.getFile().getInputstream();

                while (true) {
                	bulk = inputStream.read(buffer);
                	if (bulk < 0) break;
                    
                	fileOutputStream.write(buffer, 0, bulk);
                	fileOutputStream.flush();
                }
                
                fileOutputStream.close();
                inputStream.close();
                
                itemForm.setPicName(itemPathStorePic+fileName);
            }                        
            
            
        } catch (IOException e) {
        	e.printStackTrace();
        	ResultBO resultBO = new ResultBO();
        	resultBO.setException(e);
        	resultBO.setErrorMessage(e.getMessage());
        	DisplayMessages.showMessage("Upload Fail!!! ", resultBO); 
        }
	}   
	
	public void doFindItemDetail() throws NumberFormatException, Exception{		
		stockItemFormBO = new StockItemForm();

		String itemId = getHttpServletRequest().getParameter("itemId");
		String itemName = getHttpServletRequest().getParameter("itemName");
		String supplierName = getHttpServletRequest().getParameter("supplierName");
		
		if(!itemId.equals("")){
			itemForm = (ItemForm) itemService.findItem(Integer.parseInt(itemId));
			
			stockItemFormBO.setItemId(itemId);
			stockItemFormBO.setItemName(itemName);
			stockItemFormBO.setSupplierId(itemForm.getSupplierId());
			stockItemFormBO.setSupplierName(supplierName);		
		}
	}	
	
	
	//**** PAGE NAVIGATOR ****//
	public String listPage() throws Exception{
		itemForm = new ItemForm();
		searchForm = new ItemSearchForm();
		supplierDropdown = supplierComboDropdownService.listAll();
		
		transactionList = itemService.listTransaction(searchForm);
		
		return "item.list";
	}
	
	public void addPage() throws Exception{
		itemForm = new ItemForm();
		supplierDropdown = supplierComboDropdownService.listAll();
		
	}
	
	public void editPage() throws Exception{
		String itemId = getHttpServletRequest().getParameter("itemId");
		itemForm = (ItemForm) itemService.findItem(Integer.parseInt(itemId));
		
	}

	//****** SETTER, GETTER *******//
	public ItemSearchForm getSearchForm() {
		return searchForm;
	}
	public List<ItemInquiry> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<ItemInquiry> transactionList) {
		this.transactionList = transactionList;
	}
	public List<Dropdown> getSupplierDropdown() {
		return supplierDropdown;
	}
	public void setSupplierDropdown(List<Dropdown> supplierDropdown) {
		this.supplierDropdown = supplierDropdown;
	}
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	public void setSupplierComboDropdownService(SupplierComboDropdownService supplierComboDropdownService) {
		this.supplierComboDropdownService = supplierComboDropdownService;
	}
	public ItemForm getItemForm() {
		return itemForm;
	}
	public void setItemForm(ItemForm itemForm) {
		this.itemForm = itemForm;
	}
	public void setSearchForm(ItemSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	public ReportExporter getReportExporter() {
		return reportExporter;
	}
	public void setReportExporter(ReportExporter reportExporter) {
		this.reportExporter = reportExporter;
	}
	public StreamedContent getFileTransactionsDataExport() {
		return fileTransactionsDataExport;
	}
	public void setFileTransactionsDataExport(
			StreamedContent fileTransactionsDataExport) {
		this.fileTransactionsDataExport = fileTransactionsDataExport;
	}
	public void setItemReportService(ItemReportService itemReportService) {
		this.itemReportService = itemReportService;
	}
	public StockItemForm getStockItemFormBO() {
		return stockItemFormBO;
	}
	public void setStockItemFormBO(StockItemForm stockItemFormBO) {
		this.stockItemFormBO = stockItemFormBO;
	}
	public void setStockService(StockItemService stockService) {
		this.stockService = stockService;
	}
}
