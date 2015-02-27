/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.facade;

import com.ecom.ejb.entity.AuthUser;
import javax.ejb.Remote;

/**
 *
 * @author ACOP_LAR
 */
@Remote
public interface UserFacadeRemote {

    AuthUser findAuthUserByUsername(String username) throws Exception;

    AuthUser findAuthUserById(String userId) throws Exception;
}
