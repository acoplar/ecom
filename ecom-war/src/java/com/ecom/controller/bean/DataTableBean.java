package com.ecom.controller.bean;

import com.ecom.ejb.entity.Invoice;
import com.ecom.ejb.entity.ProductDetail;
import com.ecom.ejb.entity.Purchase;
import java.util.List;

public class DataTableBean {

    private boolean check;
    private Integer seq;
    private String value;
    private String lableEn;
    private String lableth;
    private String imgPath;
    private ProductDetail productDetail;
    private Purchase purchase;
    private Invoice invoice;
    private List<DataTableBean> list;
    private List<DataTableDetailBean> detail;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getLableEn() {
        return lableEn;
    }

    public void setLableEn(String lableEn) {
        this.lableEn = lableEn;
    }

    public String getLableth() {
        return lableth;
    }

    public void setLableth(String lableth) {
        this.lableth = lableth;
    }

    public List<DataTableDetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DataTableDetailBean> detail) {
        this.detail = detail;
    }

    public List<DataTableBean> getList() {
        return list;
    }

    public void setList(List<DataTableBean> list) {
        this.list = list;
    }
}
