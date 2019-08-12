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

    public void simpan(Db d, String nota) throws SQLException {
        if (ono(d, nota)) balik(d);
        if (!ono(d, nota)) mlebu(d, nota);
        else ubah(d, nota);
        sudo(d);
    }

    private void balik(Db d) throws SQLException {
        var p = d.prep("update barang set stok=stok+? where kode=? and not hapus");
        p.setDouble(1, jum);
        p.setString(2, brg);
        p.execute();
        p.close();
    }

    private void mlebu(Db d, String nota) throws SQLException {
        var p = d.prep("insert into item_ju values(?,?,?,?,?)");
        p.setString(1, nota);
        p.setString(2, brg);
        p.setDouble(3, jum);
        p.setLong(4, sat.toLong());
        p.setLong(5, tot.toLong());
        p.execute();
        p.close();
    }

    private void sudo(Db d) throws SQLException {
        var p = d.prep("update barang set stok=stok-? where kode=? and not hapus");
        p.setDouble(1, jum);
        p.setString(2, brg);
        p.execute();
        p.close();
    }

    private boolean ono(Db d, String nota) throws SQLException {
        boolean b;
        var p = d.prep("select jum from item_ju where nota=? and brg=?");
        p.setString(1, nota);
        p.setString(2, brg);
        var r = p.executeQuery();
        b = r.next();
        r.close();
        p.close();
        return b;
    }

    private void ubah(Db d, String nota) throws SQLException {
        var p = d.prep("update item_ju set jum=?,sat=?,tot=? where nota=? and brg=?");
        p.setDouble(1, jum);
        p.setLong(2, sat.toLong());
        p.setLong(3, tot.toLong());
        p.setString(4, nota);
        p.setString(5, brg);
        p.execute();
        p.close();
    }
}
