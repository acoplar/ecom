/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.bo.user;

import com.ecom.ejb.entity.AuthUser;
import com.ecom.ejb.dao.AuthUserDAOLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class UserBO implements UserBOLocal {

    @EJB
    private AuthUserDAOLocal authUserDAO;

    @Override
    public AuthUser findAuthUserByUsername(String username) throws Exception {
        return authUserDAO.findByUsername(username);
    }

    @Override
    public AuthUser findAuthUserById(String userId) throws Exception {
        return authUserDAO.find(userId);
    }
}
