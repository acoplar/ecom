/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.bo.purchase;

import com.ecom.ejb.entity.PayBank;
import com.ecom.ejb.entity.PayChannel;
import com.ecom.ejb.entity.Purchase;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface PurchaseBOLocal {

    void createPurchase(Purchase purchase) throws Exception;

    void editPurchase(Purchase purchase) throws Exception;

    void deletePurchase(Purchase purchase) throws Exception;

    Purchase findPurchase(Object id) throws Exception;

    List<Purchase> findAllPurchase() throws Exception;

    List<Purchase> findListPurchaseByCri(String purchaseCode, String customerName, String mobile, String purchaseStatus, Date dateFrom, Date dateTo) throws Exception;

    List<PayChannel> findAllPayChannel() throws Exception;

    List<PayBank> findAllPayBank() throws Exception;
}
