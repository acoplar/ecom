/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.common.util.DateTimeUtil;
import com.ecom.ejb.common.util.StringUtil;
import com.ecom.ejb.entity.Purchase;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class PurchaseDAO extends AbstractFacade<Purchase> implements PurchaseDAOLocal {

    @PersistenceContext(unitName = "ecom-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseDAO() {
        super(Purchase.class);
    }

    @Override
    public List<Purchase> findListByCri(String purchaseCode, String customerName, String mobile, String purchaseStatus, Date dateFrom, Date dateTo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("select o from Purchase o where 1=1 ");
        if (StringUtil.isNotNullOrNotEmpty(purchaseCode)) {
            sb.append("and o.purchaseCode like '%").append(purchaseCode).append("%' ");
        }
        if (StringUtil.isNotNullOrNotEmpty(customerName)) {
            sb.append("and o.customerName like '%").append(customerName).append("%' ");
        }
        if (StringUtil.isNotNullOrNotEmpty(mobile)) {
            sb.append("and o.customerMobile = '").append(mobile).append("' ");
        }
        if (StringUtil.isNotNullOrNotEmpty(purchaseStatus)) {
            sb.append("and o.purchaseStatus = '").append(purchaseStatus).append("' ");
        }
        if (dateFrom != null) {
            sb.append(" and o.createDt >= '").append(DateTimeUtil.dateToString(dateFrom, DateTimeUtil.PATTERN_DB)).append(DateTimeUtil.SELECT_FROM_TIME).append("'");
        }
        if (dateTo != null) {
            sb.append(" and o.createDt <= '").append(DateTimeUtil.dateToString(dateTo, DateTimeUtil.PATTERN_DB)).append(DateTimeUtil.SELECT_TO_TIME).append("'");
        }
        System.out.println(sb.toString());
        Query q = em.createQuery(sb.toString());
        return q.getResultList();
    }
}
