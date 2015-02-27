package com.ecom.controller;

import com.ecom.common.util.JsfUtil;
import com.ecom.common.util.LoaderConfig;
import com.ecom.ejb.common.util.DateTimeUtil;
import com.ecom.ejb.entity.AuthUser;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = LocaleController.CONTROLLER_NAME)
@SessionScoped
public class LocaleController implements Serializable {

    public static final String CONTROLLER_NAME = "localeController";
    private Locale locale;
    private String localeAddress;
    private Date currentDt;
    @ManagedProperty(value = "#{" + UserInfoController.CONTROLLER_NAME + "}")
    private UserInfoController userInfo;

    public LocaleController() {
    }

    @PostConstruct
    public void init() {
        String l = LoaderConfig.getConfig("default_locale");
        locale = new Locale(l.substring(0, 2), l.substring(3, 5));
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);

        HttpServletRequest req = (HttpServletRequest) JsfUtil.getExternalContext().getRequest();
        localeAddress = req.getRemoteAddr();
        currentDt = DateTimeUtil.currentDate();
    }

    public void changeLocale(String l) {
        if (l.toLowerCase().equals("th")) {
            locale = new Locale("th", "TH");
        }
        if (l.toLowerCase().equals("th_TH")) {
            locale = new Locale("th", "TH");
        }
        if (l.toLowerCase().equals("en")) {
            locale = new Locale("en", "US");
        }
        if (l.toLowerCase().equals("en_US")) {
            locale = new Locale("en", "US");
        }
    }

    public void changeLocaleByUser(String l) {
        if (l.toLowerCase().equals("th")) {
            locale = new Locale("th", "TH");
        } else if (l.toLowerCase().equals("th_TH")) {
            locale = new Locale("th", "TH");
        } else if (l.toLowerCase().equals("en")) {
            locale = new Locale("en", "US");
        } else if (l.toLowerCase().equals("en_US")) {
            locale = new Locale("en", "US");
        } else {
            locale = new Locale("th", "TH");
        }
        if (userInfo == null) {
            userInfo = (UserInfoController) JsfUtil.getManagedBean(UserInfoController.CONTROLLER_NAME);
        }
        try {
            AuthUser auth = userInfo.getAuthUser();
            auth.setLocale(locale.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getLocaleAddress() {
        return localeAddress;
    }

    public void setLocaleAddress(String localeAddress) {
        this.localeAddress = localeAddress;
    }

    public Date getCurrentDt() {
        return currentDt;
    }

    public void setCurrentDt(Date currentDt) {
        this.currentDt = currentDt;
    }

    public UserInfoController getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoController userInfo) {
        this.userInfo = userInfo;
    }
}
