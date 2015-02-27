/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.bo.system;

import com.ecom.ejb.common.util.DateTimeUtil;
import com.ecom.ejb.common.util.StringUtil;
import com.ecom.ejb.dao.SysGenCodeDAOLocal;
import com.ecom.ejb.entity.SysGenCode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ACOP_LAR
 */
@Stateless
public class SysGenCodeBO implements SysGenCodeBOLocal {

    @EJB
    private SysGenCodeDAOLocal sysGenCodeDAO;

    @Override
    public String genCodeByCodeType(String codeType) throws Exception {
        String genCode = "";
        int value;
        SysGenCode sysGenCode = sysGenCodeDAO.findByType(codeType);
        if (sysGenCode != null) {
            NumberFormat formatter = new DecimalFormat(sysGenCode.getCodeFormat());
            if (StringUtil.isNotNullOrNotEmpty(sysGenCode.getCodeYy())) {
                if (!DateTimeUtil.dateToString(DateTimeUtil.currentDate(), "yyyy").equals(sysGenCode.getCodeYy())) {
                    sysGenCode.setCodeYy(DateTimeUtil.dateToString(DateTimeUtil.currentDate(), "yyyy"));
                }
            }
            if (StringUtil.isNotNullOrNotEmpty(sysGenCode.getCodeMm())) {
                if (!DateTimeUtil.dateToString(DateTimeUtil.currentDate(), "MM").equals(sysGenCode.getCodeMm())) {
                    sysGenCode.setCodeMm(DateTimeUtil.dateToString(DateTimeUtil.currentDate(), "MM"));
                    sysGenCode.setCodeValue(0);
                }
            }
            value = sysGenCode.getCodeValue() + 1;
            genCode = StringUtil.getIsNull(sysGenCode.getCodeHead());
            genCode = genCode + StringUtil.getIsNull(sysGenCode.getCodeYy());
            genCode = genCode + StringUtil.getIsNull(sysGenCode.getCodeMm());
            genCode = genCode + formatter.format(value);
            sysGenCode.setCodeValue(value);
            sysGenCodeDAO.edit(sysGenCode);
        }
        return genCode;
    }
}
