package com.stream.it.ss.hibernate.domain;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name = "m_product_item_group")
public class MProductItemGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MProductItemGroupPK mProductItemGroupPK;

    public MProductItemGroup() {
    }

    public MProductItemGroup(MProductItemGroupPK mProductItemGroupPK) {
        this.mProductItemGroupPK = mProductItemGroupPK;
    }

    public MProductItemGroup(int productId, int itemId) {
        this.mProductItemGroupPK = new MProductItemGroupPK(productId, itemId);
    }

    public MProductItemGroupPK getMProductItemGroupPK() {
        return mProductItemGroupPK;
    }

    public void setMProductItemGroupPK(MProductItemGroupPK mProductItemGroupPK) {
        this.mProductItemGroupPK = mProductItemGroupPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mProductItemGroupPK != null ? mProductItemGroupPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MProductItemGroup)) {
            return false;
        }
        MProductItemGroup other = (MProductItemGroup) object;
        if ((this.mProductItemGroupPK == null && other.mProductItemGroupPK != null) || (this.mProductItemGroupPK != null && !this.mProductItemGroupPK.equals(other.mProductItemGroupPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cimb_entiry.MProductItemGroup[ mProductItemGroupPK=" + mProductItemGroupPK + " ]";
    }

}
