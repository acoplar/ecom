/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.OrderList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class OrderListDAO extends AbstractFacade<OrderList> implements OrderListDAOLocal {

    @PersistenceContext(unitName = "ecom-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderListDAO() {
        super(OrderList.class);
    }

    @Override
    public void delete(OrderList orderList) throws Exception {
        Query q = em.createQuery("delete from OrderList o where o.orderCode = ?1 ");
        q.setParameter(1, orderList.getOrderCode());
        q.executeUpdate();
    }
}
