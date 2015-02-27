/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ACOP_LAR
 */
@Entity
@Table(name = "pay_channel")
@NamedQueries({
    @NamedQuery(name = "PayChannel.findAll", query = "SELECT p FROM PayChannel p")})
public class PayChannel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "channel_code")
    private String channelCode;
    @Size(max = 100)
    @Column(name = "channel_name_en")
    private String channelNameEn;
    @Size(max = 100)
    @Column(name = "channel_name_th")
    private String channelNameTh;

    public PayChannel() {
    }

    public PayChannel(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelNameEn() {
        return channelNameEn;
    }

    public void setChannelNameEn(String channelNameEn) {
        this.channelNameEn = channelNameEn;
    }

    public String getChannelNameTh() {
        return channelNameTh;
    }

    public void setChannelNameTh(String channelNameTh) {
        this.channelNameTh = channelNameTh;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (channelCode != null ? channelCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PayChannel)) {
            return false;
        }
        PayChannel other = (PayChannel) object;
        if ((this.channelCode == null && other.channelCode != null) || (this.channelCode != null && !this.channelCode.equals(other.channelCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecom.ejb.entity.PayChannel[ channelCode=" + channelCode + " ]";
    }
    
}
