/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.controller;

import com.ecom.common.constants.Constants;
import com.ecom.common.constants.ReportConstant;
import com.ecom.common.util.BahtTextUtil;
import com.ecom.common.util.ExceptionUtil;
import com.ecom.common.util.JsfUtil;
import com.ecom.common.util.MacAddressUtil;
import com.ecom.common.util.MsgBundleLoader;
import com.ecom.common.util.NumberUtil;
import com.ecom.common.util.ReportUtil;
import com.ecom.common.util.StringUtil;
import com.ecom.common.util.UploadUtil;
import com.ecom.controller.bean.DataTableBean;
import com.ecom.controller.bean.DataTableDetailBean;
import com.ecom.ejb.common.util.DateTimeUtil;
import com.ecom.ejb.entity.OrderDetail;
import com.ecom.ejb.entity.OrderDetailPK;
import com.ecom.ejb.entity.OrderList;
import com.ecom.ejb.entity.ProductCategory;
import com.ecom.ejb.entity.ProductDetail;
import com.ecom.ejb.entity.Purchase;
import com.ecom.ejb.entity.PurchaseDetail;
import com.ecom.ejb.entity.PurchaseDetailPK;
import com.ecom.ejb.facade.OrderFacadeRemote;
import com.ecom.ejb.facade.ProductFacadeRemote;
import com.ecom.ejb.facade.PurchaseFacadeRemote;
import com.ecom.ejb.facade.SysGenCodeFacadeRemote;
import com.ecom.log.util.LogUtil;
import com.ecom.repoort.bean.ReportBean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;

@ManagedBean(name = ShopController.BEAN_NAME)
@SessionScoped
public class ShopController extends MethodController implements Serializable {

    private static Logger logger = LogUtil.getLogger(ShopController.class);
    public static final String BEAN_NAME = "shopController";
    @EJB
    private ProductFacadeRemote productFacade;
    @EJB
    private OrderFacadeRemote orderFacade;
    @EJB
    private PurchaseFacadeRemote purchaseFacade;
    @EJB
    private SysGenCodeFacadeRemote sysGenCodeFacade;
    private LocaleController locale;
    private String productCategoryS;
    private String productNameS;
    private List<DataTableBean> productCategoryList;
    private List<DataTableBean> dataList;
    private ProductDetail product;
    private DataTableBean order;
    private String purchaseCode;
    private String macAddress;
    private String customerName;
    private String customerAddress;
    private String customerMobile;
    private String customerEmail;
    private String cardNumber;
    private String pin;
    private Integer totalItems;
    private Double totalPrice;
    private List<SelectItem> itemList;
    private String removeItem;
    private Integer sumItems;
    private boolean shopFlag;
    private boolean cusFlag;
    private boolean bankFlag;
    private boolean finishFlag;
    private boolean loginFlag;

