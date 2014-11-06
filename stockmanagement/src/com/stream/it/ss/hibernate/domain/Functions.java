package com.stream.it.ss.hibernate.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FUNCTIONS")
public class Functions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FUNC_ID",nullable=false,length=10)
    private String funcId;
    @Basic(optional = false)
    @Column(name = "FUNC_NAME",nullable=false,length=250)
    private String funcName;
    @Basic(optional = false)
    @Column(name = "FUNC_URL",nullable=false,length=300)
    private String funcUrl;

    public Functions() {
    }

    public Functions(String funcId) {
        this.funcId = funcId;
    }

    public Functions(String funcId, String funcName, String funcUrl) {
        this.funcId = funcId;
        this.funcName = funcName;
        this.funcUrl = funcUrl;
    }

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncUrl() {
        return funcUrl;
    }

    public void setFuncUrl(String funcUrl) {
        this.funcUrl = funcUrl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (funcId != null ? funcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Functions)) {
            return false;
        }
        Functions other = (Functions) object;
        if ((this.funcId == null && other.funcId != null) || (this.funcId != null && !this.funcId.equals(other.funcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.stream.it.ss.hibernate.domain.Functions[ funcId=" + funcId + " ]";
    }
    
}
