/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.dao;

import com.ecom.ejb.entity.PayChannel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ACOP_LAR
 */
@Local
public interface PayChannelDAOLocal {

    void create(PayChannel payChannel) throws Exception;

    void edit(PayChannel payChannel) throws Exception;

    void remove(PayChannel payChannel) throws Exception;

    PayChannel find(Object id) throws Exception;

    List<PayChannel> findAll() throws Exception;

    int count() throws Exception;
}
