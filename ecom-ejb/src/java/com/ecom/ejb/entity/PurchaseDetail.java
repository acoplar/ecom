/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ACOP_LAR
 */
@Entity
@Table(name = "purchase_detail")
@NamedQueries({
    @NamedQuery(name = "PurchaseDetail.findAll", query = "SELECT p FROM PurchaseDetail p")})
public class PurchaseDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PurchaseDetailPK purchaseDetailPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "product_qty")
    private Integer productQty;
    @Column(name = "product_price")
    private Double productPrice;
    @Column(name = "product_total")
    private Double productTotal;
    @Column(name = "create_dt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDt;
    @JoinColumn(name = "product_code", referencedColumnName = "product_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ProductDetail productDetail;
    @JoinColumn(name = "purchase_code", referencedColumnName = "purchase_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Purchase purchase;

    public PurchaseDetail() {
    }

    public PurchaseDetail(PurchaseDetailPK purchaseDetailPK) {
        this.purchaseDetailPK = purchaseDetailPK;
    }

    public PurchaseDetail(String purchaseCode, String productCode) {
        this.purchaseDetailPK = new PurchaseDetailPK(purchaseCode, productCode);
    }

    public PurchaseDetailPK getPurchaseDetailPK() {
        return purchaseDetailPK;
    }

    public void setPurchaseDetailPK(PurchaseDetailPK purchaseDetailPK) {
        this.purchaseDetailPK = purchaseDetailPK;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(Double productTotal) {
        this.productTotal = productTotal;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseDetailPK != null ? purchaseDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseDetail)) {
            return false;
        }
        PurchaseDetail other = (PurchaseDetail) object;
        if ((this.purchaseDetailPK == null && other.purchaseDetailPK != null) || (this.purchaseDetailPK != null && !this.purchaseDetailPK.equals(other.purchaseDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecom.ejb.entity.PurchaseDetail[ purchaseDetailPK=" + purchaseDetailPK + " ]";
    }
}
