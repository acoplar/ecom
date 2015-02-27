package com.ecom.controller;

import java.io.Serializable;

public abstract class MethodController implements Serializable {

    public abstract void init() throws Exception;

    public abstract void findAll() throws Exception;

    public abstract void find() throws Exception;

    public abstract void insert() throws Exception;

    public abstract void update() throws Exception;

    public abstract void delete() throws Exception;

    public abstract void cancel() throws Exception;

    public abstract void reportPDF() throws Exception;

    public abstract void reportExcel() throws Exception;
}
