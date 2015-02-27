package com.ecom.controller;

import com.ecom.common.util.JsfUtil;
import com.ecom.common.util.MsgBundleLoader;
import com.ecom.ejb.entity.AuthUser;
import com.ecom.ejb.facade.UserFacadeRemote;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.StringUtils;

@ManagedBean(name = UserInfoController.CONTROLLER_NAME)
@SessionScoped
public class UserInfoController implements Serializable {

    public static final String CONTROLLER_NAME = "userInfoController";
    private String username;
    private AuthUser authUser;
    private String pssw;
    private String firstName;
    private String lastName;
    @EJB
    private UserFacadeRemote userFacade;

    public UserInfoController() {
    }

    public void init() {
        try {
            System.out.println("UserInfoController.init");
            username = JsfUtil.getExternalContext().getUserPrincipal().getName();
            authUser = userFacade.findAuthUserByUsername(this.username);
            if (authUser == null) {
                JsfUtil.addErrorMessage(MsgBundleLoader.getMessage("error.title"), MsgBundleLoader.getMessage("error.login.bad-credentials"));
                return;
            } else {
                String l = authUser.getLocale();
                firstName = authUser.getFirstName();
                lastName = authUser.getLastName();
                if (!StringUtils.isEmpty(l)) {
                    LocaleController localeController = (LocaleController) JsfUtil.getManagedBean(LocaleController.CONTROLLER_NAME);
                    localeController.changeLocale(l.substring(0, 2));
                    FacesContext.getCurrentInstance().getViewRoot().setLocale(localeController.getLocale());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public String getPssw() {
        return pssw;
    }

    public void setPssw(String pssw) {
        this.pssw = pssw;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}