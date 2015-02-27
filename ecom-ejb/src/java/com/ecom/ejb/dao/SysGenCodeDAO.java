/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.SysGenCode;
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
public class SysGenCodeDAO extends AbstractFacade<SysGenCode> implements SysGenCodeDAOLocal {

    @PersistenceContext(unitName = "ecom-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SysGenCodeDAO() {
        super(SysGenCode.class);
    }

    @Override
    public SysGenCode findByType(String codeType) throws Exception {
        Query q = em.createQuery("select o from SysGenCode o where o.codeType = ?1");
        q.setParameter(1, codeType);
        try {
            return (SysGenCode) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
