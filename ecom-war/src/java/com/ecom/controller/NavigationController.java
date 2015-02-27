package com.ecom.controller;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;

@ManagedBean(name = NavigationController.CONTROLLER_NAME)
@SessionScoped
public class NavigationController implements Serializable {

    public static final String CONTROLLER_NAME = "navigationController";
    private String currentPage;

    public NavigationController() {
    }

    @PostConstruct
    public void init() {
        FacesContext cont = FacesContext.getCurrentInstance();
        cont.getViewRoot().getViewMap();
        currentPage = "pur/purchase";
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void nextPage(String page, String controller) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map sessionMap = fc.getExternalContext().getSessionMap();
        if (!StringUtils.isEmpty(controller)) {
            if (sessionMap.containsKey(controller)) {
                sessionMap.remove(controller);
            }
        }
        this.currentPage = page;
    }
}