/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto.tools;

import java.sql.Date;
import java.sql.SQLException;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import jtoko.anto.Db;

/**
 *
 * @author ashura
 */
public class Deleter {

    public static void magicBrg(Db d, String kode) throws SQLException {
        java.sql.PreparedStatement p = d.prep("update barang set hapus=? where kode=?");
        p.setBoolean(1, true);
        p.setString(2, kode);
        p.execute();
        p.close();
    }

    public static boolean isLongValid(JFormattedTextField f) {
        boolean b = false; try {
            long l = Long.parseLong(f.getText());
            System.out.println(l);
            b = true;
        } catch (NumberFormatException e) {
            Db.hindar(e);
        } return b;
    }

    public static boolean isDoubleValid(JFormattedTextField f) {
        boolean b = false; try {
            double d = Double.parseDouble(f.getText());
            System.out.println(d);
            b = true;
        } catch (NumberFormatException e) {
            Db.hindar(e);
        } return b;
    }

    public static boolean isDateValid(JFormattedTextField f) {
        boolean b = false; try {
            java.sql.Date d = Date.valueOf(f.getText());
            System.out.println(d);
            b = true;
        } catch (java.time.format.DateTimeParseException e) {
            Db.hindar(e);
        } return b;
    }

    public static void magicJual(Db d, String sJu) throws SQLException {
        java.sql.PreparedStatement p = d.prep("update jual set hapus=? where nota=?");
        p.setBoolean(1, true);
        p.setString(2, sJu);
        p.execute();
        p.close();
    }

    public static boolean isDateValid(JTextField f) {
        boolean b = false; try {
            java.sql.Date d = Date.valueOf(f.getText());
            System.out.println(d);
            b = true;
        } catch (java.lang.IllegalArgumentException e) {
            Db.hindar(e);
        } return b;
    }

    public static void magicPel(Db d, String sPel) throws SQLException {
        var p = d.prep("update pelanggan set hapus=? where kode=?");
        p.setBoolean(1, true);
        p.setString(2, sPel);
        p.execute();
        p.close();
    }

    public static void magicSup(Db d, String sSup) throws SQLException {
        var p = d.prep("update suplier set hapus=? where kode=?");
        p.setBoolean(1, true);
        p.setString(2, sSup);
        p.execute();
        p.close();
    }

    public static void magicPas(Db d, String Spas) throws SQLException {
        var p = d.prep("update pasok set hapus=? where kode=?");
        p.setBoolean(1, true);
        p.setString(2, Spas);
        p.execute();
        p.close();
    }

    static void physikBrg(Db d, String sBrg) throws SQLException {
        var p = d.prep("update barang set hapus=? where kode=?");
        p.setBoolean(1, false);
        p.setString(2, sBrg);
        p.execute();
        p.close();
    }

    static void trueBrg(Db d, String sBrg) throws SQLException {
        backupJual(d, sBrg);
        var p1 = d.prep("delete from item_ju where brg=?");
        p1.setString(1, sBrg);
        p1.execute();
        p1.close();
        backupPskBrg(d, sBrg);
        var p2 = d.prep("delete from item_psk where brg=?");
        p2.setString(1, sBrg);
        p2.execute();
        p2.close();
        var p3 = d.prep("delete from barang where kode=?");
        p3.setString(1, sBrg);
        p3.execute();
        p3.close();
    }

    private static void backupJual(Db d, String sBrg) throws SQLException {
        var p1 = d.prep("select nota,(sat*jum)::numeric::float8 as a from item_ju where brg=?");
        p1.setString(1, sBrg);
        var r = p1.executeQuery();
        while (r.next()) {
            String nota = r.getString("nota");
            long sub = r.getLong("a");
            var p = d.prep("update jual set tot=tot-?,kbl=kbl+? where nota=?");
            p.setLong(1, sub);
            p.setLong(2, sub);
            p.setString(3, nota);
            p.execute();
            p.close();
        } r.close();
        p1.close();
    }

