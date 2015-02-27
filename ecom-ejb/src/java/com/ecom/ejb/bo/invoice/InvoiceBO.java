/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.bo.invoice;

import com.ecom.ejb.common.util.StringUtil;
import com.ecom.ejb.dao.InvoiceDAOLocal;
import com.ecom.ejb.entity.Invoice;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class InvoiceBO implements InvoiceBOLocal {

    @EJB
    private InvoiceDAOLocal invoiceDAO;

    @Override
    public void createInvoice(Invoice invoice) throws Exception {
        invoiceDAO.create(invoice);
    }

    @Override
    public Invoice findInvoice(Object id) throws Exception {
        Invoice invoice = invoiceDAO.find(id);
        if (StringUtil.isNotNullOrNotEmpty(invoice)) {
            invoice.getInvoiceDetailList().toString();
        }
        return invoice;
    }

    @Override
    public List<Invoice> findListInvoiceByCri(String invoiceCode, String purchaseCode, String customerName, String customerMobile, Date dateFrom, Date dateTo) throws Exception {
        return invoiceDAO.findListByCri(invoiceCode, purchaseCode, customerName, customerMobile, dateFrom, dateTo);
    }
}
