/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.facade;

import com.ecom.ejb.bo.order.OrderBOLocal;
import com.ecom.ejb.entity.OrderDetail;
import com.ecom.ejb.entity.OrderList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class OrderFacade implements OrderFacadeRemote {

    @EJB
    private OrderBOLocal orderBO;

    @Override
    public void createOrderList(OrderList orderList) throws Exception {
        orderBO.createOrderList(orderList);
    }

    @Override
    public OrderList findOrderList(Object id) throws Exception {
        return orderBO.findOrderList(id);
    }

    @Override
    public void createOrderDetail(OrderDetail orderDetail) throws Exception {
        orderBO.createOrderDetail(orderDetail);
    }

    @Override
    public void editOrderDetail(OrderDetail orderDetail) throws Exception {
        orderBO.editOrderDetail(orderDetail);
    }

    @Override
    public void deleteOrderDetail(OrderDetail orderDetail) throws Exception {
        orderBO.deleteOrderDetail(orderDetail);
    }

    @Override
    public void deleteOrderList(OrderList orderList) throws Exception {
        orderBO.deleteOrderList(orderList);
    }

    @Override
    public Integer sumItemsOrderDetail(String orderCode) throws Exception {
        return orderBO.sumItemsOrderDetail(orderCode);
    }
}
