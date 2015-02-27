/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.InvoiceDetail;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class InvoiceDetailDAO extends AbstractFacade<InvoiceDetail> implements InvoiceDetailDAOLocal {
    @PersistenceContext(unitName = "ecom-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InvoiceDetailDAO() {
        super(InvoiceDetail.class);
    }
    
}
