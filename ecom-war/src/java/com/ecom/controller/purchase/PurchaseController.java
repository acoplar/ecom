/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.controller.purchase;

import com.ecom.common.constants.Constants;
import com.ecom.common.constants.ReportConstant;
import com.ecom.common.util.BahtTextUtil;
import com.ecom.common.util.ExceptionUtil;
import com.ecom.common.util.JsfUtil;
import com.ecom.common.util.NumberUtil;
import com.ecom.common.util.ReportUtil;
import com.ecom.common.util.StringUtil;
import com.ecom.controller.LocaleController;
import com.ecom.controller.MethodController;
import com.ecom.controller.NavigationController;
import com.ecom.controller.UserInfoController;
import com.ecom.controller.bean.DataTableBean;
import com.ecom.ejb.common.util.DateTimeUtil;
import com.ecom.ejb.entity.Invoice;
import com.ecom.ejb.entity.InvoiceDetail;
import com.ecom.ejb.entity.InvoiceDetailPK;
import com.ecom.ejb.entity.PayBank;
import com.ecom.ejb.entity.PayChannel;
import com.ecom.ejb.entity.Purchase;
import com.ecom.ejb.entity.PurchaseDetail;
import com.ecom.ejb.facade.InvoiceFacadeRemote;
import com.ecom.ejb.facade.PurchaseFacadeRemote;
import com.ecom.ejb.facade.SysGenCodeFacadeRemote;
import com.ecom.log.util.LogUtil;
import com.ecom.repoort.bean.ReportBean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;

@ManagedBean(name = PurchaseController.BEAN_NAME)
@SessionScoped
public class PurchaseController extends MethodController implements Serializable {

    private static Logger logger = LogUtil.getLogger(PurchaseController.class);
    public static final String BEAN_NAME = "purchaseController";
    @EJB
    private PurchaseFacadeRemote purchaseFacade;
    @EJB
    private InvoiceFacadeRemote invoiceFacade;
    @EJB
    private SysGenCodeFacadeRemote sysGenCodeFacade;
    private UserInfoController userInfo;
    private LocaleController locale;
    private NavigationController navigationController;
    private String purchaseCodeS;
    private String customerNameS;
    private String customerMobileS;
    private Date dateFromS;
    private Date dateToS;
    //
    private String payBank;
    private List<SelectItem> payBankList;
    private String payChannel;
    private List<SelectItem> payChannelList;
    private Double payment;
    private Date payDt;
    //
    private String purchaseCode;
    private List<DataTableBean> dataList;
    private boolean invoiceFlag;
    private boolean payStatus;
    private Purchase detail;

