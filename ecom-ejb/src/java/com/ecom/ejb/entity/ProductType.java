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
@Table(name = "product_type")
@NamedQueries({
    @NamedQuery(name = "ProductType.findAll", query = "SELECT p FROM ProductType p")})
public class ProductType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "type_code")
    private String typeCode;
    @Size(max = 255)
    @Column(name = "type_desc_th")
    private String typeDescTh;
    @Size(max = 255)
    @Column(name = "type_desc_en")
    private String typeDescEn;
    @OneToMany(mappedBy = "productType")
    private List<ProductDetail> productDetailList;

    public ProductType() {
    }

    public ProductType(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeDescTh() {
        return typeDescTh;
    }

    public void setTypeDescTh(String typeDescTh) {
        this.typeDescTh = typeDescTh;
    }

    public String getTypeDescEn() {
        return typeDescEn;
    }

    public void setTypeDescEn(String typeDescEn) {
        this.typeDescEn = typeDescEn;
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
        hash += (typeCode != null ? typeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductType)) {
            return false;
        }
        ProductType other = (ProductType) object;
        if ((this.typeCode == null && other.typeCode != null) || (this.typeCode != null && !this.typeCode.equals(other.typeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecom.ejb.entity.ProductType[ typeCode=" + typeCode + " ]";
    }
    
}
