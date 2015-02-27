/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.facade;

import com.ecom.ejb.bo.product.ProductBOLocal;
import com.ecom.ejb.entity.ProductCategory;
import com.ecom.ejb.entity.ProductDetail;
import com.ecom.ejb.entity.ProductStatus;
import com.ecom.ejb.entity.ProductType;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class ProductFacade implements ProductFacadeRemote {

    @EJB
    private ProductBOLocal productBO;

    @Override
    public List<ProductType> findAllProductType() throws Exception {
        return productBO.findAllProductType();
    }

    @Override
    public List<ProductCategory> findAllProductCategory() throws Exception {
        return productBO.findAllProductCategory();
    }

    @Override
    public List<ProductStatus> findAllProductStatus() throws Exception {
        return productBO.findAllProductStatus();
    }

    @Override
    public void createProductDetail(ProductDetail productDetail) throws Exception {
        productBO.createProductDetail(productDetail);
    }

    @Override
    public void editProductDetail(ProductDetail productDetail) throws Exception {
        productBO.editProductDetail(productDetail);
    }

    @Override
    public void deleteProductDetail(ProductDetail productDetail) throws Exception {
        productBO.deleteProductDetail(productDetail);
    }

    @Override
    public ProductDetail findProductDetail(Object id) throws Exception {
        return productBO.findProductDetail(id);
    }

    @Override
    public List<ProductDetail> findAllProductDetail() throws Exception {
        return productBO.findAllProductDetail();
    }

    @Override
    public List<ProductDetail> findListProductDetailByCri(String category, String type, String status, String productName, String productDesc) throws Exception {
        return productBO.findListProductDetailByCri(category, type, status, productName, productDesc);
    }

    @Override
    public List<ProductDetail> findListByCriSearch(String productName) throws Exception {
        return productBO.findListByCriSearch(productName);
    }

    @Override
    public void editImagePathProductDetail(String productCode, String productImage) throws Exception {
        productBO.editImagePathProductDetail(productCode, productImage);
    }
}
