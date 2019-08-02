/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto.tools;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import javax.swing.JPanel;
import jtoko.anto.Db;
import jtoko.anto.beans.BrgJual;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author ashura
 */
public class Analizer {

    public static void brg10Laku(Db d, JPanel parent) throws SQLException {
        List<BrgJual> l = brgTerjualBln(d), l2 = new java.util.LinkedList<>();
        for (int x = 0; x < 10; x++) l2.add(l.get(x));
        var dcd = new org.jfree.data.category.DefaultCategoryDataset();
        for (BrgJual b : l2) dcd.setValue(b.getQty(), b.getNama(), "PCS");
        var jfc = ChartFactory.createBarChart("10 Barang Terlaku Bulan Ini", "Barang", "Jumlah", dcd, 
                PlotOrientation.VERTICAL, true, true, false);
        if (parent.getComponentCount() > 0) parent.removeAll();
        parent.add(new org.jfree.chart.ChartPanel(jfc));
    }

    public static List<BrgJual> brgTerjualBln(Db d) throws SQLException {
        List<BrgJual> l = new java.util.LinkedList<>();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        java.sql.Date t1 = new java.sql.Date(c.getTime().getTime());
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        java.sql.Date t2 = new java.sql.Date(c.getTime().getTime());
        java.sql.PreparedStatement p = d.prep("select distinct brg from item_ju where nota in("
                + "select nota from jual where not hapus and tgl between ? and ?)");
        p.setDate(1, t1);
        p.setDate(2, t2);
        java.sql.ResultSet r = p.executeQuery();
        while (r.next()) l.add(new BrgJual(r.getString("brg"), d, t1, t2));
        r.close();
        p.close();
        l.sort((BrgJual a, BrgJual b) -> compBrgJual(a, b));
        return l;
    }

    private static int compBrgJual(BrgJual a, BrgJual b) {
        int i = 0;
        if (null != a.getQty() && null != b.getQty()) switch (a.getQty().compareTo(b.getQty())) {
            case 1:
                i = -1;
                break;
            case -1:
                i = 1;
                break;
            default:
                i = 0;
                break;
        } return i;
    }

    public static void brgNull(JPanel p) {
        var dcd = new org.jfree.data.category.DefaultCategoryDataset();
        var jfc = ChartFactory.createBarChart("10 Barang Terlaku Bulan Ini", "Barang", "Jumlah", dcd, 
                PlotOrientation.VERTICAL, true, true, false);
        p.add(new org.jfree.chart.ChartPanel(jfc));
    }

    public static void laba7h(Db d, JPanel p) throws SQLException {
        List<Date> lt = list7h();
        List<jtoko.anto.beans.Laba> l = new java.util.LinkedList<>();
        for (Date t:lt) l.add(new jtoko.anto.beans.Laba(t, d));
        var dcd = new org.jfree.data.category.DefaultCategoryDataset();
        for (jtoko.anto.beans.Laba la:l) dcd.setValue(la.getJual().min(la.getBeli()), "Laba", la.getTgl());
        var jfc = ChartFactory.createBarChart("Laba 7 Hari", "Tanggal", "IDR", dcd, 
                PlotOrientation.VERTICAL, true, true, false);
        if (0 < p.getComponentCount()) p.removeAll();
        p.add(new org.jfree.chart.ChartPanel(jfc));
    }

    private static List<Date> list7h() {
        List<Date> l = new java.util.LinkedList<>();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, c.getActualMinimum(Calendar.DAY_OF_WEEK));
        var t1 = new Date(c.getTime().getTime());
        c.set(Calendar.DAY_OF_WEEK, c.getActualMaximum(Calendar.DAY_OF_WEEK));
        var t2 = new Date(c.getTime().getTime());
        for (Date d = t1; d.before(t2)||d.equals(t2); d = Date.valueOf(d.toLocalDate().plusDays(1)))
            l.add(d);
        return l;
    }
    
}
