package com.ecom.auth.controller;

import com.ecom.common.util.JsfUtil;
import com.ecom.common.util.MD5Generator;
import com.ecom.common.util.MsgBundleLoader;
import com.ecom.controller.UserInfoController;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;

@ManagedBean(name = AuthenticationController.CONTROLLER_NAME)
@Component
@Scope(value = "session")
public class AuthenticationController implements Serializable {

    public static final String CONTROLLER_NAME = "authenticationController";
    @Autowired
    @Qualifier("authenticationManager")
    protected AuthenticationManager authenticationManager;
    private String username;
    private String password;
    private String cusUsername;
    private String cusPassword;

    public AuthenticationController() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getCusUsername() {
        return cusUsername;
    }

    public void setCusUsername(String cusUsername) {
        this.cusUsername = cusUsername;
    }

    public String getCusPassword() {
        return cusPassword;
    }

    public void setCusPassword(String cusPassword) {
        this.cusPassword = cusPassword;
    }

    public void login() {
        ExternalContext context = null;
        try {
            context = FacesContext.getCurrentInstance().getExternalContext();
            if (JsfUtil.getExternalContext().getUserPrincipal() != null) {
                context.redirect(context.getRequestContextPath());
                FacesContext.getCurrentInstance().responseComplete();
            }
            //?faces-redirect=true
            if (StringUtils.isEmpty(username)) {
                JsfUtil.addErrorMessage(MsgBundleLoader.getMessage("error.title"), MsgBundleLoader.getMessage("login.exception.username"));
                return;
            }
            if (StringUtils.isEmpty(password)) {
                JsfUtil.addErrorMessage(MsgBundleLoader.getMessage("error.title"), MsgBundleLoader.getMessage("login.exception.password"));
                return;
            }
            UserInfoController userInfo = (UserInfoController) JsfUtil.getManagedBean(UserInfoController.CONTROLLER_NAME);
            userInfo.setUsername(this.username);
            userInfo.setPssw(this.password);

            Authentication request = new UsernamePasswordAuthenticationToken(this.username, MD5Generator.md5(this.password));
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            userInfo.init();
            DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) context.getSessionMap().get(WebAttributes.SAVED_REQUEST);
            context.redirect(defaultSavedRequest.getRedirectUrl());
            FacesContext.getCurrentInstance().responseComplete();

        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            JsfUtil.addErrorMessage(MsgBundleLoader.getMessage("error.title"), MsgBundleLoader.getMessage("error.login.bad-credentials"));
        } catch (Exception ex) {
            ex.printStackTrace();
            JsfUtil.addErrorMessage(MsgBundleLoader.getMessage("error.title"), MsgBundleLoader.getMessage("error.login.bad-credentials"));
        }
    }
    
    public void loginCus() {
        ExternalContext context = null;
        try {
            context = FacesContext.getCurrentInstance().getExternalContext();
            if (JsfUtil.getExternalContext().getUserPrincipal() != null) {
                context.redirect(context.getRequestContextPath());
                FacesContext.getCurrentInstance().responseComplete();
            }
            //?faces-redirect=true
            if (StringUtils.isEmpty(cusUsername)) {
                JsfUtil.addErrorMessage(MsgBundleLoader.getMessage("error.title"), MsgBundleLoader.getMessage("login.exception.username"));
                return;
            }
            if (StringUtils.isEmpty(cusPassword)) {
                JsfUtil.addErrorMessage(MsgBundleLoader.getMessage("error.title"), MsgBundleLoader.getMessage("login.exception.password"));
                return;
            }
            UserInfoController userInfo = (UserInfoController) JsfUtil.getManagedBean(UserInfoController.CONTROLLER_NAME);
            userInfo.setUsername(this.cusUsername);
            userInfo.setPssw(this.cusPassword);

            Authentication request = new UsernamePasswordAuthenticationToken(this.cusUsername, MD5Generator.md5(this.cusPassword));
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            userInfo.init();
            DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) context.getSessionMap().get(WebAttributes.SAVED_REQUEST);
            context.redirect(defaultSavedRequest.getRedirectUrl());
            FacesContext.getCurrentInstance().responseComplete();

        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            JsfUtil.addErrorMessage(MsgBundleLoader.getMessage("error.title"), MsgBundleLoader.getMessage("error.login.bad-credentials"));
        } catch (Exception ex) {
            ex.printStackTrace();
            JsfUtil.addErrorMessage(MsgBundleLoader.getMessage("error.title"), MsgBundleLoader.getMessage("error.login.bad-credentials"));
        }
    }

    public String logout() throws IOException {
        this.username = "";
        this.password = "";
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + "/j_spring_security_logout?faces-redirect=true");
        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }
}
