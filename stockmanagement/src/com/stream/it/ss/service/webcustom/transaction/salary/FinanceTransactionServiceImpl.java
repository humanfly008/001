package com.stream.it.ss.service.webcustom.transaction.salary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.base.databo.SearchBean;
import com.stream.it.ss.hibernate.dao.FinanceMonthlyTransactionDAO;
import com.stream.it.ss.hibernate.dao.InquiryDAO;
import com.stream.it.ss.hibernate.domain.FinanceMonthlyTransaction;
import com.stream.it.ss.hibernate.inquiry.SalaryTransactionInquiry;
import com.stream.it.ss.view.jsf.form.transaction.salary.SalarySearchForm;



@Service("financeTransactionService")
public class FinanceTransactionServiceImpl implements FinanceTransactionService{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("financeMonthlyTransactionsInquiry")
	private InquiryDAO inquiryDAO;
	
	@Autowired
	@Qualifier("financeMonthlyTransactionDAO")
	private FinanceMonthlyTransactionDAO financeMonthlyTransactionDAO;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List listTrasnaction(SearchBean searchBean) throws Exception {
		SalarySearchForm salarySearchFormBO = (SalarySearchForm) searchBean;
		List<SalaryTransactionInquiry> resultDataList = new ArrayList<SalaryTransactionInquiry>();

		try {
			salarySearchFormBO.setSqlParameter(Arrays.asList(salarySearchFormBO.getMonth(), salarySearchFormBO.getYear()));
			resultDataList = inquiryDAO.listAll(salarySearchFormBO);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			salarySearchFormBO.getResultBO().setException(e);
			salarySearchFormBO.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultDataList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResultBO createTrasnaction(List transactionList, int month, int year)throws Exception{
		ResultBO resultBO = new ResultBO();
		
		try {
			for(int i=0; i<transactionList.size(); i++){
				SalaryTransactionInquiry salaryTransactionInquiry = (SalaryTransactionInquiry)transactionList.get(i);
				FinanceMonthlyTransaction financeMonthlyTransaction = new FinanceMonthlyTransaction();
				financeMonthlyTransaction.setMonth(				month);
				financeMonthlyTransaction.setYear(				year);
				financeMonthlyTransaction.setUserId(			salaryTransactionInquiry.getUserId());
				financeMonthlyTransaction.setPosition(			salaryTransactionInquiry.getPosition());
				financeMonthlyTransaction.setPayType(			salaryTransactionInquiry.getPayType());
			    financeMonthlyTransaction.setSalary(			salaryTransactionInquiry.getSalary());
			    financeMonthlyTransaction.setDaily(				salaryTransactionInquiry.getDaily());
			    financeMonthlyTransaction.setFare(				salaryTransactionInquiry.getFare());
			    financeMonthlyTransaction.setDiligence(			salaryTransactionInquiry.getDiligence());
			    financeMonthlyTransaction.setBonus(				salaryTransactionInquiry.getBonus());
			    financeMonthlyTransaction.setOtherIncome(		salaryTransactionInquiry.getOtherIncome());
			    financeMonthlyTransaction.setOtSummary(			salaryTransactionInquiry.getOtSummary());
			  
			    financeMonthlyTransaction.setSubtractTax(		salaryTransactionInquiry.getSubtractTax());
			    financeMonthlyTransaction.setSubtractSocial(	salaryTransactionInquiry.getSubtractSocial());
			    financeMonthlyTransaction.setLeaveSubtract(		salaryTransactionInquiry.getLeaveSubtract());
			    financeMonthlyTransaction.setAccumulateSubtract(salaryTransactionInquiry.getAccumulateSubtract());
			    financeMonthlyTransaction.setOtherSubtract(		salaryTransactionInquiry.getOtherSubtract());
			    financeMonthlyTransaction.setDetails(			salaryTransactionInquiry.getDetails());
				
				financeMonthlyTransactionDAO.create(financeMonthlyTransaction);
			}

		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResultBO updateTrasnaction(List transactionList, int month, int year)throws Exception{
		ResultBO resultBO = new ResultBO();

		try {
			Long transactionThisMonth = financeMonthlyTransactionDAO.verifyFinance(month, year);

			if(transactionThisMonth>0){
				financeMonthlyTransactionDAO.deleteByMonthYear(month, year);
				
				for(int i=0; i<transactionList.size(); i++){
					SalaryTransactionInquiry salaryTransactionInquiry = (SalaryTransactionInquiry)transactionList.get(i);
					FinanceMonthlyTransaction financeMonthlyTransaction = new FinanceMonthlyTransaction();
					financeMonthlyTransaction.setMonth(				month);
					financeMonthlyTransaction.setYear(				year);
					financeMonthlyTransaction.setTransactionId(		Integer.parseInt(salaryTransactionInquiry.getId()));
					financeMonthlyTransaction.setUserId(			salaryTransactionInquiry.getUserId());
					financeMonthlyTransaction.setPosition(			salaryTransactionInquiry.getPosition());
					financeMonthlyTransaction.setPayType(			salaryTransactionInquiry.getPayType());
				    financeMonthlyTransaction.setSalary(			salaryTransactionInquiry.getSalary());
				    financeMonthlyTransaction.setDaily(				salaryTransactionInquiry.getDaily());
				    financeMonthlyTransaction.setFare(				salaryTransactionInquiry.getFare());
				    financeMonthlyTransaction.setDiligence(			salaryTransactionInquiry.getDiligence());
				    financeMonthlyTransaction.setBonus(				salaryTransactionInquiry.getBonus());
				    financeMonthlyTransaction.setOtherIncome(		salaryTransactionInquiry.getOtherIncome());
				    financeMonthlyTransaction.setOtSummary(			salaryTransactionInquiry.getOtSummary());
				  
				    financeMonthlyTransaction.setSubtractTax(		salaryTransactionInquiry.getSubtractTax());
				    financeMonthlyTransaction.setSubtractSocial(	salaryTransactionInquiry.getSubtractSocial());
				    financeMonthlyTransaction.setLeaveSubtract(		salaryTransactionInquiry.getLeaveSubtract());
				    financeMonthlyTransaction.setAccumulateSubtract(salaryTransactionInquiry.getAccumulateSubtract());
				    financeMonthlyTransaction.setOtherSubtract(		salaryTransactionInquiry.getOtherSubtract());
				    financeMonthlyTransaction.setDetails(			salaryTransactionInquiry.getDetails());
					financeMonthlyTransactionDAO.update(financeMonthlyTransaction);
				}
			
			}else
				createTrasnaction(transactionList, month, year);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}
}
