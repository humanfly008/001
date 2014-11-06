package com.stream.it.ss.view.jsf.action.transaction.salary;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;


import com.ibm.db2.jcc.am.dp;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.hibernate.inquiry.Dropdown;
import com.stream.it.ss.hibernate.inquiry.SalaryTransactionInquiry;
import com.stream.it.ss.service.combo.MonthComboDropdownService;
import com.stream.it.ss.service.combo.YearComboDropdownService;
import com.stream.it.ss.service.webcustom.transaction.salary.SalaryManagementService;
import com.stream.it.ss.utils.format.DateUtil;
import com.stream.it.ss.utils.format.StringType;
import com.stream.it.ss.view.jsf.base.BaseAction;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.transaction.salary.SalaryDailyForm;
import com.stream.it.ss.view.jsf.form.transaction.salary.SalaryIncomeForm;
import com.stream.it.ss.view.jsf.form.transaction.salary.SalaryOTForm;
import com.stream.it.ss.view.jsf.form.transaction.salary.SalarySearchForm;

@SessionScoped
@ManagedBean
public class SalaryManagementAction extends BaseAction{
	//**** SERVICE ****//
	@ManagedProperty(value="#{salaryManagementService}")
    private SalaryManagementService salaryManagementService;
	
	@ManagedProperty(value="#{yearComboDropdownService}")
	private YearComboDropdownService yearComboDropdownService;
	
	@ManagedProperty(value="#{monthComboDropdownService}")
	private MonthComboDropdownService monthComboDropdownService;
	
	
	//**** FORM ****//
	private SalarySearchForm searchFormBO;
	private SalarySearchForm searchFormBOTemp;
	private SalaryOTForm salaryOTFormBO;
	private SalaryIncomeForm salaryIncomeForm;
	private SalaryDailyForm salaryDailyForm;
	private SalaryTransactionInquiry printSalaryFormBO;
	
	private List<?>transactionList;
	private List<?>transactionOtByUserList;
	private List<?>transactionDailyByUserList;
	private List<?> yearDropdown;
	private List<?> monthDropdown;
	
	
	//**** ACTION ****//
	public void doListTransaction()throws Exception{
		transactionList = salaryManagementService.listTrasnaction(searchFormBO);
	
		DisplayMessages.showMessage("Search", searchFormBO);
	}	
	
	public void doCreateOT()throws Exception{
		ResultBO resultBO = salaryManagementService.createOt(salaryOTFormBO);
		
		transactionList = salaryManagementService.listTrasnaction(searchFormBO);
		
		DisplayMessages.showMessage("Create OT", resultBO);
	}	
	
	public void doCreateSalaryDaily()throws Exception{
		ResultBO resultBO = salaryManagementService.createDaily(salaryDailyForm);
		
		transactionList = salaryManagementService.listTrasnaction(searchFormBO);
		
		DisplayMessages.showMessage("Create OT", resultBO);
	}	
	
	public void doUpdateInCome()throws Exception{
		ResultBO resultBO = salaryManagementService.updateIncome(salaryIncomeForm);
		
		transactionList = salaryManagementService.listTrasnaction(searchFormBO);
		
		DisplayMessages.showMessage("Create OT", resultBO);
	}	
	
	public void doDeleteInCome()throws Exception{
		ResultBO resultBO = salaryManagementService.deleteIncome(salaryIncomeForm);
		
		transactionList = salaryManagementService.listTrasnaction(searchFormBO);
		
		DisplayMessages.showMessage("Create OT", resultBO);
	}	
	
	public void doDeleteOtTransactionByUser() throws Exception{
		String[]checkDelete = getHttpServletRequest().getParameterValues("checkDelete");
		ResultBO resultBO = salaryManagementService.deleteOtTrasnactionByUser(checkDelete);
		DisplayMessages.showMessage("Delete ", resultBO); 
		
		transactionList = salaryManagementService.listTrasnaction(searchFormBO);
	}
	
