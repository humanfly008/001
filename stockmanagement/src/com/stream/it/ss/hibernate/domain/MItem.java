package com.stream.it.ss.hibernate.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Suriyanc
 */
@Entity
@Table(name = "m_item")
public class MItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ITEM_ID")
    private Integer itemId;
    @Column(name = "ITEM_CODE")
    private String itemCode;
    @Column(name = "ITEM_NAME_TH")
    private String itemNameTh;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ITEM_UNIT_PRICE")
    private Double itemUnitPrice;
    @Column(name = "ITEM_QTY")
    private Integer itemQty;
    @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "SUPPLIER_ID")
    @ManyToOne
    private MSupplier supplierId;
    @Column(name = "ITEM_TYPE")
    private String itemType;
    @Column(name = "UNIT")
    private String unit;
    @Column(name = "ITEM_SUPPLIER_CODE")
    private String itemSupplierCode;
    @Column(name = "SET_PER_USE")
    private Integer setPerUse;
    @Column(name = "NOTIFICATE_LEVEL1_UNIT")
    private Integer norificateLevel1Unit;
    @Column(name = "NOTIFICATE_LEVEL2_UNIT")
    private Integer norificateLevel2Unit;
    @Column(name = "NOTIFICATE_LEVEL3_UNIT")
    private Integer norificateLevel3Unit;
    @Column(name = "PIC_NAME")
    private String picName;    
    
    public MItem() {
    }

    public MItem(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemNameTh() {
        return itemNameTh;
    }

    public void setItemNameTh(String itemNameTh) {
        this.itemNameTh = itemNameTh;
    }

    public Double getItemUnitPrice() {
        return itemUnitPrice;
    }

    public void setItemUnitPrice(Double itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

    public MSupplier getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(MSupplier supplierId) {
        this.supplierId = supplierId;
    }
    
    public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getItemSupplierCode() {
		return itemSupplierCode;
	}

	public void setItemSupplierCode(String itemSupplierCode) {
		this.itemSupplierCode = itemSupplierCode;
	}

	public Integer getSetPerUse() {
		return setPerUse;
	}

	public void setSetPerUse(Integer setPerUse) {
		this.setPerUse = setPerUse;
	}

	public Integer getNorificateLevel1Unit() {
		return norificateLevel1Unit;
	}

	public void setNorificateLevel1Unit(Integer norificateLevel1Unit) {
		this.norificateLevel1Unit = norificateLevel1Unit;
	}

	public Integer getNorificateLevel2Unit() {
		return norificateLevel2Unit;
	}

	public void setNorificateLevel2Unit(Integer norificateLevel2Unit) {
		this.norificateLevel2Unit = norificateLevel2Unit;
	}

	public Integer getNorificateLevel3Unit() {
		return norificateLevel3Unit;
	}

	public void setNorificateLevel3Unit(Integer norificateLevel3Unit) {
		this.norificateLevel3Unit = norificateLevel3Unit;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MItem)) {
            return false;
        }
        MItem other = (MItem) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cimb_entiry.MItem[ itemId=" + itemId + " ]";
    }
}