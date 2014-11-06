package com.stream.it.ss.service.webcustom.setting;

import java.util.List;

import com.stream.it.ss.hibernate.inquiry.GroupMenuInquiry;
import com.stream.it.ss.hibernate.inquiry.SubMenuInquiry;

public interface RenderMenuService {
	public List<GroupMenuInquiry> renderMenuByGroup(String gid)throws Exception;
	public List renderMenuByMenu(String mid)throws Exception;
	public List<SubMenuInquiry> renderSubMenuByGroup(String func_id) throws Exception;
	public List renderMenuByFunction(String[]functionList)throws Exception;
}
