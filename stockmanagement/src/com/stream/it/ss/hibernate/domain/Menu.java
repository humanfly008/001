package com.stream.it.ss.hibernate.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MENU")
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MENU_ID",nullable=false,length=10)
    private String menuId;
    @Basic(optional = false)
    @Column(name = "MENU_NAME",nullable=false,length=200)
    private String menuName;
    @Column(name = "MENU_SOURCE_ID")
    private String menuSourceId;
    @Column(name = "MENU_SOURCE_DESC")
    private String menuSourceDesc;
    
    public Menu() {
    }

    public Menu(String menuId) {
        this.menuId = menuId;
    }

    public Menu(String menuId, String menuName, String menuSourceId, String menuSourceDesc) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuSourceId = menuSourceId;
        this.menuSourceDesc = menuSourceDesc;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuId != null ? menuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.menuId == null && other.menuId != null) || (this.menuId != null && !this.menuId.equals(other.menuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.stream.it.ss.hibernate.domain.Menu[ menuId=" + menuId + " ]";
    }
    
}
