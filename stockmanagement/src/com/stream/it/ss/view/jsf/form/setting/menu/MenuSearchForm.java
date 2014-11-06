package com.stream.it.ss.view.jsf.form.setting.menu;

import com.stream.it.ss.base.databo.SearchBean;

public class MenuSearchForm extends SearchBean{

	private String menuId;
	private String menuName;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
}
