/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.bo.invoice;

import com.ecom.ejb.entity.Invoice;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface InvoiceBOLocal {

    void createInvoice(Invoice invoice) throws Exception;
    
    Invoice findInvoice(Object id) throws Exception;

    List<Invoice> findListInvoiceByCri(String invoiceCode, String purchaseCode, String customerName, String customerMobile, Date dateFrom, Date dateTo) throws Exception;
}
