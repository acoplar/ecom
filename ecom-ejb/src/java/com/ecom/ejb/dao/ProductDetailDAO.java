/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.common.util.StringUtil;
import com.ecom.ejb.entity.ProductDetail;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class ProductDetailDAO extends AbstractFacade<ProductDetail> implements ProductDetailDAOLocal {

    @PersistenceContext(unitName = "ecom-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductDetailDAO() {
        super(ProductDetail.class);
    }

    @Override
    public List<ProductDetail> findListByCri(String category, String type, String status, String productName, String productDesc) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("select o from ProductDetail o where 1=1 ");
        if (StringUtil.isNotNullOrNotEmpty(category)) {
            sb.append("and o.productCategory.categoryCode = '").append(category).append("' ");
        }
        if (StringUtil.isNotNullOrNotEmpty(type)) {
            sb.append("and o.productType.typeCode = '").append(type).append("' ");
        }
        if (StringUtil.isNotNullOrNotEmpty(status)) {
            sb.append("and o.productStatus.statusCode = '").append(status).append("' ");
        }
        if (StringUtil.isNotNullOrNotEmpty(productName)) {
            sb.append("and o.productName like '%").append(productName).append("%' ");
        }
        if (StringUtil.isNotNullOrNotEmpty(productDesc)) {
            sb.append("and o.productDesc like '%").append(productDesc).append("%' ");
        }
        Query q = em.createQuery(sb.toString());
        return q.getResultList();
    }

    @Override
    public List<ProductDetail> findListByCriSearch(String productName) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("select o from ProductDetail o where 1=1 ");
        if (StringUtil.isNotNullOrNotEmpty(productName)) {
            sb.append("and (o.productName like '%").append(productName).append("%' or o.productDesc like '%").append(productName).append("%') ");
        }
        System.out.println(sb.toString());
        Query q = em.createQuery(sb.toString());
        return q.getResultList();
    }

    @Override
    public void editImagePath(String productCode, String productImage) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("update ProductDetail o set o.productImage = '");
        sb.append(productImage).append("' where o.productCode = '");
        sb.append(productCode).append("' ");
        System.out.println(sb.toString());
        Query q = em.createQuery(sb.toString());
        q.executeUpdate();
    }
}
