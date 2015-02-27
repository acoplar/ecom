/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.PayChannel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class PayChannelDAO extends AbstractFacade<PayChannel> implements PayChannelDAOLocal {
    @PersistenceContext(unitName = "ecom-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PayChannelDAO() {
        super(PayChannel.class);
    }
    
}
