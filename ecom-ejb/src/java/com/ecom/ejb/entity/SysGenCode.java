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
@Table(name = "sys_gen_code")
@NamedQueries({
    @NamedQuery(name = "SysGenCode.findAll", query = "SELECT s FROM SysGenCode s")})
public class SysGenCode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODE_TYPE")
    private String codeType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODE_HEAD")
    private String codeHead;
    @Size(max = 4)
    @Column(name = "CODE_YY")
    private String codeYy;
    @Size(max = 2)
    @Column(name = "CODE_MM")
    private String codeMm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODE_FORMAT")
    private String codeFormat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODE_VALUE")
    private int codeValue;

    public SysGenCode() {
    }

    public SysGenCode(Integer id) {
        this.id = id;
    }

    public SysGenCode(Integer id, String codeType, String codeHead, String codeFormat, int codeValue) {
        this.id = id;
        this.codeType = codeType;
        this.codeHead = codeHead;
        this.codeFormat = codeFormat;
        this.codeValue = codeValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCodeHead() {
        return codeHead;
    }

    public void setCodeHead(String codeHead) {
        this.codeHead = codeHead;
    }

    public String getCodeYy() {
        return codeYy;
    }

    public void setCodeYy(String codeYy) {
        this.codeYy = codeYy;
    }

    public String getCodeMm() {
        return codeMm;
    }

    public void setCodeMm(String codeMm) {
        this.codeMm = codeMm;
    }

    public String getCodeFormat() {
        return codeFormat;
    }

    public void setCodeFormat(String codeFormat) {
        this.codeFormat = codeFormat;
    }

    public int getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(int codeValue) {
        this.codeValue = codeValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SysGenCode)) {
            return false;
        }
        SysGenCode other = (SysGenCode) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecom.ejb.entity.SysGenCode[ id=" + id + " ]";
    }
    
}
