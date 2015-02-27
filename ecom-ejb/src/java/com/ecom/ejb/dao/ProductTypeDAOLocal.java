/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.ProductType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface ProductTypeDAOLocal {

    void create(ProductType productType) throws Exception;

    void edit(ProductType productType) throws Exception;

    void remove(ProductType productType) throws Exception;

    ProductType find(Object id) throws Exception;

    List<ProductType> findAll() throws Exception;

    int count() throws Exception;
}
