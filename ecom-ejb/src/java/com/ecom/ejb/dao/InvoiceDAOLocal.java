/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.Invoice;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface InvoiceDAOLocal {

    void create(Invoice invoice) throws Exception;

    void edit(Invoice invoice) throws Exception;

    void remove(Invoice invoice) throws Exception;

    Invoice find(Object id) throws Exception;

    List<Invoice> findAll() throws Exception;

    int count() throws Exception;

    List<Invoice> findListByCri(String invoiceCode, String purchaseCode, String customerName, String customerMobile, Date dateFrom, Date dateTo) throws Exception;
}
