/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto.tools;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.JTable;
import jtoko.anto.Db;

/**
 *
 * @author ashura
 */
public class Loader {

    public static void muatBarang(Db d, JTable tbl, String src, int order, boolean desc) throws SQLException {
        var m = new javax.swing.table.DefaultTableModel(new String[]{"Kode", "Nama", "Harga", "Stok", "Ket"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; tbl.setModel(m);
        String sql = genSqlBrg(src, order, desc);
        java.sql.PreparedStatement p = d.prep(sql);
        if (!"".equals(src)) p.setString(1, "%" + src + '%');
        java.sql.ResultSet r = p.executeQuery();
        while (r.next()) m.addRow(new String[] {r.getString("kode"), r.getString("nm"), r.getString("jual"), r.getString("stok") + ' ' +
                r.getString("sat"), r.getString("ket")});
        r.close();
        p.close();
    }

    private static String genSqlBrg(String src, int order, boolean desc) {
        String sql = "select kode,nm,jual,stok,sat,ket from barang where not hapus";
        if (!"".equals(src)) sql += " and nm like ?";
        sql += " order by";
        switch (order) {
            case 0:
                sql += " kode";
                break;
            case 1:
                sql += " nm";
                break;
            case 2:
                sql += " jual";
                break;
            case 3:
                sql += " stok";
                break;
            case 4:
                sql += " ket";
                break;
            default:
                break;
        } if (desc) sql += " desc";
        return sql;
    }

    public static void muatTglJual(Db d, JComboBox<Date> tglJu) throws SQLException {
        for (int x = tglJu.getItemCount() - 1; x >= 0; x--) tglJu.removeItemAt(x);
        Date t = Date.valueOf(LocalDate.now());
        tglJu.addItem(t);
        java.sql.PreparedStatement p = d.prep("select distinct tgl from jual where tgl<>? order by tgl desc");
        p.setDate(1, t);
        java.sql.ResultSet r = p.executeQuery();
        while (r.next()) tglJu.addItem(r.getDate("tgl"));
        r.close();
        p.close();
        tglJu.setSelectedIndex(0);
    }

    public static void muatJual(Db d, JTable tbl, String src, int order, boolean desc, Date t) throws SQLException {
        var m = new javax.swing.table.DefaultTableModel(new String[]{"Nota", "Jam", "Pelanggan", "Total", "Ket"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; tbl.setModel(m);
        String sql = genSqlJual(src, order, desc);
        java.sql.PreparedStatement p = d.prep(sql);
        p.setDate(1, t);
        if (!"".equals(src)) p.setString(2, "%" + src + '%');
        java.sql.ResultSet r = p.executeQuery();
        while (r.next()) m.addRow(new String[]{r.getString("nota"), r.getString("jam"), getPelName(d, r.getString("pel")),
        r.getString("tot"), r.getString("ket")});
        r.close();
        p.close();
    }

    private static String genSqlJual(String src, int order, boolean desc) {
        String sql = "select nota,jam,pel,tot,ket from jual where not hapus and tgl=?";
        if (!"".equals(src)) sql += " and nota like ?";
        sql += " order by";
        switch (order) {
            case 0:
                sql += " nota";
                break;
            case 1:
                sql += " jam";
                break;
            case 2:
                sql += " pel";
                break;
            case 3:
                sql += " tot";
                break;
            case 4:
                sql += " ket";
                break;
            default:
                break;
        } if (desc) sql += " desc";
        return sql;
    }

    private static String getPelName(Db d, String kode) throws SQLException {
        String s = "";
        java.sql.PreparedStatement p = d.prep("select nama from pelanggan where kode=?");
        p.setString(1, kode);
        java.sql.ResultSet r = p.executeQuery();
        if (r.next()) s = r.getString("nama");
        r.close();
        p.close();
        return s;
    }

    public static void muatPelanggan(Db d, JTable tbl, String src, int order, boolean desc) throws SQLException {
        var m = new javax.swing.table.DefaultTableModel(new String[]{"Kode", "NIK", "Nama", "Alamat", "Telp"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; tbl.setModel(m);
        String sql = genSqlPelanggan(src, order, desc);
        java.sql.PreparedStatement p = d.prep(sql);
        if (!"".equals(src)) p.setString(1, "%" + src + '%');
        java.sql.ResultSet r = p.executeQuery();
        while (r.next()) m.addRow(new String[]{r.getString("kode"), r.getString("nik"), r.getString("nama"), r.getString("almt"),
        r.getString("tlp")});
        r.close();
        p.close();
    }

    private static String genSqlPelanggan(String src, int order, boolean desc) {
        String sql = "select kode,nik,nama,almt,tlp from pelanggan where not hapus";
        if (!"".equals(src)) sql += " and nama like ?";
        sql += " order by";
        switch (order) {
            case 0:
                sql += " kode";
                break;
            case 1:
                sql += " nik";
                break;
            case 2:
                sql += " nama";
                break;
            case 3:
                sql += " almt";
                break;
            case 4:
                sql += " tlp";
                break;
            default:
                break;
        } if (desc) sql += " desc";
        return sql;
    }

    public static void muatSuplier(Db d, JTable tbl, String src, int order, boolean desc) throws SQLException {
        var m = new javax.swing.table.DefaultTableModel(new String[]{"Kode", "Nama", "Alamat", "Telp"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; tbl.setModel(m);
        String sql = genSqlSup(src, order, desc);
        java.sql.PreparedStatement p = d.prep(sql);
        if (!"".equals(src)) p.setString(1, "%" + src + '%');
        java.sql.ResultSet r = p.executeQuery();
        while (r.next()) m.addRow(new String[]{r.getString("kode"), r.getString("nama"), r.getString("almt"), r.getString("tlp")});
        r.close();
        p.close();
    }

    private static String genSqlSup(String src, int order, boolean desc) {
        String sql = "select kode,nama,almt,tlp from suplier where not hapus";
        if (!"".equals(src)) sql += " and nama like ?";
        sql += " order by";
        switch (order) {
            case 0:
                sql += " kode";
                break;
            case 1:
                sql += " nama";
                break;
            case 2:
                sql += " almt";
                break;
            case 3:
                sql += " tlp";
                break;
            default:
                break;
        } if (desc) sql += " desc";
        return sql;
    }

    public static void muatTglPasok(Db d, JComboBox<Date> tglPas) throws SQLException {
        for (int x = tglPas.getItemCount() - 1; x >= 0; x--) tglPas.removeItemAt(x);
        Date t = Date.valueOf(LocalDate.now());
        tglPas.addItem(t);
        java.sql.PreparedStatement p = d.prep("select distinct tgl from pasok where not hapus and tgl<>? order by tgl desc");
        p.setDate(1, t);
        java.sql.ResultSet r = p.executeQuery();
        while (r.next()) tglPas.addItem(r.getDate("tgl"));
        r.close();
        p.close();
        tglPas.setSelectedIndex(0);
    }

    public static void muatPasok(Db d, JTable tbl, String src, int order, boolean desc, Date tgl) throws SQLException {
        var m = new javax.swing.table.DefaultTableModel(new String[]{"Kode", "Jam", "Pemasok", "Total", "Ket"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; tbl.setModel(m);
        String sql = genSqlPasok(src, order, desc);
        java.sql.PreparedStatement p = d.prep(sql);
        p.setDate(1, tgl);
        if (!"".equals(src)) p.setString(2, "%" + src + '%');
        java.sql.ResultSet r = p.executeQuery();
        while (r.next()) m.addRow(new String[] {r.getString("kode"), r.getString("jam"), sqlNamaSup(d, r.getString("sup")),
        r.getString("tot"), r.getString("ket")});
        r.close();
        p.close();
    }

    private static String genSqlPasok(String src, int order, boolean desc) {
        String sql = "select kode,jam,sup,tot,ket from pasok where not hapus and tgl=?";
        if (!"".equals(src)) sql += " and kode like ?";
        sql += " order by";
        switch (order) {
            case 0:
                sql += " kode";
                break;
            case 1:
                sql += " jam";
                break;
            case 2:
                sql += " sup";
                break;
            case 3:
                sql += " tot";
                break;
            case 4:
                sql += " ket";
                break;
            default:
                break;
        } if (desc) sql += " desc";
        return sql;
    }

    private static String sqlNamaSup(Db d, String sup) throws SQLException {
        String s = "";
        java.sql.PreparedStatement p = d.prep("select nama from suplier where kode=? and not hapus");
        p.setString(1, sup);
        java.sql.ResultSet r = p.executeQuery();
        if (r.next()) s = r.getString("nama");
        r.close();
        p.close();
        return s;
    }

    public static double hrgBrg(Db d, String sBrg) throws SQLException {
        double h = 0;
        var p = d.prep("select jual::numeric::float from barang where kode=? and not hapus");
        p.setString(1, sBrg);
        var r = p.executeQuery();
        if (r.next()) h = r.getDouble("jual");
        r.close();
        p.close();
        return h;
    }

}