	public void doDeleteDailyTransactionByUser() throws Exception{
		String[]checkDelete = getHttpServletRequest().getParameterValues("checkDelete2");
		ResultBO resultBO = salaryManagementService.deleteDailyTrasnactionByUser(checkDelete);
		DisplayMessages.showMessage("Delete ", resultBO); 
		
		transactionList = salaryManagementService.listTrasnaction(searchFormBO);
	}

	
	//**** PAGENAVIGATOR ****//
	public String listPage() throws Exception{
		yearDropdown = yearComboDropdownService.listCurrent10Year();
		monthDropdown = monthComboDropdownService.listAllMonthTh();
		
		searchFormBO = new SalarySearchForm();
		searchFormBO.setMonth(Integer.parseInt(DateUtil.getMonth()));
		searchFormBO.setYear(Integer.parseInt(DateUtil.getCurrentYear()));
		
		transactionList = salaryManagementService.listTrasnaction(searchFormBO);
		
		return "salary.list";
	}
	
	@SuppressWarnings("unchecked")
	public String printSalaryIncomePage()throws Exception{
		printSalaryFormBO = new SalaryTransactionInquiry();
		String userId		  = getHttpServletRequest().getParameter("userId");
		String id		  	  = getHttpServletRequest().getParameter("id");
		
		SalarySearchForm searchFormBOTemp = new SalarySearchForm();
		searchFormBOTemp.setId(id);
		searchFormBOTemp.setUserId(userId);
		searchFormBOTemp.setMonth(searchFormBO.getMonth());
		searchFormBOTemp.setYear(searchFormBO.getYear());
		
		List<SalaryTransactionInquiry> transactionPrintSalary = salaryManagementService.listTrasnactionByUser(searchFormBOTemp);
		
		if(!transactionPrintSalary.isEmpty())
			printSalaryFormBO = (SalaryTransactionInquiry)transactionPrintSalary.get(0);
		
		searchFormBOTemp = null;
		transactionPrintSalary = null;
		
		return null;
	}
	
	public String addDailySalaryPage()throws Exception{
		String id		  	  = getHttpServletRequest().getParameter("id");
		String userId		  = getHttpServletRequest().getParameter("userId");
		String income 		  = getHttpServletRequest().getParameter("income");
		String incomeType 	  = getHttpServletRequest().getParameter("incomeType");
		String incomeTypeDesc = getHttpServletRequest().getParameter("incomeTypeDesc");
	
		salaryDailyForm = new SalaryDailyForm();
		salaryDailyForm.setUserId(			userId);
		salaryDailyForm.setId(id);
		salaryDailyForm.setIncome(			Double.parseDouble(income));
		salaryDailyForm.setIncomeType(		incomeType);
		salaryDailyForm.setIncomeTypeDesc(	incomeTypeDesc);
				
		searchFormBOTemp = new SalarySearchForm();
		searchFormBOTemp.setUserId(			userId);
		searchFormBOTemp.setId(				id);
		searchFormBOTemp.setMonth(			searchFormBO.getMonth());
		searchFormBOTemp.setYear(			searchFormBO.getYear());
		transactionDailyByUserList = salaryManagementService.listSalaryDailyByUserTrasnaction(searchFormBOTemp);
		
		return null;
	}
	
	public String addOTPage()throws Exception{
		String id		  	= getHttpServletRequest().getParameter("id");
		String userId		= getHttpServletRequest().getParameter("userId");
		String salary 		= StringType.getStringReplaceValue(getHttpServletRequest().getParameter("salary"),"0");
		String daily 		= StringType.getStringReplaceValue(getHttpServletRequest().getParameter("daily"),"0");
		String fare			= StringType.getStringReplaceValue(getHttpServletRequest().getParameter("fare"),"0");
		String diligence	= StringType.getStringReplaceValue(getHttpServletRequest().getParameter("diligence"),"0");
		String bonus		= StringType.getStringReplaceValue(getHttpServletRequest().getParameter("bonus"),"0");
		String socialInsurance = StringType.getStringReplaceValue(getHttpServletRequest().getParameter("socialInsurance"),"0");
		String tax			= StringType.getStringReplaceValue(getHttpServletRequest().getParameter("tax"),"0");
		
		salaryOTFormBO = new SalaryOTForm();
		salaryOTFormBO.setOtHour(4);
		salaryOTFormBO.setOtRate(1.5);
		salaryOTFormBO.setOtDate(new Date());

		salaryOTFormBO.setUserId(			userId);
		salaryOTFormBO.setId(				id);
		salaryOTFormBO.setSalary(			Double.parseDouble(salary));
		salaryOTFormBO.setDaily(			Double.parseDouble(daily));
		salaryOTFormBO.setFare(				Double.parseDouble(fare));
		salaryOTFormBO.setDiligence(		Double.parseDouble(diligence));
		salaryOTFormBO.setBonus(			Double.parseDouble(bonus));
		salaryOTFormBO.setSocialInsurance(	Double.parseDouble(socialInsurance));
		salaryOTFormBO.setTax(				Double.parseDouble(tax));

		searchFormBOTemp = new SalarySearchForm();
		searchFormBOTemp.setUserId(			userId);
		searchFormBOTemp.setMonth(			searchFormBO.getMonth());
		searchFormBOTemp.setYear(			searchFormBO.getYear());
		transactionOtByUserList = salaryManagementService.listOtByUserTrasnaction(searchFormBOTemp);
		
		return null;
	}
	