    @PostConstruct
    @Override
    public void init() throws Exception {
        String METHOD_NAME = "init";
        try {
            userInfo = (UserInfoController) JsfUtil.getManagedBean(UserInfoController.CONTROLLER_NAME);
            navigationController = (NavigationController) JsfUtil.getManagedBean(NavigationController.CONTROLLER_NAME);
            locale = (LocaleController) JsfUtil.getManagedBean(LocaleController.CONTROLLER_NAME);

            dateFromS = DateTimeUtil.currentDate(90);
            dateToS = DateTimeUtil.currentDate();
            //
            payBankList = new ArrayList<SelectItem>();
            payBankList.add(new SelectItem("", ""));
            List<PayBank> bList = purchaseFacade.findAllPayBank();
            for (int i = 0; i < bList.size(); i++) {
                PayBank item = (PayBank) bList.get(i);
                payBankList.add(new SelectItem(item.getBankCode(), item.getBankNameEn()));
            }
            payChannelList = new ArrayList<SelectItem>();
            payChannelList.add(new SelectItem("", ""));
            List<PayChannel> cList = purchaseFacade.findAllPayChannel();
            for (int i = 0; i < cList.size(); i++) {
                PayChannel item = (PayChannel) cList.get(i);
                payChannelList.add(new SelectItem(item.getChannelCode(), item.getChannelNameEn()));
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
    public void insert() throws Exception {
        String METHOD_NAME = "insert";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, userInfo.getUsername());
        } catch (Exception ex) {
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
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, userInfo.getUsername());
            detail = purchaseFacade.findPurchase(purchaseCode);
            invoiceFlag = true;
            payStatus = false;
            if ("Y".equals(detail.getPayStatus())) {
                payStatus = true;
            }
            if (StringUtil.isNullOrEmpty(detail)) {
                List<String> error = new ArrayList<String>();
                error = ExceptionUtil.addLineException(error, "errors.1009", new String[]{purchaseCode});
                JsfUtil.addErrorMessages(error);
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

    @Override
    public void findAll() throws Exception {
        String METHOD_NAME = "findAll";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, userInfo.getUsername());
            dataList = new ArrayList<DataTableBean>();
            List<Purchase> purList = purchaseFacade.findListPurchaseByCri(purchaseCodeS, customerNameS, customerMobileS, "N", dateFromS, dateToS);
            for (int i = 0; i < purList.size(); i++) {
                Purchase pur = (Purchase) purList.get(i);
                DataTableBean bean = new DataTableBean();
                bean.setSeq(i + 1);
                bean.setPurchase(pur);
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
            if (invoiceFlag) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceNo(sysGenCodeFacade.genCodeByCodeType(Constants.GEN_INVOICE_CODE));
                invoice.setPurchaseCode(detail.getPurchaseCode());
                invoice.setCustomerType(detail.getCustomerType());
                invoice.setCustomerName(detail.getCustomerName());
                invoice.setCustomerAddress(detail.getCustomerAddress());
                invoice.setCustomerEmail(detail.getCustomerEmail());
                invoice.setCustomerMobile(detail.getCustomerMobile());
                invoice.setPurchaseDt(detail.getCreateDt());
                invoice.setTotalItem(detail.getTotalItem());
                invoice.setTotalPrice(detail.getTotalPrice());
                invoice.setCreateDt(DateTimeUtil.currentDate());
                invoice.setCreateBy(userInfo.getAuthUser());
                List<InvoiceDetail> invList = new ArrayList<InvoiceDetail>();
                for (int i = 0; i < detail.getPurchaseDetailList().size(); i++) {
                    InvoiceDetail invD = new InvoiceDetail();
                    PurchaseDetail purD = (PurchaseDetail) detail.getPurchaseDetailList().get(i);
                    invD.setInvoiceDetailPK(new InvoiceDetailPK(invoice.getInvoiceNo(), purD.getProductDetail().getProductCode()));
                    invD.setInvoice(invoice);
                    invD.setProductDetail(purD.getProductDetail());
                    invD.setProductQty(purD.getProductQty());
                    invD.setProductPrice(purD.getProductPrice());
                    invD.setProductTotal(purD.getProductTotal());
                    invD.setCreateDt(DateTimeUtil.currentDate());
                    invList.add(invD);
                }
                invoice.setInvoiceDetailList(invList);
                invoiceFacade.createInvoice(invoice);
                detail.setPurchaseStatus("Y");
            }
            if (payStatus) {
                detail.setPayStatus("Y");
            } else {
                detail.setPayStatus("N");
            }
            purchaseFacade.editPurchase(detail);
            findAll();
            JsfUtil.addSuccessMessage("");
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
        String METHOD_NAME = "reportPDF";
        try {
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, userInfo.getUsername());
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
            LogUtil.errorLog(logger, this.getClass(), METHOD_NAME, ex, userInfo.getUsername());
            List<String> error = new ArrayList<String>();
            error = ExceptionUtil.addLineException(error, "errors.2001", new String[]{ex.getMessage()});
            JsfUtil.addErrorMessages(error);
        }
    }

    @Override
    public void reportExcel() throws Exception {
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

    public String getPurchaseCodeS() {
        return purchaseCodeS;
    }

    public void setPurchaseCodeS(String purchaseCodeS) {
        this.purchaseCodeS = purchaseCodeS;
    }

    public String getCustomerNameS() {
        return customerNameS;
    }

    public void setCustomerNameS(String customerNameS) {
        this.customerNameS = customerNameS;
    }

    public String getCustomerMobileS() {
        return customerMobileS;
    }

    public void setCustomerMobileS(String customerMobileS) {
        this.customerMobileS = customerMobileS;
    }

    public Date getDateFromS() {
        return dateFromS;
    }

    public void setDateFromS(Date dateFromS) {
        this.dateFromS = dateFromS;
    }

    public Date getDateToS() {
        return dateToS;
    }

    public void setDateToS(Date dateToS) {
        this.dateToS = dateToS;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public List<DataTableBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataTableBean> dataList) {
        this.dataList = dataList;
    }

    public boolean isInvoiceFlag() {
        return invoiceFlag;
    }

    public void setInvoiceFlag(boolean invoiceFlag) {
        this.invoiceFlag = invoiceFlag;
    }

    public Purchase getDetail() {
        return detail;
    }

    public void setDetail(Purchase detail) {
        this.detail = detail;
    }

    public String getPayBank() {
        return payBank;
    }

    public void setPayBank(String payBank) {
        this.payBank = payBank;
    }

    public List<SelectItem> getPayBankList() {
        return payBankList;
    }

    public void setPayBankList(List<SelectItem> payBankList) {
        this.payBankList = payBankList;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public List<SelectItem> getPayChannelList() {
        return payChannelList;
    }

    public void setPayChannelList(List<SelectItem> payChannelList) {
        this.payChannelList = payChannelList;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Date getPayDt() {
        return payDt;
    }

    public void setPayDt(Date payDt) {
        this.payDt = payDt;
    }

    public boolean isPayStatus() {
        return payStatus;
    }

    public void setPayStatus(boolean payStatus) {
        this.payStatus = payStatus;
    }

    public LocaleController getLocale() {
        return locale;
    }

    public void setLocale(LocaleController locale) {
        this.locale = locale;
    }
}
