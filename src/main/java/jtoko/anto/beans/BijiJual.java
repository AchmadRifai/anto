/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto.beans;

import java.sql.SQLException;
import java.text.ParseException;
import jtoko.anto.Db;
import jtoko.anto.tools.Uang;

/**
 *
 * @author ashura
 */
public class BijiJual {
    private String brg;
    private double jum, max;
    private Uang sat, tot;

    public BijiJual(String brg, Db d) throws SQLException, ParseException {
        this.brg = brg;
        muat(d);
    }

    public BijiJual(String brg, double jum, double max, Uang sat, Uang tot) {
        this.brg = brg;
        this.jum = jum;
        this.max = max;
        this.sat = sat;
        this.tot = tot;
    }

    private void muat(Db d) throws SQLException, ParseException {
        var p = d.prep("select jual,stok from barang where kode=? and not hapus");
        p.setString(1, brg);
        var r = p.executeQuery();
        if (r.next()) {
            sat = new Uang(r.getString("jual"));
            max = r.getDouble("stok");
            jum = 0;
            tot = sat.mul(jum);
        } r.close();
        p.close();
    }

    public String getBrg() {
        return brg;
    }

    public void setBrg(String brg) {
        this.brg = brg;
    }

    public double getJum() {
        return jum;
    }

    public void setJum(double jum) {
        this.jum = jum;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public Uang getSat() {
        return sat;
    }

    public void setSat(Uang sat) {
        this.sat = sat;
    }

    public Uang getTot() {
        return tot;
    }

    public void setTot(Uang tot) {
        this.tot = tot;
    }
}
