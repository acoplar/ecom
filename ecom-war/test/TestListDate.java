
import com.ecom.controller.bean.DataTableBean;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ACOP_LAR
 */
public class TestListDate {

    public static void main(String[] args) {
        int a = 9;
        List<DataTableBean> h = new ArrayList<DataTableBean>();
        List<DataTableBean> d = new ArrayList<DataTableBean>();
        DataTableBean hh = new DataTableBean();
        DataTableBean dd = new DataTableBean();
        for (int i = 1; i <= a; i++) {
            if ((i % 4) == 0) {
                DataTableBean bean = new DataTableBean();
                bean.setValue(String.valueOf(i));
                d.add(bean);
                //
                hh = new DataTableBean();
                hh.setList(d);
                d = new ArrayList<DataTableBean>();
                h.add(hh);
            } else {
                DataTableBean bean = new DataTableBean();
                bean.setValue(String.valueOf(i));
                d.add(bean);
            }
        }
        hh = new DataTableBean();
        hh.setList(d);
        h.add(hh);
        for (int i = 0; i < h.size(); i++) {
            DataTableBean b = (DataTableBean) h.get(i);
            System.out.println("---->" + b.getValue());
            for (int j = 0; j < b.getList().size(); j++) {
                DataTableBean c = (DataTableBean) b.getList().get(j);
                System.out.println("--------->" + c.getValue());

            }
        }
    }
}
