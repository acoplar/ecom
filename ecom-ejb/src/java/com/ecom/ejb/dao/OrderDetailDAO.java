/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.common.util.StringUtil;
import com.ecom.ejb.entity.OrderDetail;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class OrderDetailDAO extends AbstractFacade<OrderDetail> implements OrderDetailDAOLocal {
    @PersistenceContext(unitName = "ecom-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderDetailDAO() {
        super(OrderDetail.class);
    }

    @Override
    public void delete(OrderDetail orderDetail) throws Exception {
        Query q = em.createQuery("delete from OrderDetail o where o.orderDetailPK = ?1 ");
        q.setParameter(1, orderDetail.getOrderDetailPK());
        q.executeUpdate();
    }

    @Override
    public Integer sumItems(String orderCode) throws Exception {
        try {
            Query q = em.createNativeQuery("select count(product_code) from order_detail where order_code = ?1 ");
            q.setParameter(1, orderCode);
            Number quantity = (Number) q.getSingleResult();
            if (StringUtil.isNotNullOrNotEmpty(quantity)) {
                return quantity.intValue();
            } else {
                return 0;
            }
        } catch (NoResultException e) {
            return 0;
        }
    }

    @Override
    public void deleteOrderCode(String orderCode) throws Exception {
        Query q = em.createQuery("delete from OrderDetail o where o.orderDetailPK.orderCode = ?1 ");
        q.setParameter(1, orderCode);
        q.executeUpdate();
    }
    
}
