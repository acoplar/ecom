/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.bo.order;

import com.ecom.ejb.entity.OrderDetail;
import com.ecom.ejb.entity.OrderList;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface OrderBOLocal {

    void createOrderList(OrderList orderList) throws Exception;
    
    void createOrderDetail(OrderDetail orderDetail) throws Exception;
    
    void editOrderDetail(OrderDetail orderDetail) throws Exception;

    OrderList findOrderList(Object id) throws Exception;
    
    void deleteOrderDetail(OrderDetail orderDetail) throws Exception;
    
    void deleteOrderList(OrderList orderList) throws Exception;
    
    Integer sumItemsOrderDetail(String orderCode) throws Exception;
}
