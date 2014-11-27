package com.stream.it.ss.view.jsf.action.transaction.salary;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.StreamedContent;

import com.lowagie.text.Cell;
import com.stream.it.ss.hibernate.inquiry.SalaryTransactionInquiry;
import com.stream.it.ss.service.combo.MonthComboDropdownService;
import com.stream.it.ss.service.combo.YearComboDropdownService;
import com.stream.it.ss.service.webcustom.transaction.salary.FinanceTransactionService;
import com.stream.it.ss.service.webcustom.transaction.salary.SalaryManagementService;
import com.stream.it.ss.utils.format.DateUtil;
import com.stream.it.ss.utils.format.StringType;
import com.stream.it.ss.utils.primefaces.report.Report2PDF;
import com.stream.it.ss.utils.primefaces.report.Report2PDFExporter;
import com.stream.it.ss.utils.primefaces.report.ReportExporter;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.transaction.salary.SalarySearchForm;


@SessionScoped
@ManagedBean
public class FinanceSummaryAction extends BaseAction{
	/**** SERVICE ****/
	@ManagedProperty(value="#{financeTransactionService}")
	private FinanceTransactionService financeTransactionService;
	
	@ManagedProperty(value="#{salaryManagementService}")
	private SalaryManagementService salaryManagementService;
	
	@ManagedProperty(value="#{yearComboDropdownService}")
	private YearComboDropdownService yearComboDropdownService;
	
	@ManagedProperty(value="#{monthComboDropdownService}")
	private MonthComboDropdownService monthComboDropdownService;
	
	@ManagedProperty(value="#{reportExporter}")
	private ReportExporter reportExporter;
	
	/*** FORM ****/
	private SalarySearchForm searchFormBO;
	private StreamedContent fileTransactionsDataExport;
	private String transactionFrom;
	private List<?>transactionList;
	private List<?> yearDropdown;
	private List<?> monthDropdown;

	
	//**** ACTION ****//
	public void doListTransaction()throws Exception{
		transactionFrom = "SALARY";

		transactionList = financeTransactionService.listTrasnaction(searchFormBO);
		if(transactionList.size()>0)
			transactionFrom = "FINANCE";
			
		
		DisplayMessages.showMessage("Search", searchFormBO);
	}	
	
	public void doCreateTransaction()throws Exception{
		financeTransactionService.createTrasnaction(transactionList, searchFormBO.getMonth(), searchFormBO.getYear());
		
		transactionList = financeTransactionService.listTrasnaction(searchFormBO);		
		
		DisplayMessages.showMessage("Create", searchFormBO);
		transactionFrom = "FINANCE";
	}
	
	public void doUpdateTransaction()throws Exception{
		financeTransactionService.updateTrasnaction(transactionList, searchFormBO.getMonth(), searchFormBO.getYear());
		
		transactionList = financeTransactionService.listTrasnaction(searchFormBO);		
		
		DisplayMessages.showMessage("Update", searchFormBO);
	}
	
	/***** AJAX EVENT ********/
	public void prepareTransactionFromMonthSalary()throws Exception{
		transactionList = salaryManagementService.listTrasnactionAll(searchFormBO);
		transactionFrom = "SALARY";
		
		DisplayMessages.showErrorMessage("น่ำรายการจากทะเบียนเงินเดือนเรียบร้อย");
	}
	
	public void removeTransaction(){
		String[]checkDelete = getHttpServletRequest().getParameterValues("checkDelete");
		for(int i=0; i<checkDelete.length; i++){
			transactionList.remove(Integer.parseInt(checkDelete[i]));		
		}
	}
	
