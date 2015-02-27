/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ACOP_LAR
 */
@Entity
@Table(name = "pay_bank")
@NamedQueries({
    @NamedQuery(name = "PayBank.findAll", query = "SELECT p FROM PayBank p")})
public class PayBank implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "bank_code")
    private String bankCode;
    @Size(max = 100)
    @Column(name = "bank_name_en")
    private String bankNameEn;
    @Size(max = 100)
    @Column(name = "bank_name_th")
    private String bankNameTh;

    public PayBank() {
    }

    public PayBank(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankNameEn() {
        return bankNameEn;
    }

    public void setBankNameEn(String bankNameEn) {
        this.bankNameEn = bankNameEn;
    }

    public String getBankNameTh() {
        return bankNameTh;
    }

    public void setBankNameTh(String bankNameTh) {
        this.bankNameTh = bankNameTh;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bankCode != null ? bankCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PayBank)) {
            return false;
        }
        PayBank other = (PayBank) object;
        if ((this.bankCode == null && other.bankCode != null) || (this.bankCode != null && !this.bankCode.equals(other.bankCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecom.ejb.entity.PayBank[ bankCode=" + bankCode + " ]";
    }
    
}
