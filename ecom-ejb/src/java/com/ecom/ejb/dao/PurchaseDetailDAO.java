/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.PurchaseDetail;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class PurchaseDetailDAO extends AbstractFacade<PurchaseDetail> implements PurchaseDetailDAOLocal {
    @PersistenceContext(unitName = "ecom-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseDetailDAO() {
        super(PurchaseDetail.class);
    }
    
}
