/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.OrderList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface OrderListDAOLocal {

    void create(OrderList orderList) throws Exception;

    void edit(OrderList orderList) throws Exception;

    void remove(OrderList orderList) throws Exception;

    OrderList find(Object id) throws Exception;

    List<OrderList> findAll() throws Exception;

    int count() throws Exception;
    
    void delete(OrderList orderList) throws Exception;
}
