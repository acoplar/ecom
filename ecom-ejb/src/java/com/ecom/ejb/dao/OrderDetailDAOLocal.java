/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.OrderDetail;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface OrderDetailDAOLocal {

    void create(OrderDetail orderDetail) throws Exception;

    void edit(OrderDetail orderDetail) throws Exception;

    void remove(OrderDetail orderDetail) throws Exception;

    OrderDetail find(Object id) throws Exception;

    List<OrderDetail> findAll() throws Exception;

    int count() throws Exception;

    void delete(OrderDetail orderDetail) throws Exception;

    Integer sumItems(String orderCode) throws Exception;
    
    void deleteOrderCode(String orderCode) throws Exception;
}