    @PostConstruct
    @Override
    public void init() throws Exception {
        String METHOD_NAME = "init";
        try {
            locale = (LocaleController) JsfUtil.getManagedBean(LocaleController.CONTROLLER_NAME);

            macAddress = MacAddressUtil.getMacAddressClient();
            order = new DataTableBean();
            productCategoryS = "C001";
            productCategoryList = new ArrayList<DataTableBean>();
            List<ProductCategory> proCategoryList = productFacade.findAllProductCategory();
            for (int i = 0; i < proCategoryList.size(); i++) {
                DataTableBean bean = new DataTableBean();
                ProductCategory item = (ProductCategory) proCategoryList.get(i);
                bean.setValue(item.getCategoryCode());
                bean.setLableEn(item.getCategoryDescEn());
                bean.setLableth(item.getCategoryDescEn());
                productCategoryList.add(bean);
            }
            itemList = new ArrayList<SelectItem>();
            for (int i = 1; i <= 5; i++) {
                itemList.add(new SelectItem(i, String.valueOf(i)));
            }
            findAll();
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, Constants.USER_SYSTEM);
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    @Override
    public void insert() throws Exception {
        String METHOD_NAME = "insert";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, Constants.USER_SYSTEM);
            if (StringUtil.isNotNullOrNotEmpty(order)) {
                purchaseCode = sysGenCodeFacade.genCodeByCodeType(Constants.GEN_PURCHASE_CODE);
                Purchase p = new Purchase(purchaseCode);
                p.setCustomerType("C");
                p.setCustomerName(customerName);
                p.setCustomerAddress(customerAddress);
                p.setCustomerMobile(customerMobile);
                p.setCustomerEmail(customerEmail);
                p.setPayStatus("N");
                p.setPurchaseStatus("N");
                p.setTotalItem(NumberUtil.getInteger(totalItems));
                p.setTotalPrice(NumberUtil.getDouble(totalPrice));
                p.setCreateDt(DateTimeUtil.currentDate());
                List<PurchaseDetail> pList = new ArrayList<PurchaseDetail>();
                for (int i = 0; i < order.getDetail().size(); i++) {
                    DataTableDetailBean b = (DataTableDetailBean) order.getDetail().get(i);
                    PurchaseDetail pd = new PurchaseDetail(new PurchaseDetailPK(p.getPurchaseCode(), b.getOrderDetail().getProductDetail().getProductCode()));
                    pd.setProductDetail(b.getOrderDetail().getProductDetail());
                    pd.setProductPrice(b.getOrderDetail().getProductDetail().getProductPrice());
                    pd.setProductQty(b.getOrderDetail().getItems());
                    pd.setProductTotal(b.getOrderDetail().getPrices());
                    pd.setCreateDt(b.getOrderDetail().getCreateDt());
                    pList.add(pd);
                }
                p.setPurchaseDetailList(pList);
                purchaseFacade.createPurchase(p);
            }
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, Constants.USER_SYSTEM);
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    @Override
    public void find() throws Exception {
        String METHOD_NAME = "find";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, Constants.USER_SYSTEM);
            shopFlag = true;
            cusFlag = false;
            bankFlag = false;
            finishFlag = false;
            loginFlag = false;
            OrderList oList = orderFacade.findOrderList(macAddress);
            order.setValue(macAddress);
            List<DataTableDetailBean> list = new ArrayList<DataTableDetailBean>();
            totalItems = 0;
            totalPrice = 0.0;
            for (int i = 0; i < oList.getOrderDetailList().size(); i++) {
                DataTableDetailBean detail = new DataTableDetailBean();
                OrderDetail oDetail = (OrderDetail) oList.getOrderDetailList().get(i);
                detail.setSeq(i + 1);
                detail.setImgPath(UploadUtil.getFilePath(oDetail.getProductDetail().getProductImage()));
                detail.setOrderDetail(oDetail);
                totalItems = totalItems + oDetail.getItems();
                totalPrice = totalPrice + oDetail.getPrices();
                list.add(detail);
            }
            order.setDetail(list);
            sumItems();
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, Constants.USER_SYSTEM);
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    public void proceed() throws Exception {
        String METHOD_NAME = "proceed";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, Constants.USER_SYSTEM);
            shopFlag = false;
            cusFlag = true;
            bankFlag = false;
            finishFlag = false;
            loginFlag = false;
        } catch (Exception ex) {
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, Constants.USER_SYSTEM);
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    public void proceedCustomer() throws Exception {
        String METHOD_NAME = "proceedCustomer";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, Constants.USER_SYSTEM);
            List<String> error = new ArrayList<String>();
            if (StringUtil.isNullOrEmpty(customerName)) {
                error = ExceptionUtil.addLineException(error, "errors.1001", new String[]{MsgBundleLoader.getMessage("customerName", MsgBundleLoader.LABLE_PATH)});
            }
            if (StringUtil.isNullOrEmpty(customerAddress)) {
                error = ExceptionUtil.addLineException(error, "errors.1001", new String[]{MsgBundleLoader.getMessage("address", MsgBundleLoader.LABLE_PATH)});
            }
            if (StringUtil.isNullOrEmpty(customerEmail)) {
                error = ExceptionUtil.addLineException(error, "errors.1001", new String[]{MsgBundleLoader.getMessage("email", MsgBundleLoader.LABLE_PATH)});
            }
            if (StringUtil.isNullOrEmpty(customerMobile)) {
                error = ExceptionUtil.addLineException(error, "errors.1001", new String[]{MsgBundleLoader.getMessage("mobile", MsgBundleLoader.LABLE_PATH)});
            }
            if (error.size() > 0) {
                JsfUtil.addErrorMessages(error);
                return;
            }
            shopFlag = false;
            cusFlag = false;
            bankFlag = true;
            finishFlag = false;
            loginFlag = false;
        } catch (Exception ex) {
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, Constants.USER_SYSTEM);
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    public void proceedBank() throws Exception {
        String METHOD_NAME = "proceedBank";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, Constants.USER_SYSTEM);
            shopFlag = false;
            cusFlag = false;
            bankFlag = false;
            insert();
            orderFacade.deleteOrderList(new OrderList(macAddress));
            finishFlag = true;
        } catch (Exception ex) {
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, Constants.USER_SYSTEM);
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    public void sumItems() throws Exception {
        String METHOD_NAME = "sumItems";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, Constants.USER_SYSTEM);
            sumItems = orderFacade.sumItemsOrderDetail(macAddress);
        } catch (Exception ex) {
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, Constants.USER_SYSTEM);
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    public void editItems() {
        String METHOD_NAME = "editItems";
        try {
            totalItems = 0;
            totalPrice = 0.0;
            for (int i = 0; i < order.getDetail().size(); i++) {
                DataTableDetailBean bean = (DataTableDetailBean) order.getDetail().get(i);
                totalItems = totalItems + bean.getOrderDetail().getItems();
                bean.getOrderDetail().setPrices(bean.getOrderDetail().getProductDetail().getProductPrice() * bean.getOrderDetail().getItems());
                totalPrice = totalPrice + bean.getOrderDetail().getPrices();
                orderFacade.editOrderDetail(bean.getOrderDetail());
            }
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, Constants.USER_SYSTEM);
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    @Override
    public void findAll() throws Exception {
        String METHOD_NAME = "findAll";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, Constants.USER_SYSTEM);
//            dataList = new ArrayList<DataTableBean>();
//            List<DataTableBean> list = new ArrayList<DataTableBean>();
//            List<ProductDetail> proList = productFacade.findListByCriSearch(productNameS);
//            for (int i = 0; i < proList.size(); i++) {
//                ProductDetail pro = (ProductDetail) proList.get(i);
//                DataTableBean bean = new DataTableBean();
//                bean.setSeq(i + 1);
//                bean.setImgPath(UploadUtil.getFilePath(pro.getProductImage()));
//                bean.setProductDetail(pro);
//                if ((i % 4) == 0) {
//                    list = new ArrayList<DataTableBean>();
//                    list.add(bean);
//                } else {
//                    list.add(bean);
//                }
//                dataList.add(bean);
//            }
            ///////////////////////////
            dataList = new ArrayList<DataTableBean>();
            List<ProductDetail> proList = new ArrayList<ProductDetail>();
            if (StringUtil.isNotNullOrNotEmpty(productNameS)){
                proList = productFacade.findListProductDetailByCri(productCategoryS, null, null, null, null);
            }else {
                proList = productFacade.findListByCriSearch(productNameS);
            }
            List<DataTableBean> d = new ArrayList<DataTableBean>();
            DataTableBean hh;
            for (int i = 1; i <= proList.size(); i++) {
                ProductDetail pro = (ProductDetail) proList.get(i - 1);
                if ((i % 4) == 0) {
                    DataTableBean bean = new DataTableBean();
                    bean.setImgPath(UploadUtil.getFilePath(pro.getProductImage()));
                    bean.setProductDetail(pro);
                    d.add(bean);
                    //
                    hh = new DataTableBean();
                    hh.setList(d);
                    d = new ArrayList<DataTableBean>();
                    dataList.add(hh);
                } else {
                    DataTableBean bean = new DataTableBean();
                    bean.setImgPath(UploadUtil.getFilePath(pro.getProductImage()));
                    bean.setProductDetail(pro);
                    d.add(bean);
                }
            }
            hh = new DataTableBean();
            hh.setList(d);
            dataList.add(hh);
            sumItems();
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, Constants.USER_SYSTEM);
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    public void findAllByCategory() throws Exception {
        String METHOD_NAME = "findAllByCategory";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, Constants.USER_SYSTEM);
