/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.ProductStatus;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface ProductStatusDAOLocal {

    void create(ProductStatus productStatus) throws Exception;

    void edit(ProductStatus productStatus) throws Exception;

    void remove(ProductStatus productStatus) throws Exception;

    ProductStatus find(Object id) throws Exception;

    List<ProductStatus> findAll() throws Exception;

    int count() throws Exception;
}
