/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.bo.product;

import com.ecom.ejb.dao.ProductCategoryDAOLocal;
import com.ecom.ejb.dao.ProductDetailDAOLocal;
import com.ecom.ejb.dao.ProductStatusDAOLocal;
import com.ecom.ejb.dao.ProductTypeDAOLocal;
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
public class ProductBO implements ProductBOLocal {

    @EJB
    private ProductTypeDAOLocal productTypeDAO;
    @EJB
    private ProductCategoryDAOLocal productCategoryDAO;
    @EJB
    private ProductStatusDAOLocal productStatusDAO;
    @EJB
    private ProductDetailDAOLocal productDetailDAO;

    @Override
    public List<ProductType> findAllProductType() throws Exception {
        return productTypeDAO.findAll();
    }

    @Override
    public List<ProductCategory> findAllProductCategory() throws Exception {
        return productCategoryDAO.findAll();
    }

    @Override
    public List<ProductStatus> findAllProductStatus() throws Exception {
        return productStatusDAO.findAll();
    }

    @Override
    public void createProductDetail(ProductDetail productDetail) throws Exception {
        productDetailDAO.create(productDetail);
    }

    @Override
    public void editProductDetail(ProductDetail productDetail) throws Exception {
        productDetailDAO.edit(productDetail);
    }

    @Override
    public void deleteProductDetail(ProductDetail productDetail) throws Exception {
        productDetailDAO.remove(productDetail);
    }

    @Override
    public ProductDetail findProductDetail(Object id) throws Exception {
        return productDetailDAO.find(id);
    }

    @Override
    public List<ProductDetail> findAllProductDetail() throws Exception {
        return productDetailDAO.findAll();
    }

    @Override
    public List<ProductDetail> findListProductDetailByCri(String category, String type, String status, String productName, String productDesc) throws Exception {
        return productDetailDAO.findListByCri(category, type, status, productName, productDesc);
    }

    @Override
    public List<ProductDetail> findListByCriSearch(String productName) throws Exception {
        return productDetailDAO.findListByCriSearch(productName);
    }

    @Override
    public void editImagePathProductDetail(String productCode, String productImage) throws Exception {
        productDetailDAO.editImagePath(productCode, productImage);
    }
}
