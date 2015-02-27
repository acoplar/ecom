/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.ProductDetail;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface ProductDetailDAOLocal {

    void create(ProductDetail productDetail) throws Exception;

    void edit(ProductDetail productDetail) throws Exception;

    void remove(ProductDetail productDetail) throws Exception;

    ProductDetail find(Object id) throws Exception;

    List<ProductDetail> findAll() throws Exception;

    int count() throws Exception;

    List<ProductDetail> findListByCri(String category, String type, String status, String productName, String productDesc) throws Exception;
    
    List<ProductDetail> findListByCriSearch(String productName) throws Exception;
    
    void editImagePath(String productCode, String productImage) throws Exception;
}