	public String updateIncomePage()throws Exception{
		String id		  	= getHttpServletRequest().getParameter("id");
		String userId		= getHttpServletRequest().getParameter("userId");
		String incomeType 	= getHttpServletRequest().getParameter("incomeType");
		String incomeTypeDesc = getHttpServletRequest().getParameter("incomeTypeDesc");
	
		salaryIncomeForm = new SalaryIncomeForm();
		salaryIncomeForm.setUserId(			userId);
		salaryIncomeForm.setId(				id);
		salaryIncomeForm.setIncomeType(		incomeType);
		salaryIncomeForm.setIncomeTypeDesc(	incomeTypeDesc);
		salaryIncomeForm.setMonth(			searchFormBO.getMonth());
		salaryIncomeForm.setYear(			searchFormBO.getYear());
		
		return null;
	}
	
	
	//**** SETTER, GETTER*****//
	public List<?> getTransactionList() {
		return transactionList;
	}
	public SalarySearchForm getSearchFormBO() {
		return searchFormBO;
	}
	public void setSearchFormBO(SalarySearchForm searchFormBO) {
		this.searchFormBO = searchFormBO;
	}
	public SalaryManagementService getSalaryManagementService() {
		return salaryManagementService;
	}
	public void setTransactionList(List<?> transactionList) {
		this.transactionList = transactionList;
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
	public SalaryOTForm getSalaryOTFormBO() {
		return salaryOTFormBO;
	}
	public void setSalaryOTFormBO(SalaryOTForm salaryOTFormBO) {
		this.salaryOTFormBO = salaryOTFormBO;
	}
	public List<?> getTransactionOtByUserList() {
		return transactionOtByUserList;
	}
	public void setTransactionOtByUserList(List<?> transactionOtByUserList) {
		this.transactionOtByUserList = transactionOtByUserList;
	}
	public SalarySearchForm getSearchFormBOTemp() {
		return searchFormBOTemp;
	}
	public void setSearchFormBOTemp(SalarySearchForm searchFormBOTemp) {
		this.searchFormBOTemp = searchFormBOTemp;
	}
	public SalaryIncomeForm getSalaryIncomeForm() {
		return salaryIncomeForm;
	}
	public void setSalaryIncomeForm(SalaryIncomeForm salaryIncomeForm) {
		this.salaryIncomeForm = salaryIncomeForm;
	}
	public SalaryDailyForm getSalaryDailyForm() {
		return salaryDailyForm;
	}
	public void setSalaryDailyForm(SalaryDailyForm salaryDailyForm) {
		this.salaryDailyForm = salaryDailyForm;
	}
	public List<?> getTransactionDailyByUserList() {
		return transactionDailyByUserList;
	}
	public void setTransactionDailyByUserList(List<?> transactionDailyByUserList) {
		this.transactionDailyByUserList = transactionDailyByUserList;
	}
	public SalaryTransactionInquiry getPrintSalaryFormBO() {
		return printSalaryFormBO;
	}
	public void setPrintSalaryFormBO(SalaryTransactionInquiry printSalaryFormBO) {
		this.printSalaryFormBO = printSalaryFormBO;
	}		
}
