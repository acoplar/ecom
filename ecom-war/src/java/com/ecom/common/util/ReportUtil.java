package com.ecom.common.util;

import com.ecom.ejb.common.util.DateTimeUtil;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportUtil<T> {

    private JasperPrint jasperPrint;
    private FacesContext context;
    private ServletContext servletContext;
    private String jasperRealPath;
    public static final String JASPER_REPORT_PATH = "/resources/jasper/";
    public static final String SEPARATOR = "/";
    public static final String PREFIX = ".jasper";
    public static final String PREFIX_PDF = ".pdf";
    //
    public static final String PURCHASE_CODE = "purchaseCode";
    public static final String PURCHASE_DATE = "purchaseDate";
    public static final String INVOICEC_ODE = "invoiceCode";
    public static final String INVOICE_DATE = "invoiceDate";
    public static final String CUSTOMER_NAME = "customerName";
    public static final String CUSTOMER_EMAIL = "customerEmail";
    public static final String CUSTOMER_MOBILE = "customerMobile";
    public static final String CUSTOMER_ADDRESS = "customerAddress";
    public static final String TOTAL_STR = "totalStr";
    public static final String PRINT_DATE = "printDate";

    public ReportUtil() {
    }

    private String getReporLocale(String reportName) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Locale locale = fc.getViewRoot().getLocale();
        return (locale.getLanguage().equals("th")) ? reportName + "_th_TH" : locale.getLanguage().equals("en") ? reportName + "_en_US" : null;
    }

    public void export(String jasperName, String pdfCode, HashMap hashMap, Collection beanList, boolean isSave) {
        jasperName = getReporLocale(jasperName);
        try {
            context = FacesContext.getCurrentInstance();
            servletContext = (ServletContext) context.getExternalContext().getContext();
            jasperRealPath = servletContext.getRealPath(JASPER_REPORT_PATH + jasperName + PREFIX);
            String pdfName = pdfCode.concat("-").concat(DateTimeUtil.dateToString(DateTimeUtil.currentDate(), "yyyyMMddHHmmss"));
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(beanList);
            jasperPrint = JasperFillManager.fillReport(jasperRealPath, hashMap, beanCollectionDataSource);
            ExternalContext externalContext = context.getExternalContext();
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=" + pdfName + PREFIX_PDF);
            JasperExportManager.exportReportToPdfStream(jasperPrint, externalContext.getResponseOutputStream());
            context.getApplication().getStateManager().saveView(context);
            context.responseComplete();
        } catch (Exception ex) {
            Logger.getLogger(ReportUtil.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage(ex, ex.getMessage());
        }
    }
}
