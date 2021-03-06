package com.ecom.common.theme;

/*
 * Copyright (C) 2012 Scalaris AG, Germany. All rights reserved.
 *
 * $Id: ThemeConverter.java 984 2012-02-29 10:50:54Z ovaraksin@gmail.com $
 */
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.ecom.common.theme.ThemeConverter")
public class ThemeConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext context, final UIComponent component, final String value) {
        return AvailableThemes.getInstance().getThemeForName(value);
    }

    @Override
    public String getAsString(final FacesContext context, final UIComponent component, final Object value) {
        return ((Theme) value).getName();
    }
}
