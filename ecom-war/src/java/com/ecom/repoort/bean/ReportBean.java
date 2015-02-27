/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.repoort.bean;

/**
 *
 * @author ACOP_LAR
 */
public class ReportBean {

    private Integer item;
    private String productName;
    private Double price;
    private Integer qty;
    private Double totalPrice;

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