//            dataList = new ArrayList<DataTableBean>();
//            List<ProductDetail> proList = productFacade.findListProductDetailByCri(productCategoryS, null, null, productNameS, null);
//            for (int i = 0; i < proList.size(); i++) {
//                ProductDetail pro = (ProductDetail) proList.get(i);
//                DataTableBean bean = new DataTableBean();
//                bean.setSeq(i + 1);
//                bean.setImgPath(UploadUtil.getFilePath(pro.getProductImage()));
//                bean.setProductDetail(pro);
//                dataList.add(bean);
//            }
            dataList = new ArrayList<DataTableBean>();
            productNameS = "";
            List<ProductDetail> proList = productFacade.findListProductDetailByCri(productCategoryS, null, null, null, null);
            List<DataTableBean> d = new ArrayList<DataTableBean>();
            DataTableBean hh;
            for (int i = 1; i <= proList.size(); i++) {
                ProductDetail pro = (ProductDetail) proList.get(i - 1);
                if ((i % 4) == 0) {
                    DataTableBean bean = new DataTableBean();
                    bean.setImgPath(UploadUtil.getFilePath(pro.getProductImage()));
                    bean.setProductDetail(pro);
                    d.add(bean);
                    //
                    hh = new DataTableBean();
                    hh.setList(d);
                    d = new ArrayList<DataTableBean>();
                    dataList.add(hh);
                } else {
                    DataTableBean bean = new DataTableBean();
                    bean.setImgPath(UploadUtil.getFilePath(pro.getProductImage()));
                    bean.setProductDetail(pro);
                    d.add(bean);
                }
            }
            hh = new DataTableBean();
            hh.setList(d);
            dataList.add(hh);
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, Constants.USER_SYSTEM);
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    @Override
    public void delete() throws Exception {
        String METHOD_NAME = "delete";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, Constants.USER_SYSTEM);
            System.out.print("delete:" + order.getValue() + "," + removeItem);
            orderFacade.deleteOrderDetail(new OrderDetail(new OrderDetailPK(order.getValue(), removeItem)));
            find();
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, Constants.USER_SYSTEM);
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    @Override
    public void update() throws Exception {
        String METHOD_NAME = "update";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, Constants.USER_SYSTEM);
            boolean chkCountItems = true;
            System.out.println("macAddress:" + macAddress);
            OrderList oList = orderFacade.findOrderList(macAddress);
            if (StringUtil.isNotNullOrNotEmpty(oList)) {
                for (int i = 0; i < oList.getOrderDetailList().size(); i++) {
                    OrderDetail oDetail = (OrderDetail) oList.getOrderDetailList().get(i);
                    if (oDetail.getOrderDetailPK().getProductCode().equals(product.getProductCode())) {
                        chkCountItems = false;
                        break;
                    }
                }
                if (chkCountItems) {
                    System.out.println(sumItems);
                    if (NumberUtil.getInteger(sumItems) > 9) {
                        List<String> error = new ArrayList<String>();
                        error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{MsgBundleLoader.getMessage("error.shop", MsgBundleLoader.MESSAGE_PATH)});
                        JsfUtil.addErrorMessages(error);
                        return;
                    }
                }
                //
                boolean chk = true;
                for (int i = 0; i < oList.getOrderDetailList().size(); i++) {
                    OrderDetail oDetail = (OrderDetail) oList.getOrderDetailList().get(i);
                    if (oDetail.getOrderDetailPK().getProductCode().equals(product.getProductCode())) {
                        if (oDetail.getItems() < 5) {
                            oDetail.setItems(oDetail.getItems() + 1);
                            orderFacade.editOrderDetail(oDetail);
                        }
                        chk = false;
                        break;
                    }
                }
                if (chk) {
                    OrderDetail obj = new OrderDetail(new OrderDetailPK(macAddress, product.getProductCode()));
                    obj.setItems(1);
                    obj.setPrices(product.getProductPrice());
                    obj.setCreateDt(DateTimeUtil.currentDate());
                    orderFacade.createOrderDetail(obj);
                }
            } else {
                OrderList o = new OrderList(macAddress);
                List<OrderDetail> list = new ArrayList<OrderDetail>();
                OrderDetail obj = new OrderDetail(new OrderDetailPK(macAddress, product.getProductCode()));
                obj.setItems(1);
                obj.setPrices(product.getProductPrice());
                obj.setCreateDt(DateTimeUtil.currentDate());
                list.add(obj);
                o.setOrderDetailList(list);
                orderFacade.createOrderList(o);
            }
            find();
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, Constants.USER_SYSTEM);
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
        String METHOD_NAME = "reportPDF";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, Constants.USER_SYSTEM);
            ReportUtil reportUtil = new ReportUtil();
            Collection reportList = new ArrayList();
            HashMap parameter = new HashMap();
            System.out.println("purchaseCode:" + purchaseCode);
            Purchase p = purchaseFacade.findPurchase(purchaseCode);
            if (StringUtil.isNotNullOrNotEmpty(p)) {
                parameter.put(ReportUtil.PURCHASE_CODE, purchaseCode);
                parameter.put(ReportUtil.PURCHASE_DATE, DateTimeUtil.dateToString(p.getCreateDt(), "dd/MM/yyyy HH:mm", locale.getLocale()));
                parameter.put(ReportUtil.CUSTOMER_NAME, p.getCustomerName());
                parameter.put(ReportUtil.CUSTOMER_EMAIL, p.getCustomerEmail());
                parameter.put(ReportUtil.CUSTOMER_MOBILE, p.getCustomerMobile());
                parameter.put(ReportUtil.CUSTOMER_ADDRESS, p.getCustomerAddress());
                parameter.put(ReportUtil.TOTAL_STR, BahtTextUtil.getBahtTextUtil(new BigDecimal(p.getTotalPrice())));
                for (int i = 0; i < p.getPurchaseDetailList().size(); i++) {
                    PurchaseDetail pd = (PurchaseDetail) p.getPurchaseDetailList().get(i);
                    ReportBean bean = new ReportBean();
                    bean.setItem(i + 1);
                    bean.setProductName(pd.getProductDetail().getProductName());
                    bean.setPrice(NumberUtil.getDouble(pd.getProductPrice()));
                    bean.setQty(NumberUtil.getInteger(pd.getProductQty()));
                    bean.setTotalPrice(NumberUtil.getDouble(pd.getProductTotal()));
                    reportList.add(bean);
                }
            } else {
                reportList.add(new ReportBean());
            }
            parameter.put(ReportConstant.PRINT_DATE, DateTimeUtil.dateToString(DateTimeUtil.currentDate(), "dd/MM/yyyy HH:mm", locale.getLocale()));
            reportUtil.export(ReportConstant.PUR_JASPER, ReportConstant.PUR_CODE, parameter, reportList, false);
        } catch (Exception ex) {
            //Root cause
            while (ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }
            //
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, Constants.USER_SYSTEM);
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    @Override
    public void reportExcel() throws Exception {
    }

    public String getProductCategoryS() {
        return productCategoryS;
    }

    public void setProductCategoryS(String productCategoryS) {
        this.productCategoryS = productCategoryS;
    }

    public List<DataTableBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataTableBean> dataList) {
        this.dataList = dataList;
    }

    public List<DataTableBean> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<DataTableBean> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }

    public String getProductNameS() {
        return productNameS;
    }

    public void setProductNameS(String productNameS) {
        this.productNameS = productNameS;
    }

    public ProductDetail getProduct() {
        return product;
    }

    public void setProduct(ProductDetail product) {
        this.product = product;
    }

    public DataTableBean getOrder() {
        return order;
    }

    public void setOrder(DataTableBean order) {
        this.order = order;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<SelectItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SelectItem> itemList) {
        this.itemList = itemList;
    }

    public String getRemoveItem() {
        return removeItem;
    }

    public void setRemoveItem(String removeItem) {
        this.removeItem = removeItem;
    }

    public boolean isShopFlag() {
        return shopFlag;
    }

    public void setShopFlag(boolean shopFlag) {
        this.shopFlag = shopFlag;
    }

    public boolean isLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Integer getSumItems() {
        return sumItems;
    }

    public void setSumItems(Integer sumItems) {
        this.sumItems = sumItems;
    }

    public boolean isCusFlag() {
        return cusFlag;
    }

    public void setCusFlag(boolean cusFlag) {
        this.cusFlag = cusFlag;
    }

    public boolean isBankFlag() {
        return bankFlag;
    }

    public void setBankFlag(boolean bankFlag) {
        this.bankFlag = bankFlag;
    }

    public boolean isFinishFlag() {
        return finishFlag;
    }

    public void setFinishFlag(boolean finishFlag) {
        this.finishFlag = finishFlag;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public LocaleController getLocale() {
        return locale;
    }

    public void setLocale(LocaleController locale) {
        this.locale = locale;
    }
}
