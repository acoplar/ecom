/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.facade;

import com.ecom.ejb.bo.invoice.InvoiceBOLocal;
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
public class InvoiceFacade implements InvoiceFacadeRemote {

    @EJB
    private InvoiceBOLocal invoiceBO;

    @Override
    public void createInvoice(Invoice invoice) throws Exception {
        invoiceBO.createInvoice(invoice);
    }

    @Override
    public Invoice findInvoice(Object id) throws Exception {
        return invoiceBO.findInvoice(id);
    }

    @Override
    public List<Invoice> findListInvoiceByCri(String invoiceCode, String purchaseCode, String customerName, String customerMobile, Date dateFrom, Date dateTo) throws Exception {
        return invoiceBO.findListInvoiceByCri(invoiceCode, purchaseCode, customerName, customerMobile, dateFrom, dateTo);
    }
}
