package com.stream.it.ss.service.webcustom.transaction.salary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stream.it.ss.base.constant.SQLConstantOperType;
import com.stream.it.ss.base.constant.SQLConstantWhereType;
import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.base.databo.SearchBean;
import com.stream.it.ss.base.databo.SearchConditionValuesBean;
import com.stream.it.ss.hibernate.dao.InquiryDAO;
import com.stream.it.ss.hibernate.dao.SalaryTrasnactionDAO;
import com.stream.it.ss.hibernate.domain.SalaryTransaction;
import com.stream.it.ss.hibernate.inquiry.SalaryTransactionInquiry;
import com.stream.it.ss.utils.format.DateUtil;
import com.stream.it.ss.utils.format.SQLStringType;
import com.stream.it.ss.view.jsf.form.transaction.salary.SalaryDailyForm;
import com.stream.it.ss.view.jsf.form.transaction.salary.SalaryIncomeForm;
import com.stream.it.ss.view.jsf.form.transaction.salary.SalaryOTForm;
import com.stream.it.ss.view.jsf.form.transaction.salary.SalarySearchForm;



@Service("salaryManagementService")
public class SalaryManagementServiceImpl implements SalaryManagementService{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("salaryTransactionsInquiry")
	private InquiryDAO inquiryDAO;

	@Autowired
	@Qualifier("summarySalaryTransactionsInquiry")
	private InquiryDAO summarySalaryTransactionsInquiryDAO;

	
	@Autowired
	@Qualifier("salaryOtTransactionsInquiry")
	private InquiryDAO salaryOtTransactionsInquiryDAO;
	
	@Autowired
	@Qualifier("salarySalaryDailyTransactionsInquiry")
	private InquiryDAO salarySalaryDailyTransactionsInquiryDAO;
	
