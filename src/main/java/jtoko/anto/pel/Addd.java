/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto.pel;

import java.awt.Color;
import java.sql.SQLException;
import jtoko.anto.Db;

/**
 *
 * @author ashura
 */
public abstract class Addd extends javax.swing.JDialog {

    public abstract void reload();
    /**
     * Creates new form Addd
     */
    public Addd(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        kode = new javax.swing.JTextField();
        nik = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        almt = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        telp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        s = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tambah Pelanggan Baru");
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Masukan Kode");

        jLabel2.setText("Masukan NIK");

        jLabel3.setText("Masukan Alamat");

        kode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kodeKeyReleased(evt);
            }
        });

        nik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nikKeyReleased(evt);
            }
        });

        almt.setColumns(20);
        almt.setRows(5);
        almt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                almtKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(almt);

        jLabel4.setText("Masukan Nama");

        nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                namaKeyReleased(evt);
            }
        });

        telp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telpKeyReleased(evt);
            }
        });

        jLabel5.setText("Masukan Telp");

        s.setText("Simpan");
        s.setEnabled(false);
        s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(nik)
                            .addComponent(kode)
                            .addComponent(nama)
                            .addComponent(telp))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(s)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        new Thread(this::reload).start();
    }//GEN-LAST:event_formWindowClosing

    private void kodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodeKeyReleased
        if (!"".equals(kode.getText())) try {
            Db d = new Db();
            var p = d.prep("select kode from pelanggan where kode=?");
            p.setString(1, kode.getText());
            var r = p.executeQuery();
            if (r.next()) kode.setForeground(Color.red);
            else kode.setForeground(Color.BLACK);
            r.close();
            p.close();
            d.close();
        } catch (SQLException ex) {
            Db.hindar(ex);
        } refresh();
    }//GEN-LAST:event_kodeKeyReleased

    private void nikKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nikKeyReleased
        refresh();
    }//GEN-LAST:event_nikKeyReleased

    private void namaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaKeyReleased
        refresh();
    }//GEN-LAST:event_namaKeyReleased

    private void almtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_almtKeyReleased
        refresh();
    }//GEN-LAST:event_almtKeyReleased

    private void telpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telpKeyReleased
        refresh();
    }//GEN-LAST:event_telpKeyReleased

    private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        s.setEnabled(false);
        new Thread(this::simpan).start();
        kode.setEnabled(false);
        nik.setEnabled(false);
        nama.setEnabled(false);
        almt.setEnabled(false);
        telp.setEnabled(false);
    }//GEN-LAST:event_sActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea almt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kode;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nik;
    private javax.swing.JButton s;
    private javax.swing.JTextField telp;
    // End of variables declaration//GEN-END:variables

    private void refresh() {
        s.setEnabled(!"".equals(kode.getText()) && Color.BLACK == kode.getForeground() && !"".equals(nik.getText()) &&
                !"".equals(nama.getText()) && !"".equals(almt.getText()) && !"".equals(telp.getText()));
    }

    private void simpan() {
        try {
            Db d = new Db();
            var p = d.prep("insert into pelanggan values(?,?,?,?,?,?)");
            p.setString(1, kode.getText());
            p.setString(2, nik.getText());
            p.setString(3, nama.getText());
            p.setString(4, almt.getText());
            p.setString(5, telp.getText());
            p.setBoolean(6, false);
            p.execute();
            p.close();
            d.close();
            setVisible(false);
            reload();
        } catch (SQLException ex) {
            purify();
            Db.hindar(ex);
        }
    }

    private void purify() {
        kode.setEnabled(true);
        nik.setEnabled(true);
        nama.setEnabled(true);
        almt.setEnabled(true);
        telp.setEnabled(true);
        refresh();
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }
}
