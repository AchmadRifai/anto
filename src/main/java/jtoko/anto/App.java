/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author ashura
 */
public class App {
    public static void main(String[]a) {
        for (UIManager.LookAndFeelInfo i : UIManager.getInstalledLookAndFeels()) if ("Metal".equals(i.getName())) try {
            UIManager.setLookAndFeel(i.getClassName());
            break;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Db.hindar(ex);
        } EventQueue.invokeLater(()->{
            new Dash().setVisible(true);
        });
    }
}
