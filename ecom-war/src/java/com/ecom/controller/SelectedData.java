/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.controller;

import java.io.Serializable;

public class SelectedData implements Serializable {

    private String value;
    private String label;
    private String description;
    private boolean disabled;

    public SelectedData() {
    }

    public SelectedData(String value, String label, String description, boolean disabled) {
        this.value = value;
        this.label = label;
        this.description = description;
        this.disabled = disabled;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
