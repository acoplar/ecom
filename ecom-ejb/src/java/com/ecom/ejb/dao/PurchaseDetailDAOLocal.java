/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.PurchaseDetail;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface PurchaseDetailDAOLocal {

    void create(PurchaseDetail purchaseDetail) throws Exception;

    void edit(PurchaseDetail purchaseDetail) throws Exception;

    void remove(PurchaseDetail purchaseDetail) throws Exception;

    PurchaseDetail find(Object id) throws Exception;

    List<PurchaseDetail> findAll() throws Exception;

    int count() throws Exception;
}
