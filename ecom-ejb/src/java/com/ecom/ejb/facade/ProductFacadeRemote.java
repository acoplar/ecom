/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.facade;

import com.ecom.ejb.entity.ProductCategory;
import com.ecom.ejb.entity.ProductDetail;
import com.ecom.ejb.entity.ProductStatus;
import com.ecom.ejb.entity.ProductType;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ACOP_LAR
 */
@Remote
public interface ProductFacadeRemote {

    List<ProductType> findAllProductType() throws Exception;

    List<ProductCategory> findAllProductCategory() throws Exception;

    List<ProductStatus> findAllProductStatus() throws Exception;

    void createProductDetail(ProductDetail productDetail) throws Exception;

    void editProductDetail(ProductDetail productDetail) throws Exception;

    void deleteProductDetail(ProductDetail productDetail) throws Exception;

    ProductDetail findProductDetail(Object id) throws Exception;

    List<ProductDetail> findAllProductDetail() throws Exception;

    List<ProductDetail> findListProductDetailByCri(String category, String type, String status, String productName, String productDesc) throws Exception;
    
    List<ProductDetail> findListByCriSearch(String productName) throws Exception;
    
    void editImagePathProductDetail(String productCode, String productImage) throws Exception;
}
