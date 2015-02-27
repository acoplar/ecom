/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.SysGenCode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface SysGenCodeDAOLocal {

    void create(SysGenCode sysGenCode) throws Exception;

    void edit(SysGenCode sysGenCode) throws Exception;

    void remove(SysGenCode sysGenCode) throws Exception;

    SysGenCode find(Object id) throws Exception;

    List<SysGenCode> findAll() throws Exception;

    int count() throws Exception;

    SysGenCode findByType(String codeType) throws Exception;
}
