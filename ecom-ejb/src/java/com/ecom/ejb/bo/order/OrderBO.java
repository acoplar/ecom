/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.bo.order;

import com.ecom.ejb.common.util.StringUtil;
import com.ecom.ejb.dao.OrderDetailDAOLocal;
import com.ecom.ejb.dao.OrderListDAOLocal;
import com.ecom.ejb.entity.OrderDetail;
import com.ecom.ejb.entity.OrderList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class OrderBO implements OrderBOLocal {

    @EJB
    private OrderListDAOLocal orderListDAO;
    @EJB
    private OrderDetailDAOLocal orderDetailDAO;

    @Override
    public void createOrderList(OrderList orderList) throws Exception {
        orderListDAO.create(orderList);
    }

    @Override
    public OrderList findOrderList(Object id) throws Exception {
        OrderList oList = orderListDAO.find(id);
        if (StringUtil.isNotNullOrNotEmpty(oList)) {
            oList.getOrderDetailList().toString();
        }
        return oList;
    }

    @Override
    public void createOrderDetail(OrderDetail orderDetail) throws Exception {
        orderDetailDAO.create(orderDetail);
    }

    @Override
    public void editOrderDetail(OrderDetail orderDetail) throws Exception {
        orderDetailDAO.edit(orderDetail);
    }

    @Override
    public void deleteOrderDetail(OrderDetail orderDetail) throws Exception {
        orderDetailDAO.delete(orderDetail);
    }

    @Override
    public void deleteOrderList(OrderList orderList) throws Exception {
        orderDetailDAO.deleteOrderCode(orderList.getOrderCode());
        orderListDAO.delete(orderList);
    }

    @Override
    public Integer sumItemsOrderDetail(String orderCode) throws Exception {
        return orderDetailDAO.sumItems(orderCode);
    }
}
