/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.AuthUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface AuthUserDAOLocal {

    void create(AuthUser authUser) throws Exception;

    void edit(AuthUser authUser) throws Exception;

    void remove(AuthUser authUser) throws Exception;

    AuthUser find(Object id) throws Exception;

    List<AuthUser> findAll() throws Exception;

    int count() throws Exception;

    AuthUser findByUsername(String username) throws Exception;
}
