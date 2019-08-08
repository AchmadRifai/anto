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
public class BijiPasok {
    private String brg;
    private Uang tot, ubah, sat;
    private double jum;

    public BijiPasok() {
    }

    public BijiPasok(String brg, Uang tot, Uang ubah, Uang sat, double jum) {
        this.brg = brg;
        this.tot = tot;
        this.ubah = ubah;
        this.sat = sat;
        this.jum = jum;
    }

    public BijiPasok(String brg, Db d) throws SQLException, ParseException {
        this.brg = brg;
        muatBrg(d);
    }

    public String getBrg() {
        return brg;
    }

    public void setBrg(String brg) {
        this.brg = brg;
    }

    public Uang getTot() {
        return tot;
    }

    public void setTot(Uang tot) {
        this.tot = tot;
    }

    public Uang getUbah() {
        return ubah;
    }

    public void setUbah(Uang ubah) {
        this.ubah = ubah;
    }

    public Uang getSat() {
        return sat;
    }

    public void setSat(Uang sat) {
        this.sat = sat;
    }

    public double getJum() {
        return jum;
    }

    public void setJum(double jum) {
        this.jum = jum;
    }

    private void muatBrg(Db d) throws SQLException, ParseException {
        var p = d.prep("select beli,jual from barang where not hapus and kode=?");
        p.setString(1, brg);
        var r = p.executeQuery();
        if (r.next()) {
            ubah = new Uang(r.getString("jual"));
            sat = new Uang(r.getString("beli"));
            jum = 0;
            tot = sat.mul(jum);
        } r.close();
        p.close();
    }
}
