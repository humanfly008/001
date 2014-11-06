package com.stream.it.ss.hibernate.inquiry;

import java.io.Serializable;

public class MenuInquery implements Serializable{
	private String no;
	private String menuId;
	private String menuName;
	private String menuSourceId;
	private String menuSourceDesc;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
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
	public String getMenuSourceId() {
		return menuSourceId;
	}
	public void setMenuSourceId(String menuSourceId) {
		this.menuSourceId = menuSourceId;
	}
	public String getMenuSourceDesc() {
		return menuSourceDesc;
	}
	public void setMenuSourceDesc(String menuSourceDesc) {
		this.menuSourceDesc = menuSourceDesc;
	}
	
	

}
