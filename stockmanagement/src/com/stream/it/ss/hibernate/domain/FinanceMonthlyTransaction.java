package com.stream.it.ss.hibernate.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Suriyanc
 */
@Entity
@Table(name = "finance_monthly_transaction")
public class FinanceMonthlyTransaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Integer transactionId;
    @Column(name = "YEAR")
    private Integer year;
    @Column(name = "MONTH")
    private Integer month;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "PAY_TYPE")
    private String payType;
    @Column(name = "SALARY")
    private Double salary;
    @Column(name = "DAILY")
    private Double daily;
    @Column(name = "FARE")
    private Double fare;
    @Column(name = "DILIGENCE")
    private Double diligence;
    @Column(name = "BONUS")
    private Double bonus;
    @Column(name = "OTHER_INCOME")
    private Double otherIncome;
    @Column(name = "OT_SUMMARY")
    private Double otSummary;
    @Column(name = "SUBTRACT_SOCIAL")
    private Double subtractSocial;
    @Column(name = "SUBTRACT_TAX")
    private Double subtractTax;
    @Column(name = "LEAVE_SUBTRACT")
    private Double leaveSubtract;
    @Column(name = "ACCUMULATE_SUBTRACT")
    private Double accumulateSubtract;
    @Column(name = "OTHER_SUBTRACT")
    private Double otherSubtract;
    @Column(name = "DETAILS")
    private String details;

    public FinanceMonthlyTransaction() {
    }

    public FinanceMonthlyTransaction(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getDaily() {
        return daily;
    }

    public void setDaily(Double daily) {
        this.daily = daily;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public Double getDiligence() {
        return diligence;
    }

    public void setDiligence(Double diligence) {
        this.diligence = diligence;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Double getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(Double otherIncome) {
        this.otherIncome = otherIncome;
    }

    public Double getOtSummary() {
        return otSummary;
    }

    public void setOtSummary(Double otSummary) {
        this.otSummary = otSummary;
    }

    public Double getSubtractSocial() {
        return subtractSocial;
    }

    public void setSubtractSocial(Double subtractSocial) {
        this.subtractSocial = subtractSocial;
    }

    public Double getSubtractTax() {
        return subtractTax;
    }

    public void setSubtractTax(Double subtractTax) {
        this.subtractTax = subtractTax;
    }

    public Double getLeaveSubtract() {
        return leaveSubtract;
    }

    public void setLeaveSubtract(Double leaveSubtract) {
        this.leaveSubtract = leaveSubtract;
    }

    public Double getAccumulateSubtract() {
        return accumulateSubtract;
    }

    public void setAccumulateSubtract(Double accumulateSubtract) {
        this.accumulateSubtract = accumulateSubtract;
    }

    public Double getOtherSubtract() {
        return otherSubtract;
    }

    public void setOtherSubtract(Double otherSubtract) {
        this.otherSubtract = otherSubtract;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinanceMonthlyTransaction)) {
            return false;
        }
        FinanceMonthlyTransaction other = (FinanceMonthlyTransaction) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cimb_entiry.FinanceMonthlyTransaction[ transactionId=" + transactionId + " ]";
    }    
}

