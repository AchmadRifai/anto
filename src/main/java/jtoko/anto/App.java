/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.builder.SpringApplicationBuilder;


/**
 *
 * @author ashura
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
public class App {
    public static void main(String[]a) {
        //var ctx = new SpringApplicationBuilder(Dash.class).headless(false).run(a);
        //EventQueue.invokeLater(()->{
        //    ctx.getBean(Dash.class).setVisible(true);
        //});
        testUang();
    }

    private static void testUang() {
        try {
            Db d = new Db();
            var p = d.prep("select sum(jual) as a from barang where hapus=?");
            p.setBoolean(1, false);
            var r = p.executeQuery();
            if (r.next()) {
                jtoko.anto.tools.Uang u = new jtoko.anto.tools.Uang(r.getString("a"));
                System.out.println(u.toLong());
                System.out.println(u.toString());
            } r.close();
            p.close();
            d.close();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