	public void updateCalculateTrasnsaction(){
		double totalSalary = 0;
		double totalDaily = 0;
		double totalFare = 0;
		double totalDiligence = 0;
		double totalBonus = 0;
		double totalOtSummary = 0;
		double totalOtherIncome = 0;
		
		double totalSubtractTax = 0;
		double totalSubtractSocial = 0;
		double totalSubtractLeave = 0;
		double totalSubtractAccumulate = 0;
		double totalSubtractOther = 0;
		
		double totalSalaryIncome = 0;
		double totalSalaryIncomeNet = 0;
		
		SalaryTransactionInquiry transactionInquiry;
		
		for(int i=0; i<transactionList.size(); i++){
			transactionInquiry = (SalaryTransactionInquiry)transactionList.get(i);
			
			totalSalary		+=	transactionInquiry.getSalary();
			totalDaily		+=	transactionInquiry.getDaily();
			totalFare		+=	transactionInquiry.getFare();
			totalDiligence	+=	transactionInquiry.getDiligence();
			totalBonus		+= 	transactionInquiry.getBonus();
			totalOtherIncome+= 	transactionInquiry.getOtherIncome();
			totalOtSummary	+=	transactionInquiry.getOtSummary();
			
			totalSubtractTax		+=	transactionInquiry.getSubtractTax();
			totalSubtractSocial		+=	transactionInquiry.getSubtractSocial();
			totalSubtractLeave		+=	transactionInquiry.getLeaveSubtract();
			totalSubtractAccumulate	+=	transactionInquiry.getAccumulateSubtract();
			totalSubtractOther		+=	transactionInquiry.getOtherSubtract();
			
			if(transactionInquiry.getPayType().equals("MONTH")){
				transactionInquiry.setTotalSalaryIncome(transactionInquiry.getSalary()+
														transactionInquiry.getFare()+
														transactionInquiry.getDiligence()+
														transactionInquiry.getBonus()+
														transactionInquiry.getOtherIncome()+
														transactionInquiry.getOtSummary()
														);
				
				transactionInquiry.setTotalSalaryIncomeNet((transactionInquiry.getSalary()+
														transactionInquiry.getFare()+
														transactionInquiry.getDiligence()+
														transactionInquiry.getBonus()+
														transactionInquiry.getOtherIncome()+
														transactionInquiry.getOtSummary())
														-
														(transactionInquiry.getSubtractTax()+
														transactionInquiry.getSubtractSocial()+
														transactionInquiry.getLeaveSubtract()+
														transactionInquiry.getAccumulateSubtract()+
														transactionInquiry.getOtherSubtract())
													);
			}else{
				transactionInquiry.setTotalSalaryIncome(transactionInquiry.getDaily()+
														transactionInquiry.getFare()+
														transactionInquiry.getDiligence()+
														transactionInquiry.getBonus()+
														transactionInquiry.getOtherIncome()+
														transactionInquiry.getOtSummary()
														);

				transactionInquiry.setTotalSalaryIncomeNet((transactionInquiry.getDaily()+
														transactionInquiry.getFare()+
														transactionInquiry.getDiligence()+
														transactionInquiry.getBonus()+
														transactionInquiry.getOtherIncome()+
														transactionInquiry.getOtSummary())
															-
														(transactionInquiry.getSubtractTax()+
														transactionInquiry.getSubtractSocial()+
														transactionInquiry.getLeaveSubtract()+
														transactionInquiry.getAccumulateSubtract()+
														transactionInquiry.getOtherSubtract())
													);
			}
		}	
				
		totalSalaryIncome = totalSalary+totalDaily+totalFare+totalDiligence+totalBonus+totalOtSummary+totalOtherIncome;
		totalSalaryIncomeNet = totalSalaryIncome-(totalSubtractTax+totalSubtractSocial+totalSubtractLeave+totalSubtractAccumulate+totalSubtractOther);
		
		searchFormBO.setTotalSalary(				StringType.getDoubleNumberMoneyFormatted(totalSalary));
		searchFormBO.setTotalDaily(					StringType.getDoubleNumberMoneyFormatted(totalDaily));
		searchFormBO.setTotalFare(					StringType.getDoubleNumberMoneyFormatted(totalFare));
		searchFormBO.setTotalDiligence(				StringType.getDoubleNumberMoneyFormatted(totalDiligence));
		searchFormBO.setTotalBonus(					StringType.getDoubleNumberMoneyFormatted(totalBonus));
		searchFormBO.setTotalOtherIncome(			StringType.getDoubleNumberMoneyFormatted(totalOtherIncome));
		searchFormBO.setTotalOtSummary(				StringType.getDoubleNumberMoneyFormatted(totalOtSummary));
		
		searchFormBO.setTotalSalaryIncome(			StringType.getDoubleNumberMoneyFormatted(totalSalaryIncome));
		
		searchFormBO.setTotalSubtractTax(			StringType.getDoubleNumberMoneyFormatted(totalSubtractTax));
		searchFormBO.setTotalSubtractSocial(		StringType.getDoubleNumberMoneyFormatted(totalSubtractSocial));
		searchFormBO.setTotalSubtractLeave(			StringType.getDoubleNumberMoneyFormatted(totalSubtractLeave));
		searchFormBO.setTotalSubtractAccumulate(	StringType.getDoubleNumberMoneyFormatted(totalSubtractAccumulate));
		searchFormBO.setTotalSubtractOther(			StringType.getDoubleNumberMoneyFormatted(totalSubtractOther));
		
		searchFormBO.setTotalSalaryIncomeNet(		StringType.getDoubleNumberMoneyFormatted(totalSalaryIncomeNet));	
	}
	
	private String[] monthTHDesc = {"","มกราคม","กุมภาพันธ์","มีนาคม","เมษายน","พฤษภาคม","มิถุนายน","กฏกฎาคม","สิงหาคม","กันยายน","ตุลาคม","พฤจิยายน","ธันวาคม"};
	
