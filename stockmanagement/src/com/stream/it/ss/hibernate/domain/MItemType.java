package com.stream.it.ss.hibernate.domain;


import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Suriyanc
 */
@Entity
@Table(name = "m_item_type")
public class MItemType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ITEM_TYPE_ID")
    private Integer itemTypeId;
    @Column(name = "ITEM_TYPE_CODE")
    private String itemTypeCode;
    @Column(name = "ITEM_TYPE_NAME_TH")
    private String itemTypeNameTh;
    
    public MItemType() {
    }

    public MItemType(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getItemTypeCode() {
        return itemTypeCode;
    }

    public void setItemTypeCode(String itemTypeCode) {
        this.itemTypeCode = itemTypeCode;
    }

    public String getItemTypeNameTh() {
        return itemTypeNameTh;
    }

    public void setItemTypeNameTh(String itemTypeNameTh) {
        this.itemTypeNameTh = itemTypeNameTh;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemTypeId != null ? itemTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MItemType)) {
            return false;
        }
        MItemType other = (MItemType) object;
        if ((this.itemTypeId == null && other.itemTypeId != null) || (this.itemTypeId != null && !this.itemTypeId.equals(other.itemTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cimb_entiry.MItemType[ itemTypeId=" + itemTypeId + " ]";
    }
    
}
