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
import com.stream.it.ss.service.combo.ProductComboDropdownService;
import com.stream.it.ss.service.combo.SupplierComboDropdownService;
import com.stream.it.ss.service.findservice.ProductFindService;
import com.stream.it.ss.service.webcustom.setting.ProductService;
import com.stream.it.ss.service.webcustom.transaction.stock.StockProductService;
import com.stream.it.ss.utils.primefaces.report.ReportExporter;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.setting.product.ProductForm;
import com.stream.it.ss.view.jsf.form.transaction.stock.StockProductForm;
import com.stream.it.ss.view.jsf.form.transaction.stock.StockProductSearchForm;


@ViewScoped
@ManagedBean(name="stockProductAction")
public class StockProductManageAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	//****** SERVICE *********//
	@ManagedProperty(value="#{stockProductService}")
    private StockProductService stockService;
	
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
	
	@PostConstruct
	public void init(){
		try{
			dataFormBO = new StockProductForm();
			productDataFormBO = new ProductForm();
			productDropdown = productComboDropdownService.listAll();
			supplierDropdown = supplierComboDropdownService.listAll();
			
		}catch(Exception e){
			DisplayMessages.showMessage("Stock Product", searchForm);   	
		}
    }
	
	//**** ACTION *****//
	public String doCreateStock() throws Exception{
		ResultBO resultBO = stockService.createStockTransaction(dataFormBO);
		
		DisplayMessages.showInfoMessage("สินค้า "+productDataFormBO.getProductSupplierCode()+" "+productDataFormBO.getProductName()+" จำนวน "+dataFormBO.getStockQty()+" ถูกสร้างรายการเรียบร้อย");

		dataFormBO.setResultBO(resultBO);
		dataFormBO.setStockQty(null);		
		
		return null;
	}
	
	//**** AJAX *****//
	public void doSelectProduct() throws NumberFormatException, Exception{
		String productId = searchForm.getProductId();
		Integer supplierId = productFindService.findSupplierIdByProduct(Integer.parseInt(productId));
		
		searchForm.setSupplierId(supplierId.toString());
	}

	public void doSelectSupplierAddForm() throws NumberFormatException, Exception{
		productDataFormBO = new ProductForm();
		dataFormBO.setProductId(null);

		
		String supplierId = dataFormBO.getSupplierId();
		if(supplierId.equals(""))
			productDropdown = productComboDropdownService.listAll();
		else
			productDropdown = productComboDropdownService.listBySupplierId(Integer.parseInt(supplierId));
		
	}
	
	public void doFindProductDetail() throws NumberFormatException, Exception{
		productDataFormBO = new ProductForm();
		dataFormBO.setSupplierId(null);
		
		String productId = dataFormBO.getProductId();
		
		if(!productId.equals("")){
			productDataFormBO = (ProductForm) productService.findProduct(Integer.parseInt(productId));
			
			Integer supplierId = productFindService.findSupplierIdByProduct(Integer.parseInt(productId));
			dataFormBO.setSupplierId(supplierId.toString());
		}
	}
		
	//**** PAGE NAVIGATOR ****//
	public String addPage() throws Exception{
		
		return "stock.product.add";
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
	public void setReportExporter(ReportExporter reportExporter) {
		this.reportExporter = reportExporter;
	}
}
