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
import com.stream.it.ss.hibernate.dao.InquiryDAO;
import com.stream.it.ss.hibernate.dao.MenuDAO;
import com.stream.it.ss.hibernate.domain.Menu;
import com.stream.it.ss.utils.format.SQLStringType;
import com.stream.it.ss.utils.format.StringType;
import com.stream.it.ss.view.jsf.base.DisplayMessages;
import com.stream.it.ss.view.jsf.form.setting.menu.MenuForm;
import com.stream.it.ss.view.jsf.form.setting.menu.MenuSearchForm;

@Service("menuService")
public class MenuServiceImpl implements MenuService{
private static final Log logger = LogFactory.getLog(MenuServiceImpl.class);
	
	//****** dao ***********// 
	@Autowired
	@Qualifier("menuMasterInquiry")
	private InquiryDAO inquiryDAO;
	
	@Autowired
	@Qualifier("menuDAO")
	private MenuDAO menuDAO;
	
	//****** entity obj ***********//
	private MenuForm menuForm;
	private Menu menu;

	@Override
	public DataBo findMenu(String menuId) throws Exception {
		menuForm = new MenuForm();
		
		try{
			menu = menuDAO.findById(menuId);
			menuForm.setMenuId(menuId);
			menuForm.setMenuName(menu.getMenuName());
			String[] str = null;
			
			if(menu.getMenuSourceId().substring(0,1).equals("[")){
				String funcId = menu.getMenuSourceId();
				str = funcId.split("\\[");
				funcId = str[1].toString();
				str = funcId.split("\\]");
				menuForm.setEventFunc(str[0].toString()+","+menu.getMenuSourceDesc());	
			}else{
				menuForm.setEventFunc("none");	
			}
			menuForm.setMenuSourceId(menu.getMenuSourceId());
			menuForm.setMenuSourceDesc(menu.getMenuSourceDesc());
		}catch (Exception e) {
			e.printStackTrace();
	        logger.error(e);
		}
		return menuForm;
	}
	
	@Override
	public List searchList(SearchBean searchBean) throws Exception {
		
		MenuSearchForm searchForm = (MenuSearchForm) searchBean;
		List resultDataList = null;
		
		try{
			searchForm.setConditionValuesBean(new SearchConditionValuesBean[]{
					new SearchConditionValuesBean(SQLConstantWhereType.AND,	"upper(menu_id)",SQLConstantOperType.LIKE,new Object[]{SQLStringType.likeValue(searchForm.getMenuId())}),
					new SearchConditionValuesBean(SQLConstantWhereType.AND,	"upper(menu_name)",SQLConstantOperType.LIKE,new Object[]{SQLStringType.likeValue(searchForm.getMenuName())})
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
	public List listAllMenu() throws Exception {
		
		List resultDataList = null;
		
		try{
			resultDataList = menuDAO.listAll();
			
		}catch (Exception e) {
			e.printStackTrace();
	        logger.error(e);
		}
		
		return resultDataList;
		
	}

	@Override
	public ResultBO createMenu(DataBo dataBo) throws Exception {
		
		ResultBO resultBO = new ResultBO();
		MenuForm menuForm = (MenuForm) dataBo;
		Menu menu = new Menu();
		String[]  splitfunc = null;
		String    sourceId = "";
		String    sourceDesc = "";		
		int count = 1;
		try{
			if(menuForm.getEventFunc().equals("none")){
				for(Object funcList : menuForm.getFunctionSelected()){
					splitfunc  = ((String) funcList).split(",");
					if(menuForm.getFunctionSelected().size()==count){
						sourceId+= splitfunc[0].trim().toString();
						sourceDesc+= splitfunc[1].trim().toString();
					}else{
						sourceId+= splitfunc[0].trim().toString()+",";
						sourceDesc+= splitfunc[1].trim().toString()+",";
					}
					count++;	
				}
			}else{
				splitfunc  = menuForm.getEventFunc().split(",");
				sourceId= "["+splitfunc[0].trim().toString()+"]";
				sourceDesc= splitfunc[1].trim().toString();
			}
			
			menu.setMenuId(menuForm.getMenuId());
			menu.setMenuName(menuForm.getMenuName());
			
			menu.setMenuSourceId(sourceId);
			menu.setMenuSourceDesc(sourceDesc);
				
			menuDAO.create(menu);
			
			
		}catch(Exception e){
			 e.printStackTrace();
             logger.error(e);
             resultBO.setException(e);
             resultBO.setErrorMessage(e.getMessage());
		}
		
		return resultBO;
	}

	@Override
	public ResultBO updateMenu(DataBo dataBo) throws Exception {
		ResultBO resultBO = new ResultBO();
		menuForm = (MenuForm) dataBo;
		Menu menu = new Menu();
		String sourceId = "";
		String sourceDesc = "";
		String[]  splitfunc = null;
		int count = 1;
		
		try{
			if(menuForm.getEventFunc().equals("none")){
				for(Object funcList : menuForm.getFunctionSelected()){
					splitfunc  = ((String) funcList).split(",");
					if(menuForm.getFunctionSelected().size()==count){
						sourceId+= splitfunc[0].trim().toString();
						sourceDesc+= splitfunc[1].trim().toString();
					}else{
						sourceId+= splitfunc[0].trim().toString()+",";
						sourceDesc+= splitfunc[1].trim().toString()+",";
					}
					count++;	
				}
			}else{
				splitfunc  = menuForm.getEventFunc().split(",");
				sourceId= "["+splitfunc[0].trim().toString()+"]";
				sourceDesc= splitfunc[1].trim().toString();
			}
			menu.setMenuId(menuForm.getMenuId());
			menu.setMenuName(menuForm.getMenuName());
			menu.setMenuSourceDesc(sourceDesc);
			menu.setMenuSourceId(sourceId);
			menuDAO.update(menu);
			
		}catch(Exception e){
            e.printStackTrace();
            logger.error(e);
            resultBO.setException(e);
            resultBO.setErrorMessage(e.getMessage());
        }
        
        return resultBO;
	}

	@Override
	public ResultBO deleteMenu(String[] menuId) throws Exception {
		ResultBO resultBO = new ResultBO();
		menu = new Menu();
		
		try{
			for(int i=0 ; i< menuId.length ; i++){
				menu.setMenuId(menuId[i]);
				menu.setMenuName("");
				menu.setMenuSourceId("");
				menu.setMenuSourceDesc("");
				menuDAO.delete(menu);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
	         logger.error(e);
	         resultBO.setException(e);
	         resultBO.setErrorMessage(e.getMessage());
	         DisplayMessages.showErrorMessage("Can't delete menu. this menu are using");
		}
		return resultBO;
		
	}
	@Override
	public String findMenuId() throws Exception{
		String maxId = "";
		maxId = genMenuId(StringType.getString(menuDAO.findMAXId()));
		
		return maxId;
	}
	
	private String genMenuId(String maxId) throws Exception{
		String value = "";
		String tmp = "";
		if(maxId != null && !maxId.equals("null") && !maxId.equals("")){
			value = String.valueOf(Integer.parseInt(maxId.substring(2,3))+1);
			maxId = "M0"+value; 
			for (int i = 0; maxId.length() < 3; i++) {
				tmp += "0";
				maxId = "M"+tmp+value;
			}
		}else
			maxId = "M01";
		
		return maxId;
	}
	
	
	
	//********** getter setter ************//
	public void setInquiryDAO(InquiryDAO inquiryDAO) {
		this.inquiryDAO = inquiryDAO;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	

}
