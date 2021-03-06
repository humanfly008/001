package com.stream.it.ss.view.jsf.action.transaction.stock;

import java.util.Date;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.lowagie.text.Cell;
import com.stream.it.ss.hibernate.inquiry.Dropdown;
import com.stream.it.ss.hibernate.inquiry.MenuInquery;
import com.stream.it.ss.service.combo.ProductComboDropdownService;
import com.stream.it.ss.service.combo.SupplierComboDropdownService;
import com.stream.it.ss.service.findservice.ProductFindService;
import com.stream.it.ss.service.webcustom.report.StockProductReportService;
import com.stream.it.ss.service.webcustom.setting.ProductService;
import com.stream.it.ss.service.webcustom.transaction.stock.StockProductService;
import com.stream.it.ss.utils.format.DateUtil;
import com.stream.it.ss.utils.primefaces.report.Report2PDF;
import com.stream.it.ss.utils.primefaces.report.Report2PDFExporter;
import com.stream.it.ss.utils.primefaces.report.ReportExporter;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.setting.product.ProductForm;
import com.stream.it.ss.view.jsf.form.transaction.stock.StockProductForm;
import com.stream.it.ss.view.jsf.form.transaction.stock.StockProductSearchForm;


@ViewScoped
@ManagedBean(name="stockProductInquiryAction")
public class StockProductInquiryAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	//****** SERVICE *********//
	@ManagedProperty(value="#{stockProductService}")
    private StockProductService stockService;
	
	@ManagedProperty(value="#{stockProductReportService}")
    private StockProductReportService stockProductReportService;
	
	@ManagedProperty(value="#{productService}")
    private ProductService productService;
	
	@ManagedProperty(value="#{supplierComboDropdownService}")
	private SupplierComboDropdownService supplierComboDropdownService;
	
	@ManagedProperty(value="#{productComboDropdownService}")
	private ProductComboDropdownService productComboDropdownService;

	@ManagedProperty(value="#{productFindService}")
	private ProductFindService productFindService;
	
	@ManagedProperty(value="#{reportExporter}")
	private ReportExporter reportExporter;
	
	//****** FORM *******//
	private List<MenuInquery> transactionList;
	private List<Dropdown> supplierDropdown;
	private List<Dropdown> productDropdown;
	private StreamedContent fileTransactionsDataExport;
	
	private StockProductSearchForm searchForm = new StockProductSearchForm();
	private StockProductForm dataFormBO;
	private ProductForm productDataFormBO;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){
		try{
			productDropdown = productComboDropdownService.listAll();
			supplierDropdown = supplierComboDropdownService.listAll();
			
			searchForm = new StockProductSearchForm();
			searchForm.setStockFromDate(DateUtil.getDateBeforeByMonth(3));
			searchForm.setStockToDate(new Date());
			
			transactionList = stockService.listTransaction(searchForm);
			
		}catch(Exception e){
			DisplayMessages.showMessage("Stock Product", searchForm);   	
		}
    }
	
	//**** ACTION *****//
	@SuppressWarnings("unchecked")
	public void doListTransaction() throws Exception{
		transactionList = stockService.listTransaction(searchForm);
		DisplayMessages.showMessage("Search", searchForm);   		
		
	}	
	
	//**** AJAX *****//
	public void doSelectProduct() throws NumberFormatException, Exception{
		String productId = searchForm.getProductId();
		Integer supplierId = productFindService.findSupplierIdByProduct(Integer.parseInt(productId));
		
		searchForm.setSupplierId(supplierId.toString());
	}
	
	public void doSelectSupplier() throws NumberFormatException, Exception{
		String supplierId = searchForm.getSupplierId();
		if(supplierId.equals(""))
			productDropdown = productComboDropdownService.listAll();
		else
			productDropdown = productComboDropdownService.listBySupplierId(Integer.parseInt(supplierId));
	}
	
	@SuppressWarnings("unchecked")
	public void doExportReport() throws Exception{
		transactionList = stockProductReportService.listTransaction(searchForm);
		String reportType = getHttpServletRequest().getParameter("listForm:reportType_input");

		Report2PDF report2pdfExporter = new Report2PDFExporter();
		report2pdfExporter.setColumnWidths(new float[]{0.3f, 0.5f, 1f, 0.5f, 1f, 3.5f, 3f, 1f});
		report2pdfExporter.setCellValueAlign(new int[]{Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_RIGHT, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER});
		 
		reportExporter.setReportName("stock product report");
		reportExporter.setReport2pdfExporter(report2pdfExporter);
		
		fileTransactionsDataExport = reportExporter.genReportData(reportType, "สรุป รายการเข้า/ออก สินค้า", transactionList, 
				new String[]{"no",	"stockTypeDesc",	"stockDateStr",	"stockQtyStr",	"productCode",	"productName",	"supplierName", "createBy"}, 
				new String[]{"No.",	"ประเภทรายการ",			"วันที่บันทึกข้อมูล",		"จำนวน",			"รหัสสินค้า",			"ชื่อสินค้า",			"ตัวแทนจำหน่าย",		"คนสร้างรายการ"},
				null,
				null,
				searchForm.getSecuriyBO().getUserAuthentication().getUserLogin());
	}
	
	
	//**** PAGE NAVIGATOR ****//
	public String listPage() throws Exception{
		
		return "stock.product.list";
	}


	//****** SETTER, GETTER *******//
	public List<MenuInquery> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<MenuInquery> transactionList) {
		this.transactionList = transactionList;
	}
	public StockProductForm getDataFormBO() {
		return dataFormBO;
	}
	public void setDataFormBO(StockProductForm dataFormBO) {
		this.dataFormBO = dataFormBO;
	}
	public StockProductSearchForm getSearchForm() {
		return searchForm;
	}
	public void setSearchForm(StockProductSearchForm searchForm) {
		this.searchForm = searchForm;
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
	public ProductForm getProductDataFormBO() {
		return productDataFormBO;
	}
	public void setProductDataFormBO(ProductForm productDataFormBO) {
		this.productDataFormBO = productDataFormBO;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setStockService(StockProductService stockService) {
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
	public void setProductFindService(ProductFindService productFindService) {
		this.productFindService = productFindService;
	}
	public StreamedContent getFileTransactionsDataExport() {
		return fileTransactionsDataExport;
	}
	public void setFileTransactionsDataExport(StreamedContent fileTransactionsDataExport) {
		this.fileTransactionsDataExport = fileTransactionsDataExport;
	}
	public void setStockProductReportService(StockProductReportService stockProductReportService) {
		this.stockProductReportService = stockProductReportService;
	}
	public void setReportExporter(ReportExporter reportExporter) {
		this.reportExporter = reportExporter;
	}
}
