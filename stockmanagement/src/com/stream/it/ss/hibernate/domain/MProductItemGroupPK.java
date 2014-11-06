package com.stream.it.ss.hibernate.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;



@Embeddable
public class MProductItemGroupPK implements Serializable{
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "PRODUCT_ID")
    private int productId;
    @Basic(optional = false)
    @Column(name = "ITEM_ID")
    private int itemId;

    public MProductItemGroupPK() {
    }

    public MProductItemGroupPK(int productId, int itemId) {
        this.productId = productId;
        this.itemId = itemId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) productId;
        hash += (int) itemId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MProductItemGroupPK)) {
            return false;
        }
        MProductItemGroupPK other = (MProductItemGroupPK) object;
        if (this.productId != other.productId) {
            return false;
        }
        if (this.itemId != other.itemId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cimb_entiry.MProductItemGroupPK[ productId=" + productId + ", itemId=" + itemId + " ]";
    }
}
