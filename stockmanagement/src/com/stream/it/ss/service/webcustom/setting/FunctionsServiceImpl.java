package com.stream.it.ss.service.webcustom.setting;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stream.it.ss.base.constant.SQLConstantOperType;
import com.stream.it.ss.base.constant.SQLConstantWhereType;
import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.base.databo.SearchBean;
import com.stream.it.ss.base.databo.SearchConditionValuesBean;
import com.stream.it.ss.hibernate.dao.FunctionsDAO;
import com.stream.it.ss.hibernate.dao.InquiryDAO;
import com.stream.it.ss.hibernate.dao.MenuDAO;
import com.stream.it.ss.hibernate.domain.Functions;
import com.stream.it.ss.hibernate.domain.Menu;
import com.stream.it.ss.utils.format.SQLStringType;
import com.stream.it.ss.utils.format.StringType;
import com.stream.it.ss.view.jsf.form.setting.functions.FunctionsForm;
import com.stream.it.ss.view.jsf.form.setting.functions.FunctionsSearchForm;


@Service("functionsService")
public class FunctionsServiceImpl implements FunctionsService{
	private static final Log logger = LogFactory.getLog(FunctionsServiceImpl.class);
	
	//****** dao ***********// 
	@Autowired
	@Qualifier("functionsDAO")
	private FunctionsDAO functionsDAO;
		
	@Autowired  
	@Qualifier("functionMasterInquiry")
	private InquiryDAO inquiryDAO;
	
	@Autowired
	@Qualifier("menuDAO")
	private MenuDAO menuDAO;
	
	
	//****** entity obj ***********//
	private Functions     functions;
	private FunctionsForm functionsForm;
	
	@Override
	public DataBo findFunctions(String funcId) throws Exception {
	
		functionsForm = new FunctionsForm();
		
		try{
			functions = functionsDAO.findById(funcId);
			
			functionsForm.setFuncId(functions.getFuncId());
			functionsForm.setFuncName(functions.getFuncName());
			functionsForm.setFuncUrl(functions.getFuncUrl());
			
		}catch (Exception e) {
			e.printStackTrace();
	        logger.error(e);
		}
		return functionsForm;
	}

	@Override
	public List listFunctions(SearchBean searchBean) throws Exception {
		FunctionsSearchForm searchForm = (FunctionsSearchForm) searchBean;
		List resultDataList = null;
		
		try{
			searchForm.setConditionValuesBean(new SearchConditionValuesBean[]{
					new SearchConditionValuesBean(SQLConstantWhereType.AND,	"upper(func_id)",SQLConstantOperType.LIKE,new Object[]{SQLStringType.likeValue(searchForm.getfuncId())}),
					new SearchConditionValuesBean(SQLConstantWhereType.AND,	"upper(func_name)",SQLConstantOperType.LIKE,new Object[]{SQLStringType.likeValue(searchForm.getfuncName())})
			});
			
			resultDataList = inquiryDAO.listByPage(searchForm);
		}catch (Exception e) {
			e.printStackTrace();
	        logger.error(e);
	        searchBean.getResultBO().setException(e);
	        searchBean.getResultBO().setErrorMessage(e.getMessage());
		}
		
		return resultDataList;
		
	}
	
	@Override
	public List findFunctionsInMapping(List funcId) throws Exception{		
		SearchBean searchDataBO = new SearchBean();	
		
		searchDataBO.setConditionValuesBean(new SearchConditionValuesBean[]{
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"func_id", SQLConstantOperType.EQUALS, funcId.toArray())
			});
		
        List<?> result = inquiryDAO.listAll(searchDataBO);
	    
