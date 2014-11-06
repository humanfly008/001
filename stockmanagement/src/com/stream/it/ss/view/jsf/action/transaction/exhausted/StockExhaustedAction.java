package com.stream.it.ss.view.jsf.action.transaction.exhausted;

import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.StreamedContent;

import com.lowagie.text.Cell;
import com.stream.it.ss.hibernate.domain.MItem;
import com.stream.it.ss.hibernate.inquiry.Dropdown;
import com.stream.it.ss.service.combo.ItemComboDropdownService;
import com.stream.it.ss.service.combo.ProductComboDropdownService;
import com.stream.it.ss.service.combo.SupplierComboDropdownService;
import com.stream.it.ss.service.webcustom.report.StockExhaustedInquiryReportService;
import com.stream.it.ss.service.webcustom.transaction.exhausted.StockExhaustedInquiryService;
import com.stream.it.ss.utils.primefaces.report.Report2PDF;
import com.stream.it.ss.utils.primefaces.report.Report2PDFExporter;
import com.stream.it.ss.utils.primefaces.report.ReportExporter;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.transaction.exhausted.StockExhaustedSearchForm;



@ManagedBean(name="stockExhaustedAction")
@SessionScoped
public class StockExhaustedAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	//****** SERVICE *********//
	@ManagedProperty(value="#{stockExhaustedInquiryService}")
    private StockExhaustedInquiryService stockExhaustedInquiryService;
	
	@ManagedProperty(value="#{stockExhaustedInquiryReportService}")
	private StockExhaustedInquiryReportService stockExhaustedInquiryReportService;
	
	@ManagedProperty(value="#{supplierComboDropdownService}")
	private SupplierComboDropdownService supplierComboDropdownService;
	
	@ManagedProperty(value="#{itemComboDropdownService}")
	private ItemComboDropdownService itemComboDropdownService;

	@ManagedProperty(value="#{productComboDropdownService}")
	private ProductComboDropdownService productComboDropdownService;
	
	@ManagedProperty(value="#{reportExporter}")
	private ReportExporter reportExporter;
	
	
	//****** FORM *******//
	private List<?> transactionList;
	private List<Dropdown> supplierDropdown;
	private List<Dropdown> itemDropdown;
	private List<Dropdown> productDropdown;
	private List<?>productItemGroupList;
	
	private StreamedContent fileTransactionsDataExport;
	
	private StockExhaustedSearchForm searchForm;
	
	
	public StockExhaustedAction(){
		
	}
	
	//**** ACTION *****//
	public void doListTransaction() throws Exception{
		transactionList = stockExhaustedInquiryService.listTransaction(searchForm);
		DisplayMessages.showMessage("Search", searchForm);   		
		
	}
	
	public void doFindItemProductGroup() throws Exception{
		String productId = getHttpServletRequest().getParameter("productId");
		productItemGroupList = stockExhaustedInquiryService.findItemProductGroup(productId);
		
	}
		
	public void doExportReport() throws Exception{
		transactionList = stockExhaustedInquiryReportService.listTransaction(searchForm);
		String reportType = getHttpServletRequest().getParameter("listForm:reportType_input");

		Report2PDF report2pdfExporter = new Report2PDFExporter();
		report2pdfExporter.setColumnWidths(new float[]{0.5f, 0.5f, 1f, 3f, 0.5f, 1f, 0.5f});
		report2pdfExporter.setCellValueAlign(new int[]{Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_RIGHT, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER});
		 
		reportExporter.setReportName("stock exhausted report");
		reportExporter.setReport2pdfExporter(report2pdfExporter);
		
		fileTransactionsDataExport = reportExporter.genReportData(reportType, "รายการวัตถุดิบ สินค้าใกล้หมด", transactionList, 
				new String[]{"no",	"transactionType",	"objectCode",	"objectName",	"qty",		"supplier",	"statusDesc"}, 
				new String[]{"No.",	"ประเภทรายการ",			"รหัสสินค้าตัวแทนจำหน่่าย",	"ชื่อรายการ",		"จำนวนคงเหลือ",	"ตัวแทนจำหน่าย",	"สถานะ"},
				null,
				null,
				searchForm.getSecuriyBO().getUserAuthentication().getUserLogin());
	}
	
	//**** PAGE NAVIGATOR ****//
	public String listPage() throws Exception{
		itemDropdown = itemComboDropdownService.listAll();
		productDropdown = productComboDropdownService.listAll();
		supplierDropdown = supplierComboDropdownService.listAll();
		
		searchForm = new StockExhaustedSearchForm();
		transactionList = stockExhaustedInquiryService.listTransaction(searchForm);
		
		return "stock.exhausted.list";
	}

	//****** SETTER, GETTER *******//
	public List<?> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<?> transactionList) {
		this.transactionList = transactionList;
	}
	public List<Dropdown> getSupplierDropdown() {
		return supplierDropdown;
	}
	public void setSupplierDropdown(List<Dropdown> supplierDropdown) {
		this.supplierDropdown = supplierDropdown;
	}
	public List<Dropdown> getItemDropdown() {
		return itemDropdown;
	}
	public void setItemDropdown(List<Dropdown> itemDropdown) {
		this.itemDropdown = itemDropdown;
	}
	public StockExhaustedSearchForm getSearchForm() {
		return searchForm;
	}
	public void setSearchFormBO(StockExhaustedSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setStockExhaustedInquiryService(StockExhaustedInquiryService stockExhaustedInquiryService) {
		this.stockExhaustedInquiryService = stockExhaustedInquiryService;
	}
	public void setSupplierComboDropdownService(SupplierComboDropdownService supplierComboDropdownService) {
		this.supplierComboDropdownService = supplierComboDropdownService;
	}
	public void setItemComboDropdownService(ItemComboDropdownService itemComboDropdownService) {
		this.itemComboDropdownService = itemComboDropdownService;
	}
	public List<Dropdown> getProductDropdown() {
		return productDropdown;
	}
	public void setProductDropdown(List<Dropdown> productDropdown) {
		this.productDropdown = productDropdown;
	}
	public void setProductComboDropdownService(ProductComboDropdownService productComboDropdownService) {
		this.productComboDropdownService = productComboDropdownService;
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
	public void setFileTransactionsDataExport(StreamedContent fileTransactionsDataExport) {
		this.fileTransactionsDataExport = fileTransactionsDataExport;
	}
	public void setStockExhaustedInquiryReportService(StockExhaustedInquiryReportService stockExhaustedInquiryReportService) {
		this.stockExhaustedInquiryReportService = stockExhaustedInquiryReportService;
	}
	public void setSearchForm(StockExhaustedSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	public List<?> getProductItemGroupList() {
		return productItemGroupList;
	}
	public void setProductItemGroupList(List<?> productItemGroupList) {
		this.productItemGroupList = productItemGroupList;
	}
}
