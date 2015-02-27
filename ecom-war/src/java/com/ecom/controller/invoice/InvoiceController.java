/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.controller.invoice;

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
import com.ecom.ejb.facade.InvoiceFacadeRemote;
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
import org.apache.log4j.Logger;

@ManagedBean(name = InvoiceController.BEAN_NAME)
@SessionScoped
public class InvoiceController extends MethodController implements Serializable {

    private static Logger logger = LogUtil.getLogger(InvoiceController.class);
    public static final String BEAN_NAME = "invoiceController";
    @EJB
    private InvoiceFacadeRemote invoiceFacade;
    private UserInfoController userInfo;
    private LocaleController locale;
    private NavigationController navigationController;
    private String invoiceNoS;
    private String purchaseCodeS;
    private String customerNameS;
    private String customerMobileS;
    private Date dateFromS;
    private Date dateToS;
    //
    private String invoiceNo;
    private List<DataTableBean> dataList;
    private boolean check;
    private Invoice detail;

    @PostConstruct
    @Override
    public void init() throws Exception {
        String METHOD_NAME = "init";
        try {
            userInfo = (UserInfoController) JsfUtil.getManagedBean(UserInfoController.CONTROLLER_NAME);
            locale = (LocaleController) JsfUtil.getManagedBean(LocaleController.CONTROLLER_NAME);
            navigationController = (NavigationController) JsfUtil.getManagedBean(NavigationController.CONTROLLER_NAME);
            dateFromS = DateTimeUtil.currentDate(90);
            dateToS = DateTimeUtil.currentDate();
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
            LogUtil.infoLog(logger, this.getClass(), METHOD_NAME, userInfo.getUsername());
            detail = invoiceFacade.findInvoice(invoiceNo);
            if (StringUtil.isNullOrEmpty(detail)) {
                List<String> error = new ArrayList<String>();
                error = ExceptionUtil.addLineException(error, "errors.1009", new String[]{invoiceNo});
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
            List<Invoice> invList = invoiceFacade.findListInvoiceByCri(invoiceNoS, purchaseCodeS, customerNameS, customerMobileS, dateFromS, dateToS);
            for (int i = 0; i < invList.size(); i++) {
                Invoice inv = (Invoice) invList.get(i);
                DataTableBean bean = new DataTableBean();
                bean.setSeq(i + 1);
                bean.setInvoice(inv);
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
            Invoice invoice = invoiceFacade.findInvoice(invoiceNo);
            if (StringUtil.isNotNullOrNotEmpty(invoice)) {
                parameter.put(ReportUtil.INVOICEC_ODE, invoiceNo);
                parameter.put(ReportUtil.INVOICE_DATE, DateTimeUtil.dateToString(invoice.getCreateDt(), "dd/MM/yyyy HH:mm", locale.getLocale()));
                parameter.put(ReportUtil.CUSTOMER_NAME, invoice.getCustomerName());
                parameter.put(ReportUtil.CUSTOMER_EMAIL, invoice.getCustomerEmail());
                parameter.put(ReportUtil.CUSTOMER_MOBILE, invoice.getCustomerMobile());
                parameter.put(ReportUtil.CUSTOMER_ADDRESS, invoice.getCustomerAddress());
                parameter.put(ReportUtil.TOTAL_STR, BahtTextUtil.getBahtTextUtil(new BigDecimal(invoice.getTotalPrice())));
                for (int i = 0; i < invoice.getInvoiceDetailList().size(); i++) {
                    InvoiceDetail invd = (InvoiceDetail) invoice.getInvoiceDetailList().get(i);
                    ReportBean bean = new ReportBean();
                    bean.setItem(i + 1);
                    bean.setProductName(invd.getProductDetail().getProductName());
                    bean.setPrice(NumberUtil.getDouble(invd.getProductPrice()));
                    bean.setQty(NumberUtil.getInteger(invd.getProductQty()));
                    bean.setTotalPrice(NumberUtil.getDouble(invd.getProductTotal()));
                    reportList.add(bean);
                }
            } else {
                reportList.add(new ReportBean());
            }
            parameter.put(ReportConstant.PRINT_DATE, DateTimeUtil.dateToString(DateTimeUtil.currentDate(), "dd/MM/yyyy HH:mm", locale.getLocale()));
            reportUtil.export(ReportConstant.INV_JASPER, ReportConstant.INV_CODE, parameter, reportList, false);
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

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceNoS() {
        return invoiceNoS;
    }

    public void setInvoiceNoS(String invoiceNoS) {
        this.invoiceNoS = invoiceNoS;
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

    public Invoice getDetail() {
        return detail;
    }

    public void setDetail(Invoice detail) {
        this.detail = detail;
    }

    public LocaleController getLocale() {
        return locale;
    }

    public void setLocale(LocaleController locale) {
        this.locale = locale;
    }
}
