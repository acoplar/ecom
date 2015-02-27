/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.facade;

import com.ecom.ejb.bo.system.SysGenCodeBOLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class SysGenCodeFacade implements SysGenCodeFacadeRemote {

    @EJB
    private SysGenCodeBOLocal sysGenCodeBO;

    @Override
    public String genCodeByCodeType(String codeType) throws Exception {
        return sysGenCodeBO.genCodeByCodeType(codeType);
    }
}
