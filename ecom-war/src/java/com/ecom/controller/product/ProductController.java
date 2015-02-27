/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.controller.product;

import com.ecom.common.constants.Constants;
import com.ecom.common.util.ExceptionUtil;
import com.ecom.common.util.JsfUtil;
import com.ecom.common.util.MsgBundleLoader;
import com.ecom.common.util.NumberUtil;
import com.ecom.common.util.StringUtil;
import com.ecom.common.util.UploadUtil;
import com.ecom.controller.MethodController;
import com.ecom.controller.NavigationController;
import com.ecom.controller.UserInfoController;
import com.ecom.controller.bean.DataTableBean;
import com.ecom.ejb.common.util.DateTimeUtil;
import com.ecom.ejb.entity.ProductCategory;
import com.ecom.ejb.entity.ProductDetail;
import com.ecom.ejb.entity.ProductStatus;
import com.ecom.ejb.entity.ProductType;
import com.ecom.ejb.facade.ProductFacadeRemote;
import com.ecom.ejb.facade.SysGenCodeFacadeRemote;
import com.ecom.log.util.LogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = ProductController.BEAN_NAME)
@SessionScoped
public class ProductController extends MethodController implements Serializable {

    private static Logger logger = LogUtil.getLogger(ProductController.class);
    public static final String BEAN_NAME = "productController";
    @EJB
    private ProductFacadeRemote productFacade;
    @EJB
    private SysGenCodeFacadeRemote sysGenCodeFacade;
    private UserInfoController userInfo;
    private NavigationController navigationController;
    private String productCategoryS;
    private String productTypeS;
    private String productStatusS;
    private String productNameS;
    private String productDescS;
    //
    private String mode;
    private String productCode;
    private String productName;
    private String productDesc;
    private UploadedFile file;
    private String imgPath;
    private String productImage;
    private String productCategory;
    private List<SelectItem> productCategoryList;
    private String productType;
    private List<SelectItem> productTypeList;
    private String productStatus;
    private List<SelectItem> productStatusList;
    private Double productCost;
    private Integer productDiscount;
    private Double productPrice;
    private List<DataTableBean> dataList;
    private boolean check;

