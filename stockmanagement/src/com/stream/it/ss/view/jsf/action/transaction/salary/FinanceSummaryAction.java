package com.stream.it.ss.view.jsf.action.transaction.salary;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.stream.it.ss.hibernate.inquiry.SalaryTransactionInquiry;
import com.stream.it.ss.service.combo.MonthComboDropdownService;
import com.stream.it.ss.service.combo.YearComboDropdownService;
import com.stream.it.ss.service.webcustom.transaction.salary.FinanceTransactionService;
import com.stream.it.ss.service.webcustom.transaction.salary.SalaryManagementService;
import com.stream.it.ss.utils.format.DateUtil;
import com.stream.it.ss.utils.format.StringType;
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
	
	/*** FORM ****/
	private SalarySearchForm searchFormBO;
	
	private List<?>transactionList;
	private List<?> yearDropdown;
	private List<?> monthDropdown;

	
	//**** ACTION ****//
	public void doListTransaction()throws Exception{
		transactionList = financeTransactionService.listTrasnaction(searchFormBO);
		
		DisplayMessages.showMessage("Search", searchFormBO);
	}	
	
	public void doCreateTransaction()throws Exception{
		financeTransactionService.createTrasnaction(transactionList, searchFormBO.getMonth(), searchFormBO.getYear());
		
		transactionList = financeTransactionService.listTrasnaction(searchFormBO);		
		
		DisplayMessages.showMessage("Create", searchFormBO);
	}
	
	public void doUpdateTransaction()throws Exception{
		financeTransactionService.updateTrasnaction(transactionList, searchFormBO.getMonth(), searchFormBO.getYear());
		
		transactionList = financeTransactionService.listTrasnaction(searchFormBO);		
		
		DisplayMessages.showMessage("Update", searchFormBO);
	}
	
	/***** AJAX EVENT ********/
	public void prepareTransactionFromMonthSalary()throws Exception{
		transactionList = salaryManagementService.listTrasnactionAll(searchFormBO);
		
		DisplayMessages.showErrorMessage("น่ำรายการจากทะเบียนเงินเดือนเรียบร้อย");
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
			
			transactionInquiry.setTotalSalaryIncome(transactionInquiry.getSalary()+
													transactionInquiry.getDaily()+
													transactionInquiry.getFare()+
													transactionInquiry.getDiligence()+
													transactionInquiry.getBonus()+
													transactionInquiry.getOtherIncome()+
													transactionInquiry.getOtSummary()
													);
			
			transactionInquiry.setTotalSalaryIncomeNet((transactionInquiry.getSalary()+
														transactionInquiry.getDaily()+
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
				
		totalSalaryIncome = totalSalary+totalDaily+totalFare+totalDiligence+totalBonus+totalOtSummary+totalOtherIncome;
		totalSalaryIncomeNet = totalSalaryIncome-(totalSubtractTax+totalSubtractSocial+totalSubtractLeave+totalSubtractAccumulate+totalSubtractOther);

//		System.out.println("totalSalary 	: "+totalSalary);
//		System.out.println("totalDaily 		: "+totalDaily);
//		System.out.println("totalFare 		: "+totalFare);
//		System.out.println("totalDiligence 	: "+totalDiligence);
//		System.out.println("totalBonus 		: "+totalBonus);
//		System.out.println("totalOtherIncome: "+totalOtherIncome);
//		System.out.println("totalOtSummary 	: "+totalOtSummary);

//		System.out.println("totalSalaryIncome 		: "+totalSalaryIncome);
//		System.out.println("totalSalaryIncomeNet 	: "+totalSalaryIncomeNet);
		
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
	
	//**** PAGENAVIGATOR ****//
	public String listPage() throws Exception{
		yearDropdown = yearComboDropdownService.listCurrent10Year();
		monthDropdown = monthComboDropdownService.listAllMonthTh();
		
		searchFormBO = new SalarySearchForm();
		searchFormBO.setMonth(Integer.parseInt(DateUtil.getMonth()));
		searchFormBO.setYear(Integer.parseInt(DateUtil.getCurrentYear()));
		
		transactionList = financeTransactionService.listTrasnaction(searchFormBO);
		
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
}