    private static void backupPskBrg(Db d, String sBrg) throws SQLException {
        var p1 = d.prep("select pas,(sat*jum)::numeric::float8 as a from item_psk where brg=?");
        p1.setString(1, sBrg);
        var r = p1.executeQuery();
        while (r.next()) {
            String pas = r.getString("pas");
            long sub = r.getLong("a");
            var p = d.prep("update pasok set tot=tot-? where kode=?");
            p.setLong(1, sub);
            p.setString(2, pas);
            p.execute();
            p.close();
        } r.close();
        p1.close();
    }

    static void physikPel(Db d, String sPel) throws SQLException {
        var p = d.prep("update pelanggan set hapus=? where kode=?");
        p.setBoolean(1, false);
        p.setString(2, sPel);
        p.execute();
        p.close();
    }

    static void physikJu(Db d, String sJu) throws SQLException {
        var p = d.prep("update jual set hapus=? where nota=?");
        p.setBoolean(1, false);
        p.setString(2, sJu);
        p.execute();
        p.close();
    }

    static void trueJual(Db d, String sJu) throws SQLException {
        backupBrgJual(d, sJu);
        var p = d.prep("delete from jual where nota=?");
        p.setString(1, sJu);
        p.execute();
        p.close();
    }

    private static void backupBrgJual(Db d, String sJu) throws SQLException {
        var p1 = d.prep("select brg,jum from item_ju where nota=?");
        p1.setString(1, sJu);
        var r = p1.executeQuery();
        while (r.next()) {
            var p = d.prep("update barang set stok=stok+? where kode=?");
            p.setDouble(1, r.getDouble("jum"));
            p.setString(2, r.getString("brg"));
            p.execute();
            p.close();
        } r.close();
        p1.close();
        var p = d.prep("delete from item_ju where nota=?");
        p.setString(1, sJu);
        p.execute();
        p.close();
    }

    static void truePel(Db d, String sPel) throws SQLException {
        var p1 = d.prep("select nota from jual where pel=?");
        p1.setString(1, sPel);
        var r = p1.executeQuery();
        while (r.next()) 
            trueJual(d, r.getString("nota"));
        r.close();
        p1.close();
        var p = d.prep("delete from pelanggan where kode=?");
        p.setString(1, sPel);
        p.execute();
        p.close();
    }

    static void physikPas(Db d, String sPas) throws SQLException {
        var p = d.prep("update pasok set hapus=? where kode=?");
        p.setBoolean(1, false);
        p.setString(2, sPas);
        p.execute();
        p.close();
    }

    static void truePas(Db d, String sPas) throws SQLException {
        backupBrgPas(d, sPas);
        var p = d.prep("delete from pasok where kode=?");
        p.setString(1, sPas);
        p.execute();
        p.close();
    }

    private static void backupBrgPas(Db d, String sPas) throws SQLException {
        var p1 = d.prep("select brg,jum from item_psk where pas=?");
        p1.setString(1, sPas);
        var r = p1.executeQuery();
        while (r.next()) {
            var p = d.prep("update barang set stok=stok-? where kode=?");
            p.setDouble(1, r.getDouble("jum"));
            p.setString(2, r.getString("brg"));
            p.execute();
            p.close();
        } r.close();
        p1.close();
        var p = d.prep("delete from item_psk where pas=?");
        p.setString(1, sPas);
        p.execute();
        p.close();
    }

    static void physikSup(Db d, String sSup) throws SQLException {
        var p = d.prep("update suplier set hapus=? where kode=?");
        p.setBoolean(1, false);
        p.setString(2, sSup);
        p.execute();
        p.close();
    }

    static void trueSup(Db d, String sSup) throws SQLException {
        var p1 = d.prep("select kode from pasok where sup=?");
        p1.setString(1, sSup);
        var r = p1.executeQuery();
        while (r.next()) truePas(d, r.getString("kode"));
        r.close();
        p1.close();
        var p = d.prep("delete from suplier where kode=?");
        p.setString(1, sSup);
        p.execute();
        p.close();
    }
    
}
