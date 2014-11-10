package com.stream.it.ss.hibernate.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "salary_transaction")
public class SalaryTransaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TRANSACTION_ID")
    private Integer transactionId;
    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;
    @Column(name = "DATE")
    private Integer date;
    @Column(name = "MONTH")
    private Integer month;
    @Column(name = "YEAR")
    private Integer year;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "ID")
    private String id;
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
    @Column(name = "OT_HOUR")
    private Double otHour;
    @Column(name = "OT_RATE")
    private Double otRate;
    @Column(name = "SOCIAL_INSURANCE")
    private Double socialInsurance;
    @Column(name = "TAX")
    private Double tax;
    @Column(name = "LEAVE_SUBTRACT")
    private Double leaveSubtract;
    @Column(name = "ACCUMULATE_SUBTRACT")
    private Double accumulateSubtract;
    @Column(name = "OTHER_SUBTRACT")
    private Double otherSubtract;
    @Column(name = "DETAILS")
    private String details;

    public SalaryTransaction() {
    }
    public SalaryTransaction(Integer transactionId) {
        this.transactionId = transactionId;
    }
    public Integer getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
    public Integer getDate() {
        return date;
    }
    public void setDate(Integer date) {
        this.date = date;
    }
    public Integer getMonth() {
        return month;
    }
    public void setMonth(Integer month) {
        this.month = month;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
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
    public Double getOtHour() {
        return otHour;
    }
    public void setOtHour(Double otHour) {
        this.otHour = otHour;
    }
    public Double getOtRate() {
        return otRate;
    }
    public void setOtRate(Double otRate) {
        this.otRate = otRate;
    }
    public Double getSocialInsurance() {
        return socialInsurance;
    }
    public void setSocialInsurance(Double socialInsurance) {
        this.socialInsurance = socialInsurance;
    }
    public Double getTax() {
        return tax;
    }
    public void setTax(Double tax) {
        this.tax = tax;
    }
    public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Double getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}
	public Double getOtherSubtract() {
		return otherSubtract;
	}
	public void setOtherSubtract(Double otherSubtract) {
		this.otherSubtract = otherSubtract;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
        if (!(object instanceof SalaryTransaction)) {
            return false;
        }
        SalaryTransaction other = (SalaryTransaction) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "cimb_entiry.SalaryTransaction[ transactionId=" + transactionId + " ]";
    }
}