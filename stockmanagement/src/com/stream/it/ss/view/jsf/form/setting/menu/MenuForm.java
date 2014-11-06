package com.stream.it.ss.view.jsf.form.setting.menu;

import java.util.ArrayList;
import java.util.List;

import com.stream.it.ss.base.databo.DataBo;

public class MenuForm extends DataBo{

	private String menuId;
	private String menuName;
	private String menuSourceId;
	private String menuSourceDesc;
	private List<?> functionSelected = new ArrayList();
    private String menuPreview;
    private String funcNamePreview;
    private String eventFunc;
	
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
	public List<?> getFunctionSelected() {
		return functionSelected;
	}
	public void setFunctionSelected(List<?> functionSelected) {
		this.functionSelected = functionSelected;
	}
	
	public String getMenuSourceId() {
		return menuSourceId;
	}
	public void setMenuSourceId(String menuSourceId) {
		this.menuSourceId = menuSourceId;
	}
	public String getMenuPreview() {
		return menuPreview;
	}
	public void setMenuPreview(String menuPreview) {
		this.menuPreview = menuPreview;
	}
	public String getFuncNamePreview() {
		return funcNamePreview;
	}
	public void setFuncNamePreview(String funcNamePreview) {
		this.funcNamePreview = funcNamePreview;
	}
	public String getMenuSourceDesc() {
		return menuSourceDesc;
	}
	public void setMenuSourceDesc(String menuSourceDesc) {
		this.menuSourceDesc = menuSourceDesc;
	}
	public String getEventFunc() {
		return eventFunc;
	}
	public void setEventFunc(String eventFunc) {
		this.eventFunc = eventFunc;
	}
	 
}
