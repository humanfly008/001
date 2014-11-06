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
@Table(name = "m_supplier_type")
public class MSupplierType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SUPPLIER_TYPE_ID")
    private Integer supplierTypeId;
    @Column(name = "SUPPLIER_TYPE_CODE")
    private String supplierTypeCode;
    @Column(name = "SUPPLIER_TYPE_NAME_TH")
    private String supplierTypeNameTh;
//    @OneToMany(mappedBy = "supplirtTypeId")
//    private Collection<MSupplier> mSupplierCollection;

    public MSupplierType() {
    }

    public MSupplierType(Integer supplierTypeId) {
        this.supplierTypeId = supplierTypeId;
    }

    public Integer getSupplierTypeId() {
        return supplierTypeId;
    }

    public void setSupplierTypeId(Integer supplierTypeId) {
        this.supplierTypeId = supplierTypeId;
    }

    public String getSupplierTypeCode() {
        return supplierTypeCode;
    }

    public void setSupplierTypeCode(String supplierTypeCode) {
        this.supplierTypeCode = supplierTypeCode;
    }

    public String getSupplierTypeNameTh() {
        return supplierTypeNameTh;
    }

    public void setSupplierTypeNameTh(String supplierTypeNameTh) {
        this.supplierTypeNameTh = supplierTypeNameTh;
    }

//    @XmlTransient
//    public Collection<MSupplier> getMSupplierCollection() {
//        return mSupplierCollection;
//    }
//
//    public void setMSupplierCollection(Collection<MSupplier> mSupplierCollection) {
//        this.mSupplierCollection = mSupplierCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierTypeId != null ? supplierTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSupplierType)) {
            return false;
        }
        MSupplierType other = (MSupplierType) object;
        if ((this.supplierTypeId == null && other.supplierTypeId != null) || (this.supplierTypeId != null && !this.supplierTypeId.equals(other.supplierTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cimb_entiry.MSupplierType[ supplierTypeId=" + supplierTypeId + " ]";
    }
    
}