        return result;
	}
	
	@Override
	public List findFunctionsNotInMapping(List funcId) throws Exception{		
		SearchBean searchDataBO = new SearchBean();	
		
		searchDataBO.setConditionValuesBean(new SearchConditionValuesBean[]{
				new SearchConditionValuesBean(SQLConstantWhereType.AND,	"func_id", SQLConstantOperType.NOT_EQUALS, funcId.toArray())
			});
		
        List<?> result = inquiryDAO.listAll(searchDataBO);
	    
        return result;
	}
	
	@Override
	public ResultBO createFunctions(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		FunctionsForm functionsForm  = (FunctionsForm) dataBo;
		Functions functions = new Functions(); 
		
		try{
			functions.setFuncId(functionsForm.getFuncId());
			functions.setFuncName(functionsForm.getFuncName());
			functions.setFuncUrl(functionsForm.getFuncUrl());
				
			functionsDAO.create(functions);
		}catch(Exception e){
			 e.printStackTrace();
            logger.error(e);
            resultBO.setException(e);
            resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}

	@Override
	public ResultBO updateFunctions(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		FunctionsForm functionsForm  = (FunctionsForm) dataBo;
		Functions functions = new Functions(); 
		
		try{
			functions.setFuncId(functionsForm.getFuncId());
			functions.setFuncName(functionsForm.getFuncName());
			functions.setFuncUrl(functionsForm.getFuncUrl());
				
			functionsDAO.update(functions);
			
			List<Menu> menus = menuDAO.findMenuListBySourceId(functionsForm.getFuncId());
			
			for(Menu menu : menus){				
								
				if(menu.getMenuSourceId().indexOf("[") == 0){
										
					String newMenuSourceDesc = "";
				
					if(menu.getMenuSourceId().indexOf(functionsForm.getFuncId()) > 0){
		
						newMenuSourceDesc+=functions.getFuncName();
					}				
				
					menu.setMenuSourceDesc(newMenuSourceDesc);
					menuDAO.update(menu);
				
				}else if(menu.getMenuSourceId().indexOf(",") > 0){
				
					String[] menuSourceId = menu.getMenuSourceId().split(",");
					String[] menuSourceDesc = menu.getMenuSourceDesc().split(",");
									
					String newMenuSourceDesc = "";
				
					for(int j=0; j<menuSourceId.length; j++){
						if(functionsForm.getFuncId().equals(menuSourceId[j])){
							
							menuSourceDesc[j] = functions.getFuncName();
							
							newMenuSourceDesc+=menuSourceDesc[j];
							
						}else{		
							newMenuSourceDesc+=menuSourceDesc[j];

						}	
						
						if(j!=menuSourceId.length-1){
							newMenuSourceDesc+=",";
						}
					}	
					
					menu.setMenuSourceDesc(newMenuSourceDesc);
					menuDAO.update(menu);
					
				}else{
					
					String newMenuSourceDesc = "";
					newMenuSourceDesc=functions.getFuncName();
										
					menu.setMenuSourceDesc(newMenuSourceDesc);
					menuDAO.update(menu);
					
				}		
			}
							
		}catch(Exception e){
			 e.printStackTrace();
            logger.error(e);
            resultBO.setException(e);
            resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}

	@Override
	public ResultBO deleteFunctions(String FunctionsId) throws Exception {
		ResultBO resultBO = new ResultBO();
		Functions functions = new Functions(); 
		
		try{
				functions.setFuncId(FunctionsId);
				functions.setFuncName("");
				functions.setFuncUrl("");
				functionsDAO.delete(functions);
	
		}catch (Exception e) {
			e.printStackTrace();
	         logger.error(e);
	         resultBO.setException(e);
	         resultBO.setErrorMessage(e.getMessage());
		}
		return resultBO;
	}
	
	@Override
	public List listAllFunctions() throws Exception {
		List resultDataList = null;
		
		try{
			resultDataList = functionsDAO.listAll();
			
		}catch (Exception e) {
			e.printStackTrace();
	        logger.error(e);
		}
		
		return resultDataList;
	}
	
	@Override
	public String findMaxFnId() throws Exception{
		return genFunctionId(StringType.getString(functionsDAO.findMAXId()));
	}
	
	@Override
	public String findFnUrlbyName(String funcName) throws Exception {
		return functionsDAO.findFnUrlbyName(funcName);
	}
	
	@Override
	public boolean findFnName(String functionName) throws Exception {
		return functionsDAO.findFnNamebyName(functionName);
	}
	
	private String genFunctionId(String maxId) throws Exception{
		String value = "";
		String tmp = "";
		if(maxId != null && !maxId.equals("null") && !maxId.equals("")){
			value = String.valueOf(Integer.parseInt(maxId.substring(3,4))+1);
			maxId = "FC0"+value; 
			for (int i = 0; maxId.length() < 3; i++) {
				tmp += "0";
				maxId = "FC"+tmp+value;
			}
		}else
			maxId = "FC01";
		
		return maxId;
	}
	//***** getter setter *****//
	public void setInquiryDAO(InquiryDAO inquiryDAO) {
		this.inquiryDAO = inquiryDAO;
	}

	public void setFunctionsDAO(FunctionsDAO functionsDAO) {
		this.functionsDAO = functionsDAO;
	}
}
