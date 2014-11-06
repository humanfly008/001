package com.stream.it.ss.service.webcustom.setting;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stream.it.ss.base.constant.SQLConstantOperType;
import com.stream.it.ss.base.constant.SQLConstantWhereType;
import com.stream.it.ss.base.databo.SearchBean;
import com.stream.it.ss.base.databo.SearchConditionValuesBean;
import com.stream.it.ss.hibernate.dao.InquiryDAO;
import com.stream.it.ss.hibernate.inquiry.GroupMenuInquiry;
import com.stream.it.ss.hibernate.inquiry.SubMenuInquiry;

@Service("renderMenuService")
public class RenderMenuServiceImpl implements RenderMenuService {
	private static final Log logger = LogFactory.getLog(RenderMenuServiceImpl.class);

	//****** dao ***********// 
	@Autowired
	@Qualifier("functionRanderMenuInquiry")
	private InquiryDAO inquiryDAO;
	
	@Autowired
	@Qualifier("functionRanderSubMenuInquiry")
	private InquiryDAO inquirySubMenuDAO;
	
	@Autowired
	@Qualifier("menuMasterInquiry")
	private InquiryDAO menuMasterInquiryDAO;
	
	@Override
	public List<GroupMenuInquiry> renderMenuByGroup(String gid) throws Exception {
		List resultDataList = null;
		try{
			
			List param = new ArrayList();
			String gidTemp[] = gid.trim().split(",");
			for(int i = 0 ;i< gidTemp.length ; i++){
				if(gidTemp[i] != "")
				param.add(gidTemp[i].trim());
			}
			
			SearchBean searchBean = new SearchBean();
			
			searchBean.setConditionValuesBean(new SearchConditionValuesBean[]{
					new SearchConditionValuesBean(SQLConstantWhereType.AND,	"G.G_ID",SQLConstantOperType.EQUALS,param.toArray())
			});
		
			resultDataList =  inquiryDAO.listAll(searchBean);
			
		}catch (Exception e) {
			e.printStackTrace();
	        logger.error(e);
		}
		
		return resultDataList;
	}
	
	@Override
	public List<SubMenuInquiry> renderSubMenuByGroup(String func_id) throws Exception {
		List resultDataList = null;
		List param = new ArrayList();
		try{
			if(func_id.startsWith("[")){
				String func = func_id.replace("[", " ").replace("]", " ");
				param.add(func.trim());
			}
			else{
				//System.out.println(func_id);
				String func_idTemp[] = func_id.trim().split(",");
				for(int i = 0 ;i< func_idTemp.length ; i++){
					if(func_idTemp[i] != "")
					param.add(func_idTemp[i].trim());
				}
			}

			//F.FUNC_ID
			SearchBean searchBean = new SearchBean();
			searchBean.setConditionValuesBean(new SearchConditionValuesBean[]{
					new SearchConditionValuesBean(SQLConstantWhereType.AND,	"F.FUNC_ID",SQLConstantOperType.EQUALS,param.toArray())
			});
			resultDataList =  inquirySubMenuDAO.listAll(searchBean);

		}catch (Exception e) {
			e.printStackTrace();
	        logger.error(e);
		}
		
		return resultDataList;
	}
	
	@Override
	public List renderMenuByFunction(String[]functionList)throws Exception{
		List resultDataList = null;
		try{
			
			List param = new ArrayList();
			for(int i = 0 ;i< functionList.length ; i++){
				if(functionList[i] != "")
					param.add(functionList[i].trim());
			}
			
			SearchBean searchBean = new SearchBean();
			searchBean.setConditionValuesBean(new SearchConditionValuesBean[]{
					new SearchConditionValuesBean(SQLConstantWhereType.AND,	"MENU_ID",SQLConstantOperType.EQUALS,param.toArray())
			});
		
			resultDataList =  menuMasterInquiryDAO.listAll(searchBean);
			
		}catch (Exception e) {
			e.printStackTrace();
	        logger.error(e);
		}
		
		return resultDataList;
	}
	

	@Override
	public List renderMenuByMenu(String mid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public InquiryDAO getInquiryDAO() {
		return inquiryDAO;
	}

	public void setInquiryDAO(InquiryDAO inquiryDAO) {
		this.inquiryDAO = inquiryDAO;
	}

	public static Log getLogger() {
		return logger;
	}

	public InquiryDAO getInquirySubMenuDAO() {
		return inquirySubMenuDAO;
	}

	public void setInquirySubMenuDAO(InquiryDAO inquirySubMenuDAO) {
		this.inquirySubMenuDAO = inquirySubMenuDAO;
	}

}
