/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto.tools;

import java.awt.Frame;
import java.awt.event.WindowEvent;

/**
 *
 * @author ashura
 */
public abstract class Laporan extends javax.swing.JDialog {
    public abstract void reload();
    private net.sf.jasperreports.view.JasperViewer v;

    public Laporan(Frame owner, boolean modal, net.sf.jasperreports.engine.JasperPrint isi) {
        super(owner, modal);
        v = new net.sf.jasperreports.view.JasperViewer(isi);
        inisial();
    }

    private void inisial() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Laporan");
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                akhir();
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));
        pack();
        setLocationRelativeTo(null);
        setContentPane(v.getContentPane());
        setSize(v.getPreferredSize());
    }

    private void akhir() {
        new Thread(this::reload).start();
    }
}
