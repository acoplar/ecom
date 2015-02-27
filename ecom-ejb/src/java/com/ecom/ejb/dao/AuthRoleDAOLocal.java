/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.AuthRole;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface AuthRoleDAOLocal {

    void create(AuthRole authRole) throws Exception;

    void edit(AuthRole authRole) throws Exception;

    void remove(AuthRole authRole) throws Exception;

    AuthRole find(Object id) throws Exception;

    List<AuthRole> findAll() throws Exception;

    int count() throws Exception;
}
