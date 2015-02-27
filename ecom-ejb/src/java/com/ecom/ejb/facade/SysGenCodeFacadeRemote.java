/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.facade;

import javax.ejb.Remote;

/**
 *
 * @author ACOP_LAR
 */
@Remote
public interface SysGenCodeFacadeRemote {

    public String genCodeByCodeType(String codeType) throws Exception;
}