	public void doExportReport() throws Exception{
		transactionList = financeTransactionService.listTrasnaction(searchFormBO);
		String reportType = getHttpServletRequest().getParameter("listForm:reportType_input");

		Report2PDF report2pdfExporter = new Report2PDFExporter();
		report2pdfExporter.setColumnWidths(new float[]{0.5f, 2f, 1.5f, 1.5f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 2.0f});
		report2pdfExporter.setCellValueAlign(new int[]{Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_CENTER, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_RIGHT, Cell.ALIGN_LEFT});
		
		reportExporter.setReportName("salary report");
		reportExporter.setReport2pdfExporter(report2pdfExporter);
		
		fileTransactionsDataExport = reportExporter.genReportData(reportType, "ทะเบียนเงินเดือน", transactionList, 
				new String[]{"no",	"fullName",	"position",	"payTypeDesc",	"salaryStr",		"dailyStr",		"fareStr",	"diligenceStr",	"bonusStr",		"otherIncomeStr",	"otSummaryStr",	"totalSalaryIncome",	"subtractSocialStr",	"subtractTaxStr",	"leaveSubtractStr",		"accumulateSubtractStr",	"otherSubtractStr",	"totalSalaryIncomeNet",	"details"}, 
				new String[]{"No.",	"ชื่อ",		"ตำแหน่ง",		"ประเภทรายได้",		"เงินเดือน/ค่าแรง",		"รายวัน",			"ค่าพาหนะ",		"ค่าเบี้ยขยัน", 		"โบนัส", 			"รายได้อื่นๆ", 			"OT", 			"รายได้รวม", 				"หัก ปกส", 					"หัก ภาษี", 				"หัก หยุดงาน", 				"หักสะม", 						"หักอื่นๆ", 				"คงเหลือ", 					"หมายเหคุ"},
				new String[][]{	
						new String[]{"ปี :"+searchFormBO.getYear() +" เดือน : "+monthTHDesc[searchFormBO.getMonth()]}	
				},
				new String[]{"", "", "", "รวม", searchFormBO.getTotalSalary(), searchFormBO.getTotalDaily(), searchFormBO.getTotalFare(), searchFormBO.getTotalDiligence(), searchFormBO.getTotalBonus(), searchFormBO.getTotalOtherIncome(), searchFormBO.getTotalOtSummary(), searchFormBO.getTotalSalaryIncome(), searchFormBO.getTotalSubtractSocial(), searchFormBO.getTotalSubtractTax() , searchFormBO.getTotalSubtractLeave(), searchFormBO.getTotalSubtractAccumulate(), searchFormBO.getTotalSubtractOther(), searchFormBO.getTotalSalaryIncomeNet(), ""},
				
				searchFormBO.getSecuriyBO().getUserAuthentication().getUserLogin());
	}
	
	//**** PAGENAVIGATOR ****//
	public String listPage() throws Exception{
		transactionFrom = "SALARY";

		yearDropdown = yearComboDropdownService.listCurrent10Year();
		monthDropdown = monthComboDropdownService.listAllMonthTh();
		
		searchFormBO = new SalarySearchForm();
		searchFormBO.setMonth(Integer.parseInt(DateUtil.getMonth()));
		searchFormBO.setYear(Integer.parseInt(DateUtil.getCurrentYear()));
		
		transactionList = financeTransactionService.listTrasnaction(searchFormBO);
		if(transactionList.size()>0)
			transactionFrom = "FINANCE";
		
		return "finance.list";
	}

	
	public SalarySearchForm getSearchFormBO() {
		return searchFormBO;
	}
	public void setSearchFormBO(SalarySearchForm searchFormBO) {
		this.searchFormBO = searchFormBO;
	}
	public List<?> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<?> transactionList) {
		this.transactionList = transactionList;
	}
	public List<?> getYearDropdown() {
		return yearDropdown;
	}
	public void setYearDropdown(List<?> yearDropdown) {
		this.yearDropdown = yearDropdown;
	}
	public List<?> getMonthDropdown() {
		return monthDropdown;
	}
	public void setMonthDropdown(List<?> monthDropdown) {
		this.monthDropdown = monthDropdown;
	}
	public void setSalaryManagementService(SalaryManagementService salaryManagementService) {
		this.salaryManagementService = salaryManagementService;
	}
	public void setYearComboDropdownService(YearComboDropdownService yearComboDropdownService) {
		this.yearComboDropdownService = yearComboDropdownService;
	}
	public void setMonthComboDropdownService(MonthComboDropdownService monthComboDropdownService) {
		this.monthComboDropdownService = monthComboDropdownService;
	}
	public void setFinanceTransactionService(FinanceTransactionService financeTransactionService) {
		this.financeTransactionService = financeTransactionService;
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
	public String getTransactionFrom() {
		return transactionFrom;
	}
	public void setTransactionFrom(String transactionFrom) {
		this.transactionFrom = transactionFrom;
	}
}
