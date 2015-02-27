/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.facade;

import com.ecom.ejb.entity.Invoice;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ACOP_LAR
 */
@Remote
public interface InvoiceFacadeRemote {

    void createInvoice(Invoice invoice) throws Exception;

    Invoice findInvoice(Object id) throws Exception;

    List<Invoice> findListInvoiceByCri(String invoiceCode, String purchaseCode, String customerName, String customerMobile, Date dateFrom, Date dateTo) throws Exception;
}
