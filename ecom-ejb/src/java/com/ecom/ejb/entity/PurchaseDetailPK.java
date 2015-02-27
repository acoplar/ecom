/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ACOP_LAR
 */
@Embeddable
public class PurchaseDetailPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "purchase_code")
    private String purchaseCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "product_code")
    private String productCode;

    public PurchaseDetailPK() {
    }

    public PurchaseDetailPK(String purchaseCode, String productCode) {
        this.purchaseCode = purchaseCode;
        this.productCode = productCode;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseCode != null ? purchaseCode.hashCode() : 0);
        hash += (productCode != null ? productCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseDetailPK)) {
            return false;
        }
        PurchaseDetailPK other = (PurchaseDetailPK) object;
        if ((this.purchaseCode == null && other.purchaseCode != null) || (this.purchaseCode != null && !this.purchaseCode.equals(other.purchaseCode))) {
            return false;
        }
        if ((this.productCode == null && other.productCode != null) || (this.productCode != null && !this.productCode.equals(other.productCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecom.ejb.entity.PurchaseDetailPK[ purchaseCode=" + purchaseCode + ", productCode=" + productCode + " ]";
    }
    
}
