package com.stream.it.ss.hibernate.domain;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "T_STOCK_ITEM")
public class TStockItem {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "STOCK_ID")
    private Integer stockId;
    @Basic(optional = false)
    @Column(name = "STOCK_CODE")
    private String stockCode;
    @Basic(optional = false)
    @Column(name = "STOCK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stockDate;
    @Basic(optional = false)
    @Column(name = "STOCK_TYPE")
    private String stockType;
    @Basic(optional = false)
    @Column(name = "STOCK_QTY")
    private int stockQty;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "UPDATE_BY")
    private String updateBy;
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ITEM_ID")
    @ManyToOne
    private MItem itemId;

    public TStockItem() {
    }

    public TStockItem(Integer stockId) {
        this.stockId = stockId;
    }

    public TStockItem(Integer stockId, String stockCode, Date stockDate, String stockType, int stockQty) {
        this.stockId = stockId;
        this.stockCode = stockCode;
        this.stockDate = stockDate;
        this.stockType = stockType;
        this.stockQty = stockQty;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public MItem getItemId() {
        return itemId;
    }

    public void setItemId(MItem itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stockId != null ? stockId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TStockItem)) {
            return false;
        }
        TStockItem other = (TStockItem) object;
        if ((this.stockId == null && other.stockId != null) || (this.stockId != null && !this.stockId.equals(other.stockId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cimb_entiry.TStockItem[ stockId=" + stockId + " ]";
    }
}
