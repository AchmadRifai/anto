/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ashura
 */
public class DbLocal {
    private final java.sql.Connection c;
    private final java.sql.Statement s;

    public DbLocal() throws SQLException {
        try {
            org.sqlite.JDBC.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Conf.hindar(ex);
        } c = DriverManager.getConnection("jdbc:sqlite:" + Conf.f.getAbsolutePath());
        s = c.createStatement();
    }

    public java.sql.PreparedStatement prep(String sql) throws SQLException {
        return c.prepareStatement(sql);
    }

    public java.sql.ResultSet hasil(String sql) throws SQLException {
        return s.executeQuery(sql);
    }

    public void aksi(String sql) throws SQLException {
        s.execute(sql);
    }

    public void tutup() throws SQLException {
        s.close();
        c.close();
    }
}
