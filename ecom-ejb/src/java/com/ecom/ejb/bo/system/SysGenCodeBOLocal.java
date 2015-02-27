/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.bo.system;

import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface SysGenCodeBOLocal {
    
    String genCodeByCodeType(String codeType) throws Exception;
}
