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
@Table(name = "invoice_detail")
@NamedQueries({
    @NamedQuery(name = "InvoiceDetail.findAll", query = "SELECT i FROM InvoiceDetail i")})
public class InvoiceDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InvoiceDetailPK invoiceDetailPK;
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
    @JoinColumn(name = "invoice_no", referencedColumnName = "invoice_no", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Invoice invoice;

    public InvoiceDetail() {
    }

    public InvoiceDetail(InvoiceDetailPK invoiceDetailPK) {
        this.invoiceDetailPK = invoiceDetailPK;
    }

    public InvoiceDetail(String invoiceNo, String productCode) {
        this.invoiceDetailPK = new InvoiceDetailPK(invoiceNo, productCode);
    }

    public InvoiceDetailPK getInvoiceDetailPK() {
        return invoiceDetailPK;
    }

    public void setInvoiceDetailPK(InvoiceDetailPK invoiceDetailPK) {
        this.invoiceDetailPK = invoiceDetailPK;
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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceDetailPK != null ? invoiceDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceDetail)) {
            return false;
        }
        InvoiceDetail other = (InvoiceDetail) object;
        if ((this.invoiceDetailPK == null && other.invoiceDetailPK != null) || (this.invoiceDetailPK != null && !this.invoiceDetailPK.equals(other.invoiceDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecom.ejb.entity.InvoiceDetail[ invoiceDetailPK=" + invoiceDetailPK + " ]";
    }
}
