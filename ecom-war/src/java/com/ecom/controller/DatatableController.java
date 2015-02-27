package com.ecom.controller;

import com.ecom.common.constants.Constants;
import java.io.Serializable;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;

@ManagedBean(name = DatatableController.CONTROLLER_NAME)
@SessionScoped
public class DatatableController implements Serializable {

    public static final String CONTROLLER_NAME = "datatableController";
    private DataTable defaultDataTable;

    public DataTable getDefaultDataTable() {
        DataTable dataTable = new DataTable();
        dataTable.setRows(Integer.parseInt(Constants.DATATABLE_ROWS));
        dataTable.setPaginator(true);
        dataTable.setPaginatorPosition(Constants.DATATABLE_PAGINATOR_POSITION);
        dataTable.setPaginatorTemplate(Constants.DATATABLE_PAGINATOR_TEMPLATE);
        dataTable.setRowsPerPageTemplate(Constants.DATATABLE_ROWS_TEMPLATE);
        dataTable.setRowIndexVar(Constants.DATATABLE_ROW_INDEX_VAR);
        ExpressionFactory exp = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
        ELContext el = FacesContext.getCurrentInstance().getELContext();
        ValueExpression valExp = exp.createValueExpression(el, "#{rowNumber mod 2 eq 0 ? 'even-row' : 'odd-row'}", String.class);
        dataTable.setValueExpression("rowStyleClass", valExp);
        dataTable.setEmptyMessage("");
        return dataTable;
    }

    public void setDefaultDataTable(DataTable defaultDataTable) {
        this.defaultDataTable = defaultDataTable;
    }
}
