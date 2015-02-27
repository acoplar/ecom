package com.ecom.controller.bean;

import com.ecom.ejb.entity.OrderDetail;
import com.ecom.ejb.entity.ProductDetail;

public class DataTableDetailBean {

    private boolean check;
    private Integer seq;
    private String value;
    private String lableEn;
    private String lableth;
    private String imgPath;
    private OrderDetail orderDetail;

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

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
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
}
