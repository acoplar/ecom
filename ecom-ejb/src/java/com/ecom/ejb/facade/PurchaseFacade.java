/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.facade;

import com.ecom.ejb.bo.purchase.PurchaseBOLocal;
import com.ecom.ejb.entity.PayBank;
import com.ecom.ejb.entity.PayChannel;
import com.ecom.ejb.entity.Purchase;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class PurchaseFacade implements PurchaseFacadeRemote {

    @EJB
    private PurchaseBOLocal purchaseBO;

    @Override
    public void createPurchase(Purchase purchase) throws Exception {
        purchaseBO.createPurchase(purchase);
    }

    @Override
    public void editPurchase(Purchase purchase) throws Exception {
        purchaseBO.editPurchase(purchase);
    }

    @Override
    public void deletePurchase(Purchase purchase) throws Exception {
        purchaseBO.deletePurchase(purchase);
    }

    @Override
    public Purchase findPurchase(Object id) throws Exception {
        return purchaseBO.findPurchase(id);
    }

    @Override
    public List<Purchase> findAllPurchase() throws Exception {
        return purchaseBO.findAllPurchase();
    }

    @Override
    public List<Purchase> findListPurchaseByCri(String purchaseCode, String customerName, String mobile, String purchaseStatus, Date dateFrom, Date dateTo) throws Exception {
        return purchaseBO.findListPurchaseByCri(purchaseCode, customerName, mobile, purchaseStatus, dateFrom, dateTo);
    }

    @Override
    public List<PayChannel> findAllPayChannel() throws Exception {
        return purchaseBO.findAllPayChannel();
    }

    @Override
    public List<PayBank> findAllPayBank() throws Exception {
        return purchaseBO.findAllPayBank();
    }
}
