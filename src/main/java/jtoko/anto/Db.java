/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ashura
 */
public class Db {

    public static void hindar(Exception ex) {
        Time t = Time.valueOf(LocalTime.now());
        Date d = Date.valueOf(LocalDate.now());
        java.io.File f = new java.io.File(System.getProperty("user.home") + "/.anto/error/" + d + '/' + t.getHours() + 'a' + t.getMinutes() +
                'a' + t.getSeconds() + ".log");
        if (!f.getParentFile().exists()) f.getParentFile().mkdirs();
        if (f.exists()) f.delete(); try (java.io.PrintWriter w = new java.io.PrintWriter(f)) {
            ex.printStackTrace(w);
        } catch (FileNotFoundException ex1) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }

    private Connection c;
    private java.sql.Statement s;

    public Db() throws SQLException {
        try {
            org.postgresql.Driver.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Db.hindar(ex);
        } c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/anto", "postgres", "root");
        s = c.createStatement();
    }

    public java.sql.PreparedStatement prep(String sql) throws SQLException {
        return c.prepareStatement(sql);
    }

    public java.sql.ResultSet hasil(String sql) throws SQLException {
        return s.executeQuery(sql);
    }

    public void exec(String sql) throws SQLException {
        s.execute(sql);
    }

    public Connection getC() {
        return c;
    }

    public void close() throws SQLException {
        s.close();
        c.close();
    }
}
