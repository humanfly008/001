package com.stream.it.ss.hibernate.inquiry;

import java.io.Serializable;

public class GroupMenuInquiry  implements Serializable {
	private String menuName;
	private String menuSourceId;
	private String menuId;
	private String gId;
	
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
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getgId() {
		return gId;
	}
	public void setgId(String gId) {
		this.gId = gId;
	}

}
