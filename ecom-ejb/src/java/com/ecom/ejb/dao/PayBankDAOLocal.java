/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.PayBank;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface PayBankDAOLocal {

    void create(PayBank payBank) throws Exception;

    void edit(PayBank payBank) throws Exception;

    void remove(PayBank payBank) throws Exception;

    PayBank find(Object id) throws Exception;

    List<PayBank> findAll() throws Exception;

    int count() throws Exception;
}
