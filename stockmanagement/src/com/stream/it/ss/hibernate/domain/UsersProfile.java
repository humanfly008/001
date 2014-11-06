package com.stream.it.ss.hibernate.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users_profile")
public class UsersProfile implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "ID_CARD")
    private Integer idCard;
    @Column(name = "DEPARTMENT")
    private String department;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "FUNCTIONS_USE")
    private String functionsUse;
    @Column(name = "PAY_TYPE")
    private String payType;
    @Column(name = "SALARY")
    private Double salary;
    @Column(name = "LAST_LOGIN")
    private Date lastLogin;
    @Column(name = "SOCIAL_INSURANCE")
    private Integer socialInsurance;
    @Column(name = "TAX")
    private Integer tax;

    public UsersProfile() {
    }

    public UsersProfile(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getIdCard() {
        return idCard;
    }

    public void setIdCard(Integer idCard) {
        this.idCard = idCard;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFunctionsUse() {
        return functionsUse;
    }

    public void setFunctionsUse(String functionsUse) {
        this.functionsUse = functionsUse;
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getSocialInsurance() {
        return socialInsurance;
    }

    public void setSocialInsurance(Integer socialInsurance) {
        this.socialInsurance = socialInsurance;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersProfile)) {
            return false;
        }
        UsersProfile other = (UsersProfile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cimb_entiry.UsersProfile[ id=" + id + " ]";
    }
}
