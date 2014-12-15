package com.stream.it.ss.service.webcustom.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stream.it.ss.base.constant.SQLConstantOperType;
import com.stream.it.ss.base.constant.SQLConstantWhereType;
import com.stream.it.ss.base.databo.SearchBean;
import com.stream.it.ss.base.databo.SearchConditionValuesBean;
import com.stream.it.ss.hibernate.dao.InquiryDAO;
import com.stream.it.ss.hibernate.inquiry.SalaryTransactionInquiry;
import com.stream.it.ss.utils.format.SQLStringType;
import com.stream.it.ss.view.jsf.form.transaction.salary.SalarySearchForm;


@Service("salaryManagementReportService")
public class SalaryManagementReportServiceImpl implements SalaryManagementReportService{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("salaryTransactionsInquiry")
	private InquiryDAO inquiryDAO;

	@Autowired
	@Qualifier("summarySalaryTransactionsInquiry")
	private InquiryDAO summarySalaryTransactionsInquiryDAO;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List listTrasnaction(SearchBean searchBean) throws Exception {
		SalarySearchForm salarySearchFormBO = (SalarySearchForm) searchBean;
		List<SalaryTransactionInquiry> resultDataList = new ArrayList<SalaryTransactionInquiry>();

		try {
			salarySearchFormBO.setSqlParameter(Arrays.asList(
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), 
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear()
					));
			
			salarySearchFormBO.setConditionValuesBean(new SearchConditionValuesBean[] { new SearchConditionValuesBean(SQLConstantWhereType.AND,	"FIRST_NAME",		SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(salarySearchFormBO.getUserName()) })});
			
			resultDataList = inquiryDAO.listAll(salarySearchFormBO);
			
			if(!resultDataList.isEmpty()){
				
				salarySearchFormBO.setSqlParameter(Arrays.asList(
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), 
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear()
					));
			
				salarySearchFormBO.setConditionValuesBean(new SearchConditionValuesBean[] { new SearchConditionValuesBean(SQLConstantWhereType.AND,	"FIRST_NAME",		SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(salarySearchFormBO.getUserName()) })});
			
				
				List<SalaryTransactionInquiry> totalSumResultDataList = summarySalaryTransactionsInquiryDAO.listAll(salarySearchFormBO);
				
				for(SalaryTransactionInquiry summaryTransactionInquiry: totalSumResultDataList){
					
					salarySearchFormBO.setTotalSalary(				summaryTransactionInquiry.getSalaryStr());
					salarySearchFormBO.setTotalDaily(				summaryTransactionInquiry.getDailyStr());
					salarySearchFormBO.setTotalFare(				summaryTransactionInquiry.getFareStr());
					salarySearchFormBO.setTotalDiligence(			summaryTransactionInquiry.getDiligenceStr());
					salarySearchFormBO.setTotalBonus(				summaryTransactionInquiry.getBonusStr());
					salarySearchFormBO.setTotalOtherIncome(			summaryTransactionInquiry.getOtherIncomeStr());
					
					salarySearchFormBO.setTotalOtDate(				summaryTransactionInquiry.getOtDate());
					salarySearchFormBO.setTotalOtHour(				summaryTransactionInquiry.getOtHour());
					salarySearchFormBO.setTotalOtSummary(			summaryTransactionInquiry.getOtSummaryStr());
					salarySearchFormBO.setTotalSalaryIncome(		summaryTransactionInquiry.getTotalSalaryIncomeStr());
					salarySearchFormBO.setTotalSubtractTax(			summaryTransactionInquiry.getSubtractTaxStr());
					salarySearchFormBO.setTotalSubtractSocial(		summaryTransactionInquiry.getSubtractSocialStr());
					salarySearchFormBO.setTotalSubtractLeave(		summaryTransactionInquiry.getLeaveSubtractStr());
					salarySearchFormBO.setTotalSubtractAccumulate(	summaryTransactionInquiry.getAccumulateSubtractStr());
					salarySearchFormBO.setTotalSubtractOther(		summaryTransactionInquiry.getOtherSubtractStr());
					
					salarySearchFormBO.setTotalSalaryIncomeNet(		summaryTransactionInquiry.getTotalSalaryIncomeNetStr());					
				}
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			salarySearchFormBO.getResultBO().setException(e);
			salarySearchFormBO.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultDataList;
	}
}
