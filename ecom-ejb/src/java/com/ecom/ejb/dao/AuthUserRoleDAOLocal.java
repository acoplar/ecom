/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.AuthUserRole;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface AuthUserRoleDAOLocal {

    void create(AuthUserRole authUserRole) throws Exception;

    void edit(AuthUserRole authUserRole) throws Exception;

    void remove(AuthUserRole authUserRole) throws Exception;

    AuthUserRole find(Object id) throws Exception;

    List<AuthUserRole> findAll() throws Exception;

    int count() throws Exception;
}
