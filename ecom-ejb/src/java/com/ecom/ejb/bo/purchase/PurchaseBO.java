/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.bo.purchase;

import com.ecom.ejb.common.util.StringUtil;
import com.ecom.ejb.dao.PayBankDAOLocal;
import com.ecom.ejb.dao.PayChannelDAOLocal;
import com.ecom.ejb.dao.PurchaseDAOLocal;
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
public class PurchaseBO implements PurchaseBOLocal {

    @EJB
    private PurchaseDAOLocal purchaseDAO;
    @EJB
    private PayBankDAOLocal payBankDAO;
    @EJB
    private PayChannelDAOLocal payChannelDAO;

    @Override
    public void createPurchase(Purchase purchase) throws Exception {
        purchaseDAO.create(purchase);
    }

    @Override
    public void editPurchase(Purchase purchase) throws Exception {
        purchaseDAO.edit(purchase);
    }

    @Override
    public void deletePurchase(Purchase purchase) throws Exception {
        purchaseDAO.remove(purchase);
    }

    @Override
    public Purchase findPurchase(Object id) throws Exception {
        Purchase p = purchaseDAO.find(id);
        if (StringUtil.isNotNullOrNotEmpty(p)) {
            p.getPurchaseDetailList().toString();
        }
        return p;
    }

    @Override
    public List<Purchase> findAllPurchase() throws Exception {
        return purchaseDAO.findAll();
    }

    @Override
    public List<Purchase> findListPurchaseByCri(String purchaseCode, String customerName, String mobile, String purchaseStatus, Date dateFrom, Date dateTo) throws Exception {
        return purchaseDAO.findListByCri(purchaseCode, customerName, mobile, purchaseStatus, dateFrom, dateTo);
    }

    @Override
    public List<PayChannel> findAllPayChannel() throws Exception {
        return payChannelDAO.findAll();
    }

    @Override
    public List<PayBank> findAllPayBank() throws Exception {
        return payBankDAO.findAll();
    }
}
