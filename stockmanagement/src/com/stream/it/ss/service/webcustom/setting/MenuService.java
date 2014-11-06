package com.stream.it.ss.service.webcustom.setting;

import java.util.List;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.base.databo.SearchBean;

public interface MenuService {
	public DataBo findMenu(String menuId)throws Exception;
    public List searchList(SearchBean searchBean)throws Exception;
    public List listAllMenu()throws Exception;
    public ResultBO createMenu(DataBo dataBo)throws Exception;
    public ResultBO updateMenu(DataBo dataBo)throws Exception;
    public ResultBO deleteMenu(String[] menuId)throws Exception;
    public String findMenuId() throws Exception;
}
