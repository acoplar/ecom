/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.facade;

import com.ecom.ejb.bo.user.UserBOLocal;
import com.ecom.ejb.entity.AuthUser;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class UserFacade implements UserFacadeRemote {

    @EJB
    private UserBOLocal userBOLocal;

    @Override
    public AuthUser findAuthUserByUsername(String username) throws Exception {
        return userBOLocal.findAuthUserByUsername(username);
    }

    @Override
    public AuthUser findAuthUserById(String userId) throws Exception {
        return userBOLocal.findAuthUserById(userId);
    }
}
