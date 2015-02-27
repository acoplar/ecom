/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ACOP_LAR
 */
@Entity
@Table(name = "product_status")
@NamedQueries({
    @NamedQuery(name = "ProductStatus.findAll", query = "SELECT p FROM ProductStatus p")})
public class ProductStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status_code")
    private String statusCode;
    @Size(max = 100)
    @Column(name = "status_desc_th")
    private String statusDescTh;
    @Size(max = 100)
    @Column(name = "status_desc_en")
    private String statusDescEn;
    @OneToMany(mappedBy = "productStatus")
    private List<ProductDetail> productDetailList;

    public ProductStatus() {
    }

    public ProductStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescTh() {
        return statusDescTh;
    }

    public void setStatusDescTh(String statusDescTh) {
        this.statusDescTh = statusDescTh;
    }

    public String getStatusDescEn() {
        return statusDescEn;
    }

    public void setStatusDescEn(String statusDescEn) {
        this.statusDescEn = statusDescEn;
    }

    public List<ProductDetail> getProductDetailList() {
        return productDetailList;
    }

    public void setProductDetailList(List<ProductDetail> productDetailList) {
        this.productDetailList = productDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusCode != null ? statusCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductStatus)) {
            return false;
        }
        ProductStatus other = (ProductStatus) object;
        if ((this.statusCode == null && other.statusCode != null) || (this.statusCode != null && !this.statusCode.equals(other.statusCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecom.ejb.entity.ProductStatus[ statusCode=" + statusCode + " ]";
    }
    
}
