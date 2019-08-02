/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto.beans;

import java.math.BigDecimal;
import java.sql.SQLException;
import jtoko.anto.Db;

/**
 *
 * @author ashura
 */
public class BrgJual {
    private String kode, nama;
    private BigDecimal qty;

    public BrgJual(String kode, Db d, java.sql.Date awal, java.sql.Date akhir) throws SQLException {
        this.kode = kode;
        namane(d);
        if (nama != null) jumlahe(d, awal, akhir);
    }

    private void namane(Db d) throws SQLException {
        java.sql.PreparedStatement p = d.prep("select nm from barang where kode=?");
        p.setString(1, kode);
        java.sql.ResultSet r = p.executeQuery();
        if (r.next()) nama = r.getString("nm");
        else nama = null;
        r.close();
        p.close();
    }

    private void jumlahe(Db d, java.sql.Date awal, java.sql.Date akhir) throws SQLException {
        java.sql.PreparedStatement p = d.prep("select sum(jum)as akeh from item_ju where brg=? and nota in(select nota from jual where "
                + "tgl between ? and ? and not hapus)");
        p.setString(1, kode);
        p.setDate(2, awal);
        p.setDate(3, akhir);
        java.sql.ResultSet r = p.executeQuery();
        if (r.next()) qty = r.getBigDecimal("akeh");
        else qty = null;
        r.close();
        p.close();
    }

    public String getNama() {
        return nama;
    }

    public BigDecimal getQty() {
        return qty;
    }
}
