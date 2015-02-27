/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.bo.user;

import com.ecom.ejb.entity.AuthUser;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface UserBOLocal {

    AuthUser findAuthUserByUsername(String username) throws Exception;

    AuthUser findAuthUserById(String userId) throws Exception;
}
