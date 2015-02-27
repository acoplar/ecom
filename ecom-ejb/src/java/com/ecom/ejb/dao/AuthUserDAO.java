/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.AuthUser;
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
public class AuthUserDAO extends AbstractFacade<AuthUser> implements AuthUserDAOLocal {

    @PersistenceContext(unitName = "ecom-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthUserDAO() {
        super(AuthUser.class);
    }

    @Override
    public AuthUser findByUsername(String username) throws Exception {
        Query q = em.createQuery("select o from AuthUser o where o.username = ?1");
        q.setParameter(1, username);
        try {
            return (AuthUser) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