	@Autowired
	@Qualifier("salaryTrasnactionDAO")
	private SalaryTrasnactionDAO salaryTrasnactionDAO;
	
	
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
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear()
					));
			
			salarySearchFormBO.setConditionValuesBean(new SearchConditionValuesBean[] { new SearchConditionValuesBean(SQLConstantWhereType.AND,	"FIRST_NAME",		SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(salarySearchFormBO.getUserName()) })});
			
			resultDataList = inquiryDAO.listByPage(salarySearchFormBO);
			
			if(!resultDataList.isEmpty()){
				SalarySearchForm summarySalarySearchFormBO = new SalarySearchForm();
				summarySalarySearchFormBO.setSqlParameter(Arrays.asList(
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), 
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(),
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear()
					));
			
				summarySalarySearchFormBO.setConditionValuesBean(new SearchConditionValuesBean[] { new SearchConditionValuesBean(SQLConstantWhereType.AND,	"FIRST_NAME",		SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(salarySearchFormBO.getUserName()) })});
			
				
				List<SalaryTransactionInquiry> totalSumResultDataList = summarySalaryTransactionsInquiryDAO.listAll(summarySalarySearchFormBO);
				
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List listTrasnactionAll(SearchBean searchBean) throws Exception {
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
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear()
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
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear()
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List listTrasnactionByUser(SearchBean searchBean)throws Exception{
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
						salarySearchFormBO.getMonth(), salarySearchFormBO.getYear(), salarySearchFormBO.getMonth(), salarySearchFormBO.getYear()
					));
			
			salarySearchFormBO.setConditionValuesBean(new SearchConditionValuesBean[] { 
					new SearchConditionValuesBean(SQLConstantWhereType.AND,	"UPPER(USER_ID)",	SQLConstantOperType.EQUALS, new Object[] { salarySearchFormBO.getUserId() }),
					new SearchConditionValuesBean(SQLConstantWhereType.AND,	"ID",				SQLConstantOperType.EQUALS, new Object[] { salarySearchFormBO.getId() })
			});
			
			resultDataList = inquiryDAO.listByPage(salarySearchFormBO);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			salarySearchFormBO.getResultBO().setException(e);
			salarySearchFormBO.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultDataList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List listOtByUserTrasnaction(SearchBean searchBean) throws Exception {
		SalarySearchForm salarySearchFormBO = (SalarySearchForm) searchBean;
		List<SalaryTransactionInquiry> resultDataList = new ArrayList<SalaryTransactionInquiry>();

		try {
			salarySearchFormBO.setSqlParameter(Arrays.asList(
						salarySearchFormBO.getUserId(), 
						salarySearchFormBO.getId(),
						salarySearchFormBO.getMonth(), 
						salarySearchFormBO.getYear()
					));
			
			resultDataList = salaryOtTransactionsInquiryDAO.listAll(salarySearchFormBO);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			salarySearchFormBO.getResultBO().setException(e);
			salarySearchFormBO.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultDataList;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List listSalaryDailyByUserTrasnaction(SearchBean searchBean)throws Exception {
		SalarySearchForm salarySearchFormBO = (SalarySearchForm) searchBean;
		List<SalaryTransactionInquiry> resultDataList = new ArrayList<SalaryTransactionInquiry>();

		try {
			salarySearchFormBO.setSqlParameter(Arrays.asList(
						salarySearchFormBO.getUserId(), 
						salarySearchFormBO.getId(),
						salarySearchFormBO.getMonth(), 
						salarySearchFormBO.getYear()
					));
			
			resultDataList = salarySalaryDailyTransactionsInquiryDAO.listAll(salarySearchFormBO);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			salarySearchFormBO.getResultBO().setException(e);
			salarySearchFormBO.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultDataList;
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public ResultBO createOt(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		SalaryOTForm salaryOTForm = (SalaryOTForm)dataBo;
		
		try {
			SalaryTransaction salaryTransaction = new SalaryTransaction();
			salaryTransaction.setTransactionType(	"ot");
			salaryTransaction.setId(				salaryOTForm.getId());
			salaryTransaction.setUserId(			salaryOTForm.getUserId());
			salaryTransaction.setSalary(			salaryOTForm.getSalary());
			salaryTransaction.setDaily(				salaryOTForm.getDaily());
			salaryTransaction.setFare(				salaryOTForm.getFare());
			salaryTransaction.setDiligence(			salaryOTForm.getDiligence());
			salaryTransaction.setBonus(				salaryOTForm.getBonus());
			salaryTransaction.setOtHour(			salaryOTForm.getOtHour().doubleValue());
			salaryTransaction.setOtRate(			salaryOTForm.getOtRate());
			salaryTransaction.setSocialInsurance(	salaryOTForm.getSocialInsurance());
			salaryTransaction.setTax(				salaryOTForm.getTax());
			
			salaryTransaction.setDate(				salaryOTForm.getOtDate().getDate());
			salaryTransaction.setMonth(				Integer.parseInt(DateUtil.getMonthByDate(salaryOTForm.getOtDate())));
			salaryTransaction.setYear(				Integer.parseInt(DateUtil.getYearByDate(salaryOTForm.getOtDate())));
			
			salaryTrasnactionDAO.create(salaryTransaction);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}	
	
	@Override
	public ResultBO deleteOtTrasnactionByUser(String[] transactionId) throws Exception {
		ResultBO resultBO = new ResultBO();

		try {
			for (int i = 0; i < transactionId.length; i++) {
				SalaryTransaction salaryTransaction = new SalaryTransaction();
				salaryTransaction.setTransactionId(Integer.parseInt(transactionId[i]));
				salaryTrasnactionDAO.delete(salaryTransaction);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ResultBO createDaily(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		SalaryDailyForm salaryDailyForm = (SalaryDailyForm)dataBo;
		
		try {
			SalaryTransaction salaryTransaction = new SalaryTransaction();
			salaryTransaction.setTransactionType(	"daily");
			salaryTransaction.setUserId(			salaryDailyForm.getUserId());
			salaryTransaction.setId(				salaryDailyForm.getId());
			salaryTransaction.setDaily(				salaryDailyForm.getIncome());
			salaryTransaction.setDate(				salaryDailyForm.getDate().getDate());
			salaryTransaction.setMonth(				Integer.parseInt(DateUtil.getMonthByDate(salaryDailyForm.getDate())));
			salaryTransaction.setYear(				Integer.parseInt(DateUtil.getYearByDate(salaryDailyForm.getDate())));
			
			salaryTrasnactionDAO.create(salaryTransaction);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}	
	
	@Override
	public ResultBO deleteDailyTrasnactionByUser(String[] transactionId) throws Exception {
		ResultBO resultBO = new ResultBO();

		try {
			for (int i = 0; i < transactionId.length; i++) {
				SalaryTransaction salaryTransaction = new SalaryTransaction();
				salaryTransaction.setTransactionId(Integer.parseInt(transactionId[i]));
				salaryTrasnactionDAO.delete(salaryTransaction);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}
	
	
	@Override
	public ResultBO updateIncome(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		SalaryIncomeForm salaryIncomeForm = (SalaryIncomeForm)dataBo;
		
		try {
			
			List<SalaryTransaction> listSalaryByUser =  salaryTrasnactionDAO.listByUserId(salaryIncomeForm.getIncomeType(), salaryIncomeForm.getUserId(), salaryIncomeForm.getId(), salaryIncomeForm.getMonth(), salaryIncomeForm.getYear());
			if(listSalaryByUser.isEmpty()){
				SalaryTransaction salaryTransaction = new SalaryTransaction();
				salaryTransaction.setDate(1);
				salaryTransaction.setMonth(		salaryIncomeForm.getMonth());
				salaryTransaction.setYear(		salaryIncomeForm.getYear());
				salaryTransaction.setUserId(	salaryIncomeForm.getUserId());
				salaryTransaction.setId(		salaryIncomeForm.getId());
				
				salaryTransaction.setTransactionType(salaryIncomeForm.getIncomeType());
				
				if(salaryIncomeForm.getIncomeType().equals("daily"))
					salaryTransaction.setDaily(	salaryIncomeForm.getIncome());
					
				else if(salaryIncomeForm.getIncomeType().equals("fare"))
					salaryTransaction.setFare(	salaryIncomeForm.getIncome());
					
				else if(salaryIncomeForm.getIncomeType().equals("diligence"))
					salaryTransaction.setDiligence(	salaryIncomeForm.getIncome());
				
				else if(salaryIncomeForm.getIncomeType().equals("bonus"))
					salaryTransaction.setBonus(	salaryIncomeForm.getIncome());
				
				else if(salaryIncomeForm.getIncomeType().equals("social"))
					salaryTransaction.setSocialInsurance(	salaryIncomeForm.getIncome());
				
				else if(salaryIncomeForm.getIncomeType().equals("tax"))
					salaryTransaction.setTax(	salaryIncomeForm.getIncome());
				
				else if(salaryIncomeForm.getIncomeType().equals("otherIncome"))
					salaryTransaction.setOtherIncome(	salaryIncomeForm.getIncome());
				
				else if(salaryIncomeForm.getIncomeType().equals("subtractOther"))
					salaryTransaction.setOtherSubtract(	salaryIncomeForm.getIncome());

				else if(salaryIncomeForm.getIncomeType().equals("leaveSubtract"))
					salaryTransaction.setLeaveSubtract(	salaryIncomeForm.getIncome());

				else if(salaryIncomeForm.getIncomeType().equals("accumulateSubtract"))
					salaryTransaction.setAccumulateSubtract(	salaryIncomeForm.getIncome());
				
				salaryTrasnactionDAO.create(salaryTransaction);
				
			}else{
				for(SalaryTransaction salaryTransaction : listSalaryByUser){
					if(salaryIncomeForm.getIncomeType().equals("daily"))
						salaryTransaction.setDaily(	salaryIncomeForm.getIncome());
						
					else if(salaryIncomeForm.getIncomeType().equals("fare"))
						salaryTransaction.setFare(	salaryIncomeForm.getIncome());
						
					else if(salaryIncomeForm.getIncomeType().equals("diligence"))
						salaryTransaction.setDiligence(	salaryIncomeForm.getIncome());
					
					else if(salaryIncomeForm.getIncomeType().equals("bonus"))
						salaryTransaction.setBonus(	salaryIncomeForm.getIncome());
					
					else if(salaryIncomeForm.getIncomeType().equals("social"))
						salaryTransaction.setSocialInsurance(	salaryIncomeForm.getIncome());
					
					else if(salaryIncomeForm.getIncomeType().equals("tax"))
						salaryTransaction.setTax(	salaryIncomeForm.getIncome());
					
					else if(salaryIncomeForm.getIncomeType().equals("otherIncome"))
						salaryTransaction.setOtherIncome(	salaryIncomeForm.getIncome());
					
					else if(salaryIncomeForm.getIncomeType().equals("subtractOther"))
						salaryTransaction.setOtherSubtract(	salaryIncomeForm.getIncome());

					else if(salaryIncomeForm.getIncomeType().equals("leaveSubtract"))
						salaryTransaction.setLeaveSubtract(	salaryIncomeForm.getIncome());

					else if(salaryIncomeForm.getIncomeType().equals("accumulateSubtract"))
						salaryTransaction.setAccumulateSubtract(	salaryIncomeForm.getIncome());
					
					salaryTrasnactionDAO.update(salaryTransaction);
				}
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}
	
	@Override
	public ResultBO deleteIncome(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		SalaryIncomeForm salaryIncomeForm = (SalaryIncomeForm)dataBo;
		
		try {
			
			List<SalaryTransaction> listSalaryByUser =  salaryTrasnactionDAO.listByUserId(salaryIncomeForm.getIncomeType(), salaryIncomeForm.getUserId(), salaryIncomeForm.getId(), salaryIncomeForm.getMonth(), salaryIncomeForm.getYear());
			
			for(SalaryTransaction salaryTransaction : listSalaryByUser){
				salaryTrasnactionDAO.delete(salaryTransaction);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}
	
	public ResultBO updateDetails(DataBo dataBo)throws Exception{
		ResultBO resultBO = new ResultBO();
		SalaryIncomeForm salaryIncomeForm = (SalaryIncomeForm)dataBo;
		
		try {
			
			List<SalaryTransaction> listSalaryByUser =  salaryTrasnactionDAO.listByUserId(salaryIncomeForm.getIncomeType(), salaryIncomeForm.getUserId(), salaryIncomeForm.getId(), salaryIncomeForm.getMonth(), salaryIncomeForm.getYear());
			if(listSalaryByUser.isEmpty()){
				SalaryTransaction salaryTransaction = new SalaryTransaction();
				salaryTransaction.setDate(1);
				salaryTransaction.setMonth(		salaryIncomeForm.getMonth());
				salaryTransaction.setYear(		salaryIncomeForm.getYear());
				salaryTransaction.setUserId(	salaryIncomeForm.getUserId());
				salaryTransaction.setId(		salaryIncomeForm.getId());
				
				salaryTransaction.setTransactionType(salaryIncomeForm.getIncomeType());
				salaryTransaction.setDetails(	salaryIncomeForm.getDetails());
				
				salaryTrasnactionDAO.create(salaryTransaction);
				
			}else{
				for(SalaryTransaction salaryTransaction : listSalaryByUser){
					salaryTransaction.setDetails(	salaryIncomeForm.getDetails());

					salaryTrasnactionDAO.update(salaryTransaction);
				}
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;		
	}

}
