/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.Purchase;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface PurchaseDAOLocal {

    void create(Purchase purchase) throws Exception;

    void edit(Purchase purchase) throws Exception;

    void remove(Purchase purchase) throws Exception;

    Purchase find(Object id) throws Exception;

    List<Purchase> findAll() throws Exception;

    int count() throws Exception;
    
    List<Purchase> findListByCri(String purchaseCode, String customerName, String mobile, String purchaseStatus, Date dateFrom, Date dateTo) throws Exception;
}
