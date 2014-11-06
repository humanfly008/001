package com.stream.it.ss.hibernate.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Suriyanc
 */
@Entity
@Table(name = "m_supplier")
public class MSupplier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SUPPLIER_ID")
    private Integer supplierId;
    @Column(name = "SUPPLIER_CODE")
    private String supplierCode;
    @Column(name = "SUPPLIER_NAME_TH")
    private String supplierNameTh;
    @Column(name = "SUPPLIER_TYPE")
    private String supplierType;    
    @Column(name = "SUPPLIER_CONTRACT")
    private String supplierContract;  
    @Column(name = "SUPPLIER_PHONE")
    private String supplierPhone;
    @Column(name = "SUPPLIER_FAX")
    private String supplierFax;   
    @Column(name = "SUPPLIER_EMAIL")
    private String supplierEmail;   
    @Column(name = "SUPPLIER_DETAILS")
    private String supplierDetails;
    @Lob
    @Column(name = "SUPPLIER_ADDRESS")
    private String supplierAddress;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "UPPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uppdateDate;
    @Column(name = "UPDATE_BY")
    private String updateBy;
    @OneToMany(mappedBy = "supplierId")
    private Collection<MItem> mItemCollection;
    

    public MSupplier() {
    }

    public MSupplier(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public MSupplier(Integer supplierId, String supplierCode, String supplierNameTh) {
        this.supplierId = supplierId;
        this.supplierCode = supplierCode;
        this.supplierNameTh = supplierNameTh;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierNameTh() {
        return supplierNameTh;
    }

    public void setSupplierNameTh(String supplierNameTh) {
        this.supplierNameTh = supplierNameTh;
    }
    
    public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUppdateDate() {
        return uppdateDate;
    }

    public void setUppdateDate(Date uppdateDate) {
        this.uppdateDate = uppdateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @XmlTransient
    public Collection<MItem> getMItemCollection() {
        return mItemCollection;
    }

    public void setMItemCollection(Collection<MItem> mItemCollection) {
        this.mItemCollection = mItemCollection;
    }

    public Collection<MItem> getmItemCollection() {
		return mItemCollection;
	}

	public void setmItemCollection(Collection<MItem> mItemCollection) {
		this.mItemCollection = mItemCollection;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public String getSupplierContract() {
		return supplierContract;
	}

	public void setSupplierContract(String supplierContract) {
		this.supplierContract = supplierContract;
	}

	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	public String getSupplierFax() {
		return supplierFax;
	}

	public void setSupplierFax(String supplierFax) {
		this.supplierFax = supplierFax;
	}

	public String getSupplierDetails() {
		return supplierDetails;
	}

	public void setSupplierDetails(String supplierDetails) {
		this.supplierDetails = supplierDetails;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierId != null ? supplierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSupplier)) {
            return false;
        }
        MSupplier other = (MSupplier) object;
        if ((this.supplierId == null && other.supplierId != null) || (this.supplierId != null && !this.supplierId.equals(other.supplierId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cimb_entiry.MSupplier[ supplierId=" + supplierId + " ]";
    }
    
}
