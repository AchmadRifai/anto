/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto.tools;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
    
}
