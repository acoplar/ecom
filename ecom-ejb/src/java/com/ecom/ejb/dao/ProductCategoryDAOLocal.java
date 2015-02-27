/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.ProductCategory;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface ProductCategoryDAOLocal {

    void create(ProductCategory productCategory) throws Exception;

    void edit(ProductCategory productCategory) throws Exception;

    void remove(ProductCategory productCategory) throws Exception;

    ProductCategory find(Object id) throws Exception;

    List<ProductCategory> findAll() throws Exception;

    int count() throws Exception;
}
