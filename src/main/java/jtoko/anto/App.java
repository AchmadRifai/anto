/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import jtoko.anto.tools.Analizer;
import org.springframework.boot.builder.SpringApplicationBuilder;


/**
 *
 * @author ashura
 */
@org.springframework.boot.autoconfigure.SpringBootApplication
public class App {
    public static void main(String[]a) {
        var ctx = new SpringApplicationBuilder(Dash.class).headless(false).run(a);
        EventQueue.invokeLater(()->{
            ctx.getBean(Dash.class).setVisible(true);
        });
//            Db d = new Db();
//            for (jtoko.anto.beans.BrgJual b:Analizer.brgTerjualBln(d)) {
//                System.out.println("A : " + b.getNama());
//                System.out.println("B : " + b.getQty() + "\n");
//            }
//            d.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }
}