    @PostConstruct
    @Override
    public void init() throws Exception {
        String METHOD_NAME = "init";
        try {
            userInfo = (UserInfoController) JsfUtil.getManagedBean(UserInfoController.CONTROLLER_NAME);
            navigationController = (NavigationController) JsfUtil.getManagedBean(NavigationController.CONTROLLER_NAME);
            productCategoryList = new ArrayList<SelectItem>();
            productCategoryList.add(new SelectItem("", ""));
            List<ProductCategory> proCategoryList = productFacade.findAllProductCategory();
            for (int i = 0; i < proCategoryList.size(); i++) {
                ProductCategory item = (ProductCategory) proCategoryList.get(i);
                productCategoryList.add(new SelectItem(item.getCategoryCode(), item.getCategoryDescEn()));
            }
            productTypeList = new ArrayList<SelectItem>();
            productTypeList.add(new SelectItem("", ""));
            List<ProductType> proTypeList = productFacade.findAllProductType();
            for (int i = 0; i < proTypeList.size(); i++) {
                ProductType item = (ProductType) proTypeList.get(i);
                productTypeList.add(new SelectItem(item.getTypeCode(), item.getTypeDescEn()));
            }
            productStatusList = new ArrayList<SelectItem>();
            productStatusList.add(new SelectItem("", ""));
            List<ProductStatus> proStatusList = productFacade.findAllProductStatus();
            for (int i = 0; i < proStatusList.size(); i++) {
                ProductStatus item = (ProductStatus) proStatusList.get(i);
                productStatusList.add(new SelectItem(item.getStatusCode(), item.getStatusDescEn()));
            }
            productCategoryS = "C001";
            findAll();
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, userInfo.getUsername());
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    @Override
    public void insert() throws Exception {
        String METHOD_NAME = "insert";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, userInfo.getUsername());
            mode = "A";
            productCode = sysGenCodeFacade.genCodeByCodeType(Constants.GEN_PRODUCT_CODE);
            productName = "";
            productDesc = "";
            productImage = "";
            imgPath = UploadUtil.getFilePath("");
            productCost = 0.0;
            productDiscount = 0;
            productPrice = 0.0;
            productCategory = "";
            productType = "";
            productStatus = "Y";
            navigationController.nextPage("pro/product_detail", "");
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, userInfo.getUsername());
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    @Override
    public void find() throws Exception {
        String METHOD_NAME = "find";
        try {
            System.out.println("find()");
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, userInfo.getUsername());
            mode = "U";
            ProductDetail pro = productFacade.findProductDetail(productCode);
            if (StringUtil.isNotNullOrNotEmpty(pro)) {
                productName = pro.getProductName();
                productDesc = pro.getProductDesc();
                productImage = pro.getProductImage();
                imgPath = UploadUtil.getFilePath(pro.getProductImage());
                productCost = NumberUtil.getDouble(pro.getProductCost());
                productDiscount = NumberUtil.getInteger(pro.getProductDiscount());
                productPrice = NumberUtil.getDouble(pro.getProductPrice());
                if (StringUtil.isNotNullOrNotEmpty(pro.getProductCategory())) {
                    productCategory = pro.getProductCategory().getCategoryCode();
                }
                if (StringUtil.isNotNullOrNotEmpty(pro.getProductType())) {
                    productType = pro.getProductType().getTypeCode();
                }
                if (StringUtil.isNotNullOrNotEmpty(pro.getProductStatus())) {
                    productStatus = pro.getProductStatus().getStatusCode();
                }
            } else {
                List<String> error = new ArrayList<String>();
                error = ExceptionUtil.addLineException(error, "errors.1009", new String[]{productCode});
                JsfUtil.addErrorMessages(error);
            }
            navigationController.nextPage("pro/product_detail", "");
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, userInfo.getUsername());
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    @Override
    public void findAll() throws Exception {
        String METHOD_NAME = "findAll";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, userInfo.getUsername());
            dataList = new ArrayList<DataTableBean>();
            List<ProductDetail> proList = productFacade.findListProductDetailByCri(productCategoryS, productTypeS, productStatusS, productNameS, productDescS);
            for (int i = 0; i < proList.size(); i++) {
                ProductDetail pro = (ProductDetail) proList.get(i);
                DataTableBean bean = new DataTableBean();
                bean.setSeq(i + 1);
                bean.setImgPath(UploadUtil.getFilePath(pro.getProductImage()));
                bean.setProductDetail(pro);
                dataList.add(bean);
            }
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, userInfo.getUsername());
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    public void upload() {
        String METHOD_NAME = "upload";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, userInfo.getUsername());
            imgPath = "";
            productImage = "";
            if (StringUtil.isNotNullOrNotEmpty(file)) {
                List<String> error = new ArrayList<String>();
                if (StringUtil.isNullOrEmpty(productCode)) {
                    error = ExceptionUtil.addLineException(error, "errors.1001", new String[]{MsgBundleLoader.getMessage("productCode", MsgBundleLoader.LABLE_PATH)});
                }
                if (error.size() > 0) {
                    JsfUtil.addErrorMessages(error);
                    return;
                }
                productImage = UploadUtil.uploadFile(file, productCode);
                imgPath = UploadUtil.getFilePath(productImage);
                productFacade.editImagePathProductDetail(productCode, productImage);
            }else {
                productFacade.editImagePathProductDetail(productCode, "");
            }
            find();
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, userInfo.getUsername());
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    @Override
    public void delete() throws Exception {
        String METHOD_NAME = "delete";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, userInfo.getUsername());
            for (int i = 0; i < dataList.size(); i++) {
                DataTableBean data = (DataTableBean) dataList.get(i);
                if (data.isCheck()) {
                }
            }
            findAll();
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, userInfo.getUsername());
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    @Override
    public void update() throws Exception {
        String METHOD_NAME = "update";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, userInfo.getUsername());
            List<String> error = new ArrayList<String>();
            if (StringUtil.isNullOrEmpty(productCode)) {
                error = ExceptionUtil.addLineException(error, "errors.1001", new String[]{MsgBundleLoader.getMessage("productCode", MsgBundleLoader.LABLE_PATH)});
            }
            if (StringUtil.isNullOrEmpty(productName)) {
                error = ExceptionUtil.addLineException(error, "errors.1001", new String[]{MsgBundleLoader.getMessage("productName", MsgBundleLoader.LABLE_PATH)});
            }
            if (StringUtil.isNullOrEmpty(productCategory)) {
                error = ExceptionUtil.addLineException(error, "errors.1001", new String[]{MsgBundleLoader.getMessage("productCategory", MsgBundleLoader.LABLE_PATH)});
            }
            if (StringUtil.isNullOrEmpty(productCost)) {
                error = ExceptionUtil.addLineException(error, "errors.1001", new String[]{MsgBundleLoader.getMessage("productCost", MsgBundleLoader.LABLE_PATH)});
            }
            if (error.size() > 0) {
                JsfUtil.addErrorMessages(error);
                return;
            }
            if (mode.equals("A")) {
                ProductDetail proDetail = new ProductDetail();
                proDetail.setProductCode(productCode);
                proDetail.setProductName(productName);
                proDetail.setProductDesc(productDesc);
                if (StringUtil.isNotNullOrNotEmpty(productCategory)) {
                    proDetail.setProductCategory(new ProductCategory(productCategory));
                }else {
                    proDetail.setProductCategory(new ProductCategory());
                }
                if (StringUtil.isNotNullOrNotEmpty(productType)) {
                    proDetail.setProductType(new ProductType(productType));
                }else {
                    proDetail.setProductType(new ProductType());
                }
                if (StringUtil.isNotNullOrNotEmpty(productStatus)) {
                    proDetail.setProductStatus(new ProductStatus(productStatus));
                }else {
                    proDetail.setProductStatus(new ProductStatus());
                }
                proDetail.setProductCost(productCost);
                proDetail.setProductDiscount(productDiscount);
                if (productDiscount > 0) {
                    productPrice = productCost - ((productCost * productDiscount) / 100);
                } else {
                    productPrice = productCost;
                }
                proDetail.setProductPrice(productPrice);
                proDetail.setCreateDt(DateTimeUtil.currentDate());
                proDetail.setCreateBy(userInfo.getAuthUser().getUserId());
                proDetail.setUpdateDt(DateTimeUtil.currentDate());
                proDetail.setUpdateBy(userInfo.getAuthUser().getUserId());
                productFacade.createProductDetail(proDetail);
                JsfUtil.addSuccessMessage("");
            } else {
                ProductDetail proDetail = productFacade.findProductDetail(productCode);
                proDetail.setProductName(productName);
                proDetail.setProductDesc(productDesc);
                if (StringUtil.isNotNullOrNotEmpty(productCategory)) {
                    proDetail.setProductCategory(new ProductCategory(productCategory));
                }else {
                    proDetail.setProductCategory(new ProductCategory());
                }
                if (StringUtil.isNotNullOrNotEmpty(productType)) {
                    proDetail.setProductType(new ProductType(productType));
                }else {
                    proDetail.setProductType(new ProductType());
                }
                if (StringUtil.isNotNullOrNotEmpty(productStatus)) {
                    proDetail.setProductStatus(new ProductStatus(productStatus));
                }else {
                    proDetail.setProductStatus(new ProductStatus());
                }
                proDetail.setProductCost(productCost);
                proDetail.setProductDiscount(productDiscount);
                if (productDiscount > 0) {
                    productPrice = productCost - ((productCost * productDiscount) / 100);
                } else {
                    productPrice = productCost;
                }
                proDetail.setProductPrice(productPrice);
                proDetail.setUpdateDt(DateTimeUtil.currentDate());
                proDetail.setUpdateBy(userInfo.getAuthUser().getUserId());
                productFacade.editProductDetail(proDetail);
                JsfUtil.addSuccessMessage("");
            }
            findAll();
            navigationController.nextPage("pro/product", "");
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, userInfo.getUsername());
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }
    
    public void updateTemp() throws Exception {
        String METHOD_NAME = "updateTemp";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, userInfo.getUsername());
            List<String> error = new ArrayList<String>();
            if (StringUtil.isNullOrEmpty(productCode)) {
                error = ExceptionUtil.addLineException(error, "errors.1001", new String[]{MsgBundleLoader.getMessage("productCode", MsgBundleLoader.LABLE_PATH)});
            }
            if (StringUtil.isNullOrEmpty(productName)) {
                error = ExceptionUtil.addLineException(error, "errors.1001", new String[]{MsgBundleLoader.getMessage("productName", MsgBundleLoader.LABLE_PATH)});
            }
            if (StringUtil.isNullOrEmpty(productCategory)) {
                error = ExceptionUtil.addLineException(error, "errors.1001", new String[]{MsgBundleLoader.getMessage("productCategory", MsgBundleLoader.LABLE_PATH)});
            }
            if (StringUtil.isNullOrEmpty(productCost)) {
                error = ExceptionUtil.addLineException(error, "errors.1001", new String[]{MsgBundleLoader.getMessage("productCost", MsgBundleLoader.LABLE_PATH)});
            }
            if (error.size() > 0) {
                JsfUtil.addErrorMessages(error);
                return;
            }
            if (mode.equals("A")) {
                ProductDetail proDetail = new ProductDetail();
                proDetail.setProductCode(productCode);
                proDetail.setProductName(productName);
                proDetail.setProductDesc(productDesc);
                if (StringUtil.isNotNullOrNotEmpty(productCategory)) {
                    proDetail.setProductCategory(new ProductCategory(productCategory));
                }
                if (StringUtil.isNotNullOrNotEmpty(productType)) {
                    proDetail.setProductType(new ProductType(productType));
                }
                if (StringUtil.isNotNullOrNotEmpty(productStatus)) {
                    proDetail.setProductStatus(new ProductStatus(productStatus));
                }
                proDetail.setProductCost(productCost);
                proDetail.setProductDiscount(productDiscount);
                if (productDiscount > 0) {
                    productPrice = productCost - ((productCost * productDiscount) / 100);
                } else {
                    productPrice = productCost;
                }
                proDetail.setProductPrice(productPrice);
                proDetail.setCreateDt(DateTimeUtil.currentDate());
                proDetail.setCreateBy(userInfo.getAuthUser().getUserId());
                proDetail.setUpdateDt(DateTimeUtil.currentDate());
                proDetail.setUpdateBy(userInfo.getAuthUser().getUserId());
                productFacade.createProductDetail(proDetail);
            } else {
                ProductDetail proDetail = productFacade.findProductDetail(productCode);
                proDetail.setProductName(productName);
                proDetail.setProductDesc(productDesc);
                if (StringUtil.isNotNullOrNotEmpty(productCategory)) {
                    proDetail.setProductCategory(new ProductCategory(productCategory));
                }
                if (StringUtil.isNotNullOrNotEmpty(productType)) {
                    proDetail.setProductType(new ProductType(productType));
                }
                if (StringUtil.isNotNullOrNotEmpty(productStatus)) {
                    proDetail.setProductStatus(new ProductStatus(productStatus));
                }
                proDetail.setProductCost(productCost);
                proDetail.setProductDiscount(productDiscount);
                if (productDiscount > 0) {
                    productPrice = productCost - ((productCost * productDiscount) / 100);
                } else {
                    productPrice = productCost;
                }
                proDetail.setProductPrice(productPrice);
                proDetail.setUpdateDt(DateTimeUtil.currentDate());
                proDetail.setUpdateBy(userInfo.getAuthUser().getUserId());
                productFacade.editProductDetail(proDetail);
            }
            navigationController.nextPage("pro/product_image", "");
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, userInfo.getUsername());
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    @Override
    public void cancel() throws Exception {
    }

    @Override
    public void reportPDF() throws Exception {
    }

    @Override
    public void reportExcel() throws Exception {
    }

    public void selectAll() {
        for (int i = 0; i < dataList.size(); i++) {
            DataTableBean data = (DataTableBean) dataList.get(i);
            data.setCheck(check);
        }
    }

    public UserInfoController getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoController userInfo) {
        this.userInfo = userInfo;
    }

    public NavigationController getNavigationController() {
        return navigationController;
    }

    public void setNavigationController(NavigationController navigationController) {
        this.navigationController = navigationController;
    }

    public String getProductCategoryS() {
        return productCategoryS;
    }

    public void setProductCategoryS(String productCategoryS) {
        this.productCategoryS = productCategoryS;
    }

    public String getProductTypeS() {
        return productTypeS;
    }

    public void setProductTypeS(String productTypeS) {
        this.productTypeS = productTypeS;
    }

    public String getProductStatusS() {
        return productStatusS;
    }

    public void setProductStatusS(String productStatusS) {
        this.productStatusS = productStatusS;
    }

    public String getProductNameS() {
        return productNameS;
    }

    public void setProductNameS(String productNameS) {
        this.productNameS = productNameS;
    }

    public String getProductDescS() {
        return productDescS;
    }

    public void setProductDescS(String productDescS) {
        this.productDescS = productDescS;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public List<SelectItem> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<SelectItem> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<SelectItem> getProductTypeList() {
        return productTypeList;
    }

    public void setProductTypeList(List<SelectItem> productTypeList) {
        this.productTypeList = productTypeList;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public List<SelectItem> getProductStatusList() {
        return productStatusList;
    }

    public void setProductStatusList(List<SelectItem> productStatusList) {
        this.productStatusList = productStatusList;
    }

    public Double getProductCost() {
        return productCost;
    }

    public void setProductCost(Double productCost) {
        this.productCost = productCost;
    }

    public Integer getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(Integer productDiscount) {
        this.productDiscount = productDiscount;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public List<DataTableBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataTableBean> dataList) {
        this.dataList = dataList;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
