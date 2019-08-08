/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto.tools;

import java.text.ParseException;

/**
 *
 * @author ashura
 */
public class Uang {

    private long jum;

    private Uang() {}

    public Uang div(Uang i) {
        var u = new Uang();
        if (i.jum != 0) u.jum = jum / i.jum;
        else u.jum = 0;
        return u;
    }

    public Uang div(long i) {
        var u = new Uang();
        if (i != 0) u.jum = jum / i;
        else u.jum = 0;
        return u;
    }

    public Uang div(int i) {
        var u = new Uang();
        if (i != 0) u.jum = jum / i;
        else u.jum = 0;
        return u;
    }

    public Uang mul(Uang v) {
        var u =  new Uang();
        u.jum = jum * v.jum;
        return u;
    }

    public Uang mul(long l) {
        var u = new Uang();
        u.jum = jum * l;
        return u;
    }

    public Uang mul(int i) {
        var u = new Uang();
        u.jum = jum * i;
        return u;
    }

    public Uang min(int i) {
        var u = new Uang();
        u.jum = jum - i;
        return u;
    }

    public Uang min(long l) {
        var u = new Uang();
        u.jum = jum - l;
        return u;
    }

    public Uang min(Uang v) {
        var u = new Uang();
        u.jum = jum - v.jum;
        return u;
    }

    public Uang add(int i) {
        var u = new Uang();
        u.jum = jum + i;
        return u;
    }

    public Uang add(long l) {
        var u = new Uang();
        u.jum = jum + l;
        return u;
    }

    public Uang add(Uang v) {
        var u = new Uang();
        u.jum = jum + v.jum;
        return u;
    }

    public Uang(String s) throws ParseException {
        var df = Uang.rupiah();
        jum = (long) df.parse(s);
    }

    public long toLong() {
        return jum;
    }

    public Uang(long jum) {
        this.jum = jum;
    }

    @Override
    public String toString() {
        java.text.NumberFormat df = Uang.rupiah();
        String s = df.format(jum);
        return s.substring(0, s.length() - 3);
    }

    public static java.text.NumberFormat rupiah() {
        var df = (java.text.DecimalFormat) java.text.DecimalFormat.getCurrencyInstance();
        var dfs = new java.text.DecimalFormatSymbols();
        dfs.setCurrencySymbol("Rp");
        dfs.setMonetaryDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        return df;
    }

    public Uang mul(double jum) {
        Uang u = new Uang();
        u.jum = (long) (this.jum * jum);
        return u;
    }
}
