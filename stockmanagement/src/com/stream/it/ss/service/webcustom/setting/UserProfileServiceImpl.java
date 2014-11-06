package com.stream.it.ss.service.webcustom.setting;

import java.util.ArrayList;
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
import com.stream.it.ss.hibernate.dao.UsersProfileDAO;
import com.stream.it.ss.hibernate.domain.UsersProfile;
import com.stream.it.ss.hibernate.inquiry.UserProfileInquiry;
import com.stream.it.ss.utils.format.SQLStringType;
import com.stream.it.ss.view.jsf.form.setting.users.UserForm;
import com.stream.it.ss.view.jsf.form.setting.users.UserSearchForm;



@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	@Qualifier("userProfileInquiry")
	private InquiryDAO inquiryDAO;
	
	@Autowired
	@Qualifier("usersProfileDAO")
	private UsersProfileDAO usersProfileDAO;
	
	@Override
	public DataBo findUserProfile(String id, String userId) throws Exception {
		UserForm userFormBO = new UserForm();
		try {
			UsersProfile usersProfile = usersProfileDAO.findByUserId(id, userId);
			userFormBO.setId(				usersProfile.getId());
			userFormBO.setUserId(			usersProfile.getUserId());
			userFormBO.setPassword(			usersProfile.getPassword());
			userFormBO.setConfirmPassword(	usersProfile.getPassword());
			userFormBO.setFirstName(		usersProfile.getFirstName());
			userFormBO.setLastName(			usersProfile.getLastName());
			userFormBO.setDepartment(		usersProfile.getDepartment());
			userFormBO.setPayType(			usersProfile.getPayType());
			userFormBO.setSalary(			usersProfile.getSalary());
			userFormBO.setSocialInsurance(	usersProfile.getSocialInsurance());
			userFormBO.setTax(				usersProfile.getTax());
			userFormBO.setPhone(			usersProfile.getPhone());
			userFormBO.setStatus(			usersProfile.getStatus());
			userFormBO.setFunctionUsed(		usersProfile.getFunctionsUse());
			
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			userFormBO.getResultBO().setException(e);
			userFormBO.getResultBO().setErrorMessage(e.getMessage());
		}
			
		return userFormBO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProfileInquiry> listTransaction(SearchBean searchBean) throws Exception {
		UserSearchForm searchFormBO = (UserSearchForm) searchBean;
		List<UserProfileInquiry> resultDataList = new ArrayList<UserProfileInquiry>();

		try {
			searchFormBO.setConditionValuesBean(new SearchConditionValuesBean[] {
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"ID",			SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(searchFormBO.getUserId()) }),
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"FIRST_NAME",	SQLConstantOperType.LIKE,new Object[] { SQLStringType.likeValue(searchFormBO.getFirstName()) })
			});

			resultDataList = inquiryDAO.listByPage(searchFormBO);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			searchFormBO.getResultBO().setException(e);
			searchFormBO.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultDataList;
	}

	@Override
	public ResultBO createUserProfile(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		UserForm userFormBO = (UserForm)dataBo;
		
		try {
			UsersProfile usersProfile = new UsersProfile();
			usersProfile.setFirstName(		userFormBO.getFirstName());
			usersProfile.setLastName(		userFormBO.getLastName());
			usersProfile.setDepartment(		userFormBO.getDepartment());
			usersProfile.setPhone(			userFormBO.getPhone());
			
			usersProfile.setPayType(		userFormBO.getPayType());
			usersProfile.setSalary(			userFormBO.getSalary());
			usersProfile.setSocialInsurance(userFormBO.getSocialInsurance());
			usersProfile.setTax(			userFormBO.getTax());
			
			usersProfile.setUserId(			userFormBO.getUserId());
			usersProfile.setPassword(		userFormBO.getPassword());
			usersProfile.setStatus(			userFormBO.getStatus());
			usersProfile.setFunctionsUse(	getFunctionMenuList(userFormBO.getFunctionSelected(), userFormBO.getMenuSelected()));
			
			usersProfileDAO.create(usersProfile);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}
	
	@Override
	public ResultBO updateUserProfile(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		UserForm userFormBO = (UserForm)dataBo;
		
		try {
			UsersProfile usersProfile = new UsersProfile();
			usersProfile.setId(				userFormBO.getId());
			usersProfile.setFirstName(		userFormBO.getFirstName());
			usersProfile.setLastName(		userFormBO.getLastName());
			usersProfile.setDepartment(		userFormBO.getDepartment());
			usersProfile.setPhone(			userFormBO.getPhone());

			usersProfile.setPayType(		userFormBO.getPayType());
			usersProfile.setSalary(			userFormBO.getSalary());
			usersProfile.setSocialInsurance(userFormBO.getSocialInsurance());
			usersProfile.setTax(			userFormBO.getTax());
			
			usersProfile.setUserId(			userFormBO.getUserId());
			usersProfile.setPassword(		userFormBO.getPassword());
			usersProfile.setStatus(			userFormBO.getStatus());
			usersProfile.setFunctionsUse(	getFunctionMenuList(userFormBO.getFunctionSelected(), userFormBO.getMenuSelected()));
			
			usersProfileDAO.update(usersProfile);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}

	@Override
	public ResultBO deleteUserProfile(String[] userId) throws Exception {
		ResultBO resultBO = new ResultBO();

		try {
			for (int i = 0; i < userId.length; i++) {
				UsersProfile userProfile = usersProfileDAO.findById(Integer.parseInt(userId[i]));
				usersProfileDAO.delete(userProfile);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}
	
	public ResultBO verifyUserId(String userId)throws Exception {
		ResultBO resultBO = new ResultBO();
		try {
			UsersProfile usersProfile = usersProfileDAO.findByUserId(userId);
			if(usersProfile!=null){
				resultBO.setException(new Exception());
				resultBO.setErrorMessage("User Id is duplicate!!");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			resultBO.setException(e);
			resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}
	
	private String getFunctionMenuList(List functionSelected, List menuSelected){
		StringBuffer functionMenuSelected = new StringBuffer();
		for(int i=0; i<functionSelected.size(); i++)
			functionMenuSelected.append(functionSelected.get(i)+",");

		for(int i=0; i<menuSelected.size(); i++)
			functionMenuSelected.append(menuSelected.get(i)+",");
		
		return functionMenuSelected.toString();
	}	
}
