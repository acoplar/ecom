/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.InvoiceDetail;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface InvoiceDetailDAOLocal {

    void create(InvoiceDetail invoiceDetail) throws Exception;

    void edit(InvoiceDetail invoiceDetail) throws Exception;

    void remove(InvoiceDetail invoiceDetail) throws Exception;

    InvoiceDetail find(Object id) throws Exception;

    List<InvoiceDetail> findAll() throws Exception;

    int count() throws Exception;
}
