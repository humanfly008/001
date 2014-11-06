package com.stream.it.ss.hibernate.domain;


import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Suriyanc
 */
@Entity
@Table(name = "m_product")
public class MProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    @Column(name = "PRODUCT_CODE")
    private String productCode;
    @Column(name = "PRODUCT_NAME_TH")
    private String productNameTh;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRODUCT_UNIT_PRICE")
    private Double productUnitPrice;
    @Column(name = "PRODUCT_QTY")
    private Integer productQty;
    @Column(name = "PRODUCT_SUPPLIER_CODE")
    private String productSupplierCode;
    @Column(name = "PRODUCT_TYPE")
    private String productType;
    @Column(name = "UNIT")
    private String unit;
    @Column(name = "SET_PER_USE")
    private Integer setPerUse;
    @Column(name = "NOTIFTCATE_LEVEL1_UNIT")
    private Integer notiftcateLevel1Unit;
    @Column(name = "NOTIFTCATE_LEVEL2_UNIT")
    private Integer notiftcateLevel2Unit;
    @Column(name = "NOTIFTCATE_LEVEL3_UNIT")
    private Integer notiftcateLevel3Unit;
    @Column(name = "PIC_NAME")
    private String picName;
    @OneToMany(mappedBy = "productId")
    private Collection<TStockProduct> tStockProductCollection;
    @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "SUPPLIER_ID")
    @ManyToOne
    private MSupplier supplierId;

    public MProduct() {
    }

    public MProduct(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductNameTh() {
        return productNameTh;
    }

    public void setProductNameTh(String productNameTh) {
        this.productNameTh = productNameTh;
    }

    public Double getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(Double productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }

    public String getProductSupplierCode() {
        return productSupplierCode;
    }

    public void setProductSupplierCode(String productSupplierCode) {
        this.productSupplierCode = productSupplierCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getSetPerUse() {
        return setPerUse;
    }

    public void setSetPerUse(Integer setPerUse) {
        this.setPerUse = setPerUse;
    }

    public Integer getNotiftcateLevel1Unit() {
        return notiftcateLevel1Unit;
    }

    public void setNotiftcateLevel1Unit(Integer notiftcateLevel1Unit) {
        this.notiftcateLevel1Unit = notiftcateLevel1Unit;
    }

    public Integer getNotiftcateLevel2Unit() {
        return notiftcateLevel2Unit;
    }

    public void setNotiftcateLevel2Unit(Integer notiftcateLevel2Unit) {
        this.notiftcateLevel2Unit = notiftcateLevel2Unit;
    }

    public Integer getNotiftcateLevel3Unit() {
        return notiftcateLevel3Unit;
    }

    public void setNotiftcateLevel3Unit(Integer notiftcateLevel3Unit) {
        this.notiftcateLevel3Unit = notiftcateLevel3Unit;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    @XmlTransient
    public Collection<TStockProduct> getTStockProductCollection() {
        return tStockProductCollection;
    }

    public void setTStockProductCollection(Collection<TStockProduct> tStockProductCollection) {
        this.tStockProductCollection = tStockProductCollection;
    }

    public MSupplier getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(MSupplier supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MProduct)) {
            return false;
        }
        MProduct other = (MProduct) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cimb_entiry.MProduct[ productId=" + productId + " ]";
    }
}