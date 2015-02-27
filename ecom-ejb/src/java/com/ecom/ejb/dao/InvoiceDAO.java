/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.common.util.DateTimeUtil;
import com.ecom.ejb.common.util.StringUtil;
import com.ecom.ejb.entity.Invoice;
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
public class InvoiceDAO extends AbstractFacade<Invoice> implements InvoiceDAOLocal {
    @PersistenceContext(unitName = "ecom-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InvoiceDAO() {
        super(Invoice.class);
    }

    @Override
    public List<Invoice> findListByCri(String invoiceCode, String purchaseCode, String customerName, String customerMobile, Date dateFrom, Date dateTo) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("select o from Invoice o where 1=1 ");
        if (StringUtil.isNotNullOrNotEmpty(purchaseCode)) {
            sb.append("and o.invoiceNo like '%").append(invoiceCode).append("%' ");
        }
        if (StringUtil.isNotNullOrNotEmpty(customerName)) {
            sb.append("and o.purchaseCode like '%").append(purchaseCode).append("%' ");
        }
        if (StringUtil.isNotNullOrNotEmpty(customerName)) {
            sb.append("and o.customerName like '%").append(customerName).append("%' ");
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
