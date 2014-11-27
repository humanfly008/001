package com.stream.it.ss.view.jsf.action.setting.product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

import com.lowagie.text.Cell;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.hibernate.domain.MItem;
import com.stream.it.ss.hibernate.inquiry.Dropdown;
import com.stream.it.ss.hibernate.inquiry.ProductInquiry;
import com.stream.it.ss.service.combo.ItemComboDropdownService;
import com.stream.it.ss.service.combo.SupplierComboDropdownService;
import com.stream.it.ss.service.findservice.ItemFindService;
import com.stream.it.ss.service.webcustom.report.ProductReportService;
import com.stream.it.ss.service.webcustom.setting.ProductService;
import com.stream.it.ss.service.webcustom.transaction.stock.StockProductService;
import com.stream.it.ss.utils.primefaces.report.Report2PDF;
import com.stream.it.ss.utils.primefaces.report.Report2PDFExporter;
import com.stream.it.ss.utils.primefaces.report.ReportExporter;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.setting.product.ProductForm;
import com.stream.it.ss.view.jsf.form.setting.product.ProductItemGroupSearchForm;
import com.stream.it.ss.view.jsf.form.setting.product.ProductSearchForm;
import com.stream.it.ss.view.jsf.form.transaction.stock.StockProductForm;



@ManagedBean
@SessionScoped
public class ProductAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	//****** SERVICE *********//
	@ManagedProperty(value="#{productService}")
    private ProductService productService;
	
	@ManagedProperty(value="#{productReportService}")
	private ProductReportService productReportService;
	
	@ManagedProperty(value="#{stockProductService}")
    private StockProductService stockService;
	
	@ManagedProperty(value="#{supplierComboDropdownService}")
	private SupplierComboDropdownService supplierComboDropdownService;
	
	@ManagedProperty(value="#{itemComboDropdownService}")
	private ItemComboDropdownService itemComboDropdownService;
	
	@ManagedProperty(value="#{itemFindService}")
	private ItemFindService itemFindService;
	
	@ManagedProperty(value="#{reportExporter}")
	private ReportExporter reportExporter;
	
	//****** FORM *******//
	private ProductSearchForm searchForm;
	private ProductForm productForm;
	private StockProductForm stockFormBO;
	private ProductItemGroupSearchForm productItemGroupSearchForm;
	
	private StreamedContent fileTransactionsDataExport;
	
	private List<ProductInquiry>transactionList;
	private List<Dropdown> supplierDropdown;
	private List<Dropdown> itemDropdown;
	private List<MItem>productItemGroupList;
	
	public ProductAction(){
		
	}
	
	//**** ACTION *****//
	public void doListTransaction() throws Exception{
		transactionList = productService.listTransaction(searchForm);
		DisplayMessages.showMessage("Search", searchForm);   		
		
	}
	
	public String doCreateProduct() throws Exception{
		productForm.setProductItemGroupList(this.productItemGroupList);
		
		productService.createProduct(productForm);
		DisplayMessages.showMessage("Create", productForm);   		
		
		transactionList = productService.listTransaction(searchForm);
		
		return "product.list";
	}
	
	public String doEditProduct() throws Exception{
		productForm.setProductItemGroupList(this.productItemGroupList);
		productService.updateProduct(productForm);
		DisplayMessages.showMessage("Edit", productForm);   		
		
		transactionList = productService.listTransaction(searchForm);		
		
		return "product.list";
	}
	
	public String doDeleteProduct() throws Exception{
		String[]checkDelete = getHttpServletRequest().getParameterValues("checkDelete");
		ResultBO resultBO = productService.deleteProduct(checkDelete);
		DisplayMessages.showMessage("Delete ", resultBO); 
		
		transactionList = productService.listTransaction(searchForm);	
		
		return "product.list";
	}
	
	public String doCreateStock() throws Exception{
		ResultBO resultBO = stockService.createStockTransaction(stockFormBO);
		
		stockFormBO.setResultBO(resultBO);
		DisplayMessages.showMessage("Create Stock Transaction", stockFormBO);   				

		transactionList = productService.listTransaction(searchForm);
		
		return "product.list";
	}
	
	public void doExportReport() throws Exception{
		transactionList = productReportService.listTransaction(searchForm);
		String reportType = getHttpServletRequest().getParameter("listForm:reportType_input");

		Report2PDF report2pdfExporter = new Report2PDFExporter();
		report2pdfExporter.setColumnWidths(new float[]{0.3f, 1.8f, 3f, 1.3f, 1f, 1f, 1f, 2.5f});
		report2pdfExporter.setCellValueAlign(new int[]{Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_CENTER});
		
		reportExporter.setReportName("item report");
		reportExporter.setReport2pdfExporter(report2pdfExporter);
		
		fileTransactionsDataExport = reportExporter.genReportData(reportType, "รายการสินค้า", transactionList, 
				new String[]{"no",	"productCode",	"productName",	"productType",	"unit",		"productUnitPriceStr",	"productQtyStr",	"supplier"}, 
				new String[]{"No.",	"รหัสสินค้า",			"ชื่อสินค้า",			"ประเภท",			"หน่วย",		"ราคาต่อหน่วย",				"จำนวนคงเหลือ",			"ตัวแทนจำหน่าย"},
				null,
				null,
				searchForm.getSecuriyBO().getUserAuthentication().getUserLogin());
	}
	
	//**** AJAX EVENT HANDLE **//
	private static final ResourceBundle systemProperties = ResourceBundle.getBundle("system");
	private static final String productPathStorePic = "temp/";
	private static final int BUFFER_SIZE = 612400;
	
	public void handleFileUpload(FileUploadEvent event) throws IOException { 
	    String fileName = "product_"+new Date().getTime()+".jpg";
	    String picStore = systemProperties.getString("pic.store.file");
	    
	    File fileResult = new File(picStore+productPathStorePic+fileName);
        
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
                
                productForm.setPicName(productPathStorePic+fileName);
            }                        
            
            
        } catch (IOException e) {
        	e.printStackTrace();
        	ResultBO resultBO = new ResultBO();
        	resultBO.setException(e);
        	resultBO.setErrorMessage(e.getMessage());
        	DisplayMessages.showMessage("Upload Fail!!! ", resultBO); 
        }
	} 
	
	public void doFindProductDetail() throws NumberFormatException, Exception{		
		stockFormBO = new StockProductForm();

		String productId = getHttpServletRequest().getParameter("productId");
		String productName = getHttpServletRequest().getParameter("productName");
		String supplierName = getHttpServletRequest().getParameter("supplierName");
		
		if(!productId.equals("")){
			productForm = (ProductForm) productService.findProduct(Integer.parseInt(productId));
			
			stockFormBO.setProductId(productId);
			stockFormBO.setProductName(productName);
			stockFormBO.setSupplierId(productForm.getSupplierId());
			stockFormBO.setSupplierName(supplierName);		
		}
	}	
	
	public void doSelectItem() throws NumberFormatException, Exception{
		String itemId = productItemGroupSearchForm.getItemId();

		productItemGroupSearchForm.setSupplierId("");

		if(itemId!=null && !itemId.equals("")){
			Integer supplierId = itemFindService.findSupplierIdByItem(Integer.parseInt(itemId));
			productItemGroupSearchForm.setSupplierId(supplierId.toString());
		}
	}
	
	public void doSelectSupplier() throws NumberFormatException, Exception{
		String supplierId = productItemGroupSearchForm.getSupplierId();
		if(supplierId.equals(""))
			itemDropdown = itemComboDropdownService.listAll();
		else
			itemDropdown = itemComboDropdownService.listBySupplierId(Integer.parseInt(supplierId));
	}	
	
	public void doAddItemGroup() throws NumberFormatException, Exception{
		String itemId = productItemGroupSearchForm.getItemId();
		
		if(itemId!=null && !itemId.equals("")){
			if(verifyItemGroup(itemId))
				productItemGroupList.add(itemFindService.findItemById(Integer.parseInt(itemId)));				
			else
				DisplayMessages.showErrorMessage("วัตถุดิบนี้มีในรายการแล้ว");
		}else
			DisplayMessages.showErrorMessage("[วัตถุดิบ] : Please input Data");
		
	}
	
	public void doDeleteItemGroup(ActionEvent event){
		String no = (String)event.getComponent().getAttributes().get("no").toString();
		productItemGroupList.remove(Integer.parseInt(no)-1);
	}
	
	private boolean verifyItemGroup(String itemId){
		for(int i=0; i<productItemGroupList.size(); i++)
			if(itemId.equals(((MItem)productItemGroupList.get(i)).getItemId()+""))
				return false;
		
		return true;
	}
	
	//**** PAGE NAVIGATOR ****//
	public String listPage() throws Exception{
		productForm = new ProductForm();
		searchForm = new ProductSearchForm();
		
		supplierDropdown = supplierComboDropdownService.listAll();
		
		transactionList = productService.listTransaction(searchForm);
		
		return "product.list";
	}
	
	public String listViewPage() throws Exception{
		productForm = new ProductForm();
		searchForm = new ProductSearchForm();
		
		supplierDropdown = supplierComboDropdownService.listAll();
		
		transactionList = productService.listTransaction(searchForm);
		
		return "product.list.view";
	}
	
	public void addPage() throws Exception{
		productForm = new ProductForm();
		productItemGroupSearchForm = new ProductItemGroupSearchForm();
		productItemGroupList = new ArrayList<MItem>();
		
		itemDropdown = itemComboDropdownService.listAll();
	}
	
	public void editPage() throws Exception{
		String productId = getHttpServletRequest().getParameter("productId");
		
		itemDropdown = itemComboDropdownService.listAll();
		
		productForm = (ProductForm) productService.findProduct(Integer.parseInt(productId));	

		this.productItemGroupSearchForm = new ProductItemGroupSearchForm();
		this.setProductItemGroupList(productForm.getProductItemGroupList());
	}

	//****** SETTER, GETTER *******//
	public ProductSearchForm getSearchForm() {
		return searchForm;
	}
	public List<ProductInquiry> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<ProductInquiry> transactionList) {
		this.transactionList = transactionList;
	}
	public List<Dropdown> getSupplierDropdown() {
		return supplierDropdown;
	}
	public void setSupplierDropdown(List<Dropdown> supplierDropdown) {
		this.supplierDropdown = supplierDropdown;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setSupplierComboDropdownService(SupplierComboDropdownService supplierComboDropdownService) {
		this.supplierComboDropdownService = supplierComboDropdownService;
	}
	public ProductForm getProductForm() {
		return productForm;
	}
	public void setProductForm(ProductForm productForm) {
		this.productForm = productForm;
	}
	public void setSearchForm(ProductSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	public StreamedContent getFileTransactionsDataExport() {
		return fileTransactionsDataExport;
	}
	public void setFileTransactionsDataExport(StreamedContent fileTransactionsDataExport) {
		this.fileTransactionsDataExport = fileTransactionsDataExport;
	}
	public void setProductReportService(ProductReportService productReportService) {
		this.productReportService = productReportService;
	}
	public void setReportExporter(ReportExporter reportExporter) {
		this.reportExporter = reportExporter;
	}
	public StockProductForm getStockFormBO() {
		return stockFormBO;
	}
	public void setStockFormBO(StockProductForm stockFormBO) {
		this.stockFormBO = stockFormBO;
	}
	public void setStockService(StockProductService stockService) {
		this.stockService = stockService;
	}
	public ProductItemGroupSearchForm getProductItemGroupSearchForm() {
		return productItemGroupSearchForm;
	}
	public void setProductItemGroupSearchForm(ProductItemGroupSearchForm productItemGroupSearchForm) {
		this.productItemGroupSearchForm = productItemGroupSearchForm;
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
	public void setItemFindService(ItemFindService itemFindService) {
		this.itemFindService = itemFindService;
	}
	public List<MItem> getProductItemGroupList() {
		return productItemGroupList;
	}
	public void setProductItemGroupList(List<MItem> productItemGroupList) {
		this.productItemGroupList = productItemGroupList;
	}
}
