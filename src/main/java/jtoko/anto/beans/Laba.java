/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto.beans;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import jtoko.anto.Db;

/**
 *
 * @author ashura
 */
public class Laba {
    private Date tgl;
    private BigDecimal jual, beli;
    private java.util.List<String> brg, nota;

    public Laba(Date tgl, Db d) throws SQLException {
        this.tgl = tgl;
        jual = BigDecimal.ZERO;
        beli = BigDecimal.ZERO;
        muatBrg(d);
        muatNota(d);
        hitungLaba(d);
    }

    public Date getTgl() {
        return tgl;
    }

    public BigDecimal getJual() {
        return jual;
    }

    public BigDecimal getBeli() {
        return beli;
    }

    private void muatBrg(Db d) throws SQLException {
        brg = new java.util.LinkedList<>();
        java.sql.PreparedStatement p = d.prep("select distinct brg from item_ju where nota in("
                + "select nota from jual where tgl=? and not hapus)");
        p.setDate(1, tgl);
        java.sql.ResultSet r = p.executeQuery();
        while (r.next()) brg.add(r.getString("brg"));
        r.close();
        p.close();
    }

    private void muatNota(Db d) throws SQLException {
        nota = new java.util.LinkedList<>();
        java.sql.PreparedStatement p = d.prep("select nota from jual where tgl=? and hapus");
        p.setDate(1, tgl);
        java.sql.ResultSet r = p.executeQuery();
        while (r.next()) nota.add(r.getString("nota"));
        r.close();
        p.close();
    }

    private void hitungLaba(Db d) throws SQLException {
        for (String b:brg) for (String n:nota) {
            java.sql.PreparedStatement p = d.prep("select jum from item_ju where nota=? and brg=?");
            p.setString(1, n);
            p.setString(2, b);
            java.sql.ResultSet r = p.executeQuery();
            if (r.next()) tambah(d, r.getBigDecimal("jum"), b);
            r.close();
            p.close();
        }
    }

    private void tambah(Db d, BigDecimal b, String brg) throws SQLException {
        java.sql.PreparedStatement p = d.prep("select beli::numeric::float8,jual::numeric::float8 "
                + "from barang where kode=?");
        p.setString(1, brg);
        java.sql.ResultSet r = p.executeQuery();
        if (r.next()) {
            jual = jual.add(b.multiply(r.getBigDecimal("jual")));
            beli = beli.add(b.multiply(r.getBigDecimal("beli")));
        } r.close();
        p.close();
    }
}
