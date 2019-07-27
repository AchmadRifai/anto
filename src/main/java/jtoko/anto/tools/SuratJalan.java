/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jtoko.anto.tools;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import jtoko.anto.Dash;

/**
 *
 * @author ashura
 */
public class SuratJalan {
    public static java.io.File f = new java.io.File(System.getProperty("user.home") + "/.antok/report/jual.jrxml");

    public static void kopi(Class<? extends Dash> c) throws IOException {
        gambar(c);
        var o = new PrintStream(f);
        o.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        inisial(o);
        group(o);
        header(o);
        detail(o);
        footer(o);
        sumary1(o);
        o.close();
    }

    private static void gambar(Class<? extends Dash> c) throws IOException {
        var f = new java.io.File(System.getProperty("user.home") + "/.antok/report/coffee.jpg");
        if (!f.getParentFile().exists()) f.getParentFile().mkdirs();
        if (f.exists()) f.delete();
        Files.copy(c.getResourceAsStream("/coffee.jpg"), f.toPath(), new CopyOption[]{});
    }

    private static void inisial(PrintStream o) {
        o.print("<jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
        o.print("xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" ");
        o.print("name=\"jual\" pageWidth=\"421\" pageHeight=\"300\" orientation=\"Landscape\" columnWidth=\"381\" leftMargin=\"20\" rightMargin=\"20\"");
        o.print(" topMargin=\"20\" bottomMargin=\"20\" uuid=\"df013db5-f76e-44d3-b0df-bcbc46d93160\">");
        o.print("<property name=\"com.jaspersoft.studio.data.sql.tables\" value=\"\"/>");
        o.print("<property name=\"com.jaspersoft.studio.data.defaultdataadapter\" value=\"antok\"/>");
        o.print("<style name=\"Title\" fontName=\"Times New Roman\" fontSize=\"24\" isBold=\"true\"/>");
        o.print("<style name=\"SubTitle\" forecolor=\"#736343\" fontName=\"Arial\" fontSize=\"8\"/>");
        o.print("<style name=\"Column header\" forecolor=\"#666666\" fontName=\"Arial\" fontSize=\"12\" isBold=\"true\"/>");
        o.print("<style name=\"Detail\" fontName=\"Arial\" fontSize=\"12\"/>");
        o.print("<style name=\"Row\" mode=\"Transparent\"><conditionalStyle>");
        o.print("<conditionExpression><![CDATA[$V{REPORT_COUNT}%2 == 0]]></conditionExpression><style backcolor=\"#E6DAC3\"/>");
        o.print("</conditionalStyle></style><parameter name=\"nota\" class=\"java.lang.String\"/><queryString language=\"SQL\">");
        o.print("<![CDATA[SELECT pelanggan.nama,jual.nota,jual.tgl,jual.jam,jual.tot,jual.byr,jual.kbl,item_ju.jum,item_ju.sat,item_ju.tot,");
        o.print("barang.nm FROM item_ju INNER JOIN jual ON item_ju.nota = jual.nota INNER JOIN pelanggan ON jual.pel = pelanggan.kode ");
        o.print("INNER JOIN barang ON item_ju.brg = barang.kode WHERE jual.nota = $P{nota}]]></queryString>");
        field(o);
    }

    private static void field(PrintStream o) {
        o.print("<field name=\"nama\" class=\"java.lang.String\"><property name=\"com.jaspersoft.studio.field.label\" value=\"nama\"/>");
        o.print("<property name=\"com.jaspersoft.studio.field.tree.path\" value=\"pelanggan\"/></field>");
        o.print("<field name=\"nota\" class=\"java.lang.String\"><property name=\"com.jaspersoft.studio.field.label\" value=\"nota\"/>");
        o.print("<property name=\"com.jaspersoft.studio.field.tree.path\" value=\"jual\"/></field>");
        o.print("<field name=\"tgl\" class=\"java.sql.Date\"><property name=\"com.jaspersoft.studio.field.label\" value=\"tgl\"/>");
        o.print("<property name=\"com.jaspersoft.studio.field.tree.path\" value=\"jual\"/></field>");
        o.print("<field name=\"jam\" class=\"java.sql.Time\"><property name=\"com.jaspersoft.studio.field.label\" value=\"jam\"/>");
        o.print("<property name=\"com.jaspersoft.studio.field.tree.path\" value=\"jual\"/></field>");
        o.print("<field name=\"tot\" class=\"java.lang.String\"><property name=\"com.jaspersoft.studio.field.label\" value=\"tot\"/>");
        o.print("<property name=\"com.jaspersoft.studio.field.tree.path\" value=\"jual\"/></field>");
        o.print("<field name=\"byr\" class=\"java.lang.String\"><property name=\"com.jaspersoft.studio.field.label\" value=\"byr\"/>");
        o.print("<property name=\"com.jaspersoft.studio.field.tree.path\" value=\"jual\"/></field>");
        o.print("<field name=\"kbl\" class=\"java.lang.String\"><property name=\"com.jaspersoft.studio.field.label\" value=\"kbl\"/>");
        o.print("<property name=\"com.jaspersoft.studio.field.tree.path\" value=\"jual\"/></field>");
        o.print("<field name=\"jum\" class=\"java.lang.Double\"><property name=\"com.jaspersoft.studio.field.label\" value=\"jum\"/>");
        o.print("<property name=\"com.jaspersoft.studio.field.tree.path\" value=\"item_ju\"/></field>");
        o.print("<field name=\"sat\" class=\"java.lang.String\"><property name=\"com.jaspersoft.studio.field.label\" value=\"sat\"/>");
        o.print("<property name=\"com.jaspersoft.studio.field.tree.path\" value=\"item_ju\"/></field>");
        o.print("<field name=\"COLUMN_10\" class=\"java.lang.String\"><property name=\"com.jaspersoft.studio.field.label\" value=\"tot\"/>");
        o.print("<property name=\"com.jaspersoft.studio.field.tree.path\" value=\"item_ju\"/></field>");
        o.print("<field name=\"nm\" class=\"java.lang.String\"><property name=\"com.jaspersoft.studio.field.label\" value=\"nm\"/>");
        o.print("<property name=\"com.jaspersoft.studio.field.tree.path\" value=\"barang\"/></field>");
    }

    private static void group(PrintStream o) {
        o.print("<group name=\"Group1\"><groupExpression><![CDATA[$F{nota}]]></groupExpression><groupHeader><band height=\"37\"><frame>");
        o.print("<reportElement mode=\"Opaque\" x=\"-21\" y=\"7\" width=\"422\" height=\"24\" forecolor=\"#B89F7D\" backcolor=\"#F2EBDF\" ");
        o.print("uuid=\"ff649bda-bbd1-4c2d-bd67-21f69ad64bbf\"/><rectangle><reportElement mode=\"Opaque\" x=\"21\" y=\"0\" width=\"36\" ");
        o.print("height=\"24\" backcolor=\"#736343\" uuid=\"a7ae31ce-9474-4e4b-9627-eb9152ff7b80\"/><graphicElement><pen lineWidth=\"0.0\"/>");
        o.print("</graphicElement></rectangle><textField><reportElement style=\"SubTitle\" x=\"68\" y=\"0\" width=\"354\" height=\"24\" ");
        o.print("forecolor=\"#736343\" uuid=\"b5a25815-f4ed-4382-9712-d113dc519d4e\"/><textElement><font isBold=\"true\"/></textElement>");
        o.print("<textFieldExpression><![CDATA[$F{nota}]]></textFieldExpression></textField></frame><staticText><reportElement style=\"Column ");
        o.print("header\" x=\"310\" y=\"-16\" width=\"91\" height=\"15\" forecolor=\"#736343\" uuid=\"e47cb257-73c1-45f3-b9cf-eaa5a50050d3\">");
        o.print("<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"4a20257c-2e68-43b5-aaeb-8179c5007b57\"/>");
        o.print("</reportElement><text><![CDATA[Sub Total]]></text></staticText></band></groupHeader><groupFooter><band height=\"6\"/>");
        o.print("</groupFooter></group><background><band splitType=\"Stretch\"/></background><title><band height=\"79\" splitType=\"Stretch\">");
        o.print("<property name=\"com.jaspersoft.studio.unit.height\" value=\"px\"/><image><reportElement x=\"0\" y=\"0\" width=\"70\" ");
        o.print("height=\"40\" uuid=\"1c003177-754c-448f-8ce1-16868856f545\"/><imageExpression><![CDATA[\"coffee.jpg\"]]></imageExpression>");
        o.print("</image><staticText><reportElement style=\"Title\" x=\"70\" y=\"0\" width=\"150\" height=\"30\" ");
        o.print("uuid=\"bc1ce1da-8232-46ea-be55-cec4abb986dd\"/><textElement verticalAlignment=\"Middle\"/><text><![CDATA[Toko Winda]]></text>");
        o.print("</staticText><staticText><reportElement style=\"SubTitle\" x=\"70\" y=\"30\" width=\"110\" height=\"10\" ");
        o.print("uuid=\"f6a78448-8260-4445-a9e0-e3fb53b080d9\"/><textElement><font fontName=\"Times New Roman\"/></textElement>");
        o.print("<text><![CDATA[Wire, Gedongombo]]></text></staticText><textField isStretchWithOverflow=\"true\"><reportElement style=\"Detail\" ");
        o.print("x=\"210\" y=\"28\" width=\"140\" height=\"15\" uuid=\"c2884ad4-30af-41aa-b767-2ba3451cb495\"><property ");
        o.print("name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"635772d3-1c22-4d0d-beb0-27b5fabafa8b\"/></reportElement>");
    }

    private static void header(PrintStream o) {
        o.print("<textFieldExpression><![CDATA[$F{nama}]]></textFieldExpression></textField><textField pattern=\"HH:mm:ss zzz\">");
        o.print("<reportElement style=\"Detail\" x=\"30\" y=\"45\" width=\"140\" height=\"21\" uuid=\"9136e1ae-338a-460b-951e-2dcb72bf6276\"/>");
        o.print("<textFieldExpression><![CDATA[$F{jam}]]></textFieldExpression></textField></band></title><pageHeader><band splitType=\"Stretch\"/>");
        o.print("</pageHeader><columnHeader><band height=\"16\" splitType=\"Stretch\"><staticText><reportElement style=\"Column header\" x=\"150\" ");
        o.print("y=\"0\" width=\"80\" height=\"15\" forecolor=\"#736343\" uuid=\"e374032f-f66f-4c85-89bc-c8d187cac021\"><property ");
        o.print("name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"fb8b687f-bf81-4f2b-8294-7ff0d42cc0c3\"/></reportElement>");
        o.print("<text><![CDATA[Qty]]></text></staticText><staticText><reportElement style=\"Column header\" x=\"230\" y=\"0\" width=\"80\" ");
        o.print("height=\"15\" forecolor=\"#736343\" uuid=\"c7b04db8-6a66-4e83-8584-e06854488bf7\"><property name=\"com.jaspersoft.studio.");
        o.print("spreadsheet.connectionID\" value=\"00383320-457e-499d-bda0-95c332b01a72\"/></reportElement><text><![CDATA[Harga Satuan]]></text>");
        o.print("</staticText><staticText><reportElement style=\"Column header\" x=\"1\" y=\"1\" width=\"149\" height=\"15\" forecolor=\"#736343\" ");
        o.print("uuid=\"8ae2d82b-37ad-4be6-8605-68104036c553\"><property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"b888ae57");
        o.print("-e3e5-4a9b-a97e-d464f4de1db8\"/></reportElement><text><![CDATA[Barang]]></text></staticText></band></columnHeader>");
    }

    private static void detail(PrintStream o) {
        o.print("<detail><band height=\"15\" splitType=\"Stretch\"><frame><reportElement style=\"Row\" mode=\"Opaque\" x=\"0\" y=\"0\" ");
        o.print("width=\"401\" height=\"15\" uuid=\"fa7cec56-4ec1-48e6-a26e-7266a995d174\"/><textField isStretchWithOverflow=\"true\">");
        o.print("<reportElement style=\"Detail\" x=\"150\" y=\"0\" width=\"80\" height=\"15\" uuid=\"b6a0ca62-5f41-465c-9f8c-f9fa11477205\">");
        o.print("<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"fb8b687f-bf81-4f2b-8294-7ff0d42cc0c3\"/></reportElement>");
        o.print("<textFieldExpression><![CDATA[$F{jum}]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\">");
        o.print("<reportElement style=\"Detail\" x=\"230\" y=\"0\" width=\"80\" height=\"15\" uuid=\"f3d4cfc5-7702-4667-a6b9-678e6c840b88\">");
        o.print("<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"00383320-457e-499d-bda0-95c332b01a72\"/>");
        o.print("</reportElement><textElement textAlignment=\"Right\"/><textFieldExpression><![CDATA[$F{sat}]]></textFieldExpression>");
        o.print("</textField><textField isStretchWithOverflow=\"true\"><reportElement style=\"Detail\" x=\"310\" y=\"0\" width=\"70\" height=\"15\" ");
        o.print("uuid=\"c26ce001-39a5-48a9-be04-ec569f66e102\"><property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"4a20257c-");
        o.print("2e68-43b5-aaeb-8179c5007b57\"/></reportElement><textElement textAlignment=\"Right\"/>");
        o.print("<textFieldExpression><![CDATA[$F{COLUMN_10}]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\">");
        o.print("<reportElement style=\"Detail\" x=\"1\" y=\"0\" width=\"149\" height=\"15\" uuid=\"1de9f7ab-6642-494d-8f2e-abdb83ca30f9\">");
        o.print("<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"b888ae57-e3e5-4a9b-a97e-d464f4de1db8\"/>");
        o.print("</reportElement><textFieldExpression><![CDATA[$F{nm}]]></textFieldExpression></textField></frame></band></detail>");
    }

    private static void footer(PrintStream o) {
        o.print("<pageFooter><band height=\"25\" splitType=\"Stretch\"><frame><reportElement mode=\"Opaque\" x=\"-21\" y=\"1\" width=\"422\" ");
        o.print("height=\"24\" forecolor=\"#D0B48E\" backcolor=\"#F2EBDF\" uuid=\"5d8169bd-4a75-48c8-8a68-6d3ad5ba9402\"/>");
        o.print("<textField evaluationTime=\"Report\"><reportElement style=\"Column header\" x=\"370\" y=\"-1\" width=\"40\" height=\"20\" ");
        o.print("forecolor=\"#736343\" uuid=\"e5e27efa-b599-499b-9ca3-848cb511cb7b\"/><textElement verticalAlignment=\"Middle\"><font size=\"10\" ");
        o.print("isBold=\"false\"/></textElement><textFieldExpression><![CDATA[\" \" + $V{PAGE_NUMBER}]]></textFieldExpression></textField>");
        o.print("<textField><reportElement style=\"Column header\" x=\"287\" y=\"-1\" width=\"80\" height=\"20\" forecolor=\"#736343\" ");
        o.print("uuid=\"18cfe1ca-f7d6-48b0-9827-28578b42a5e0\"/><textElement textAlignment=\"Right\" verticalAlignment=\"Middle\">");
        o.print("<font size=\"10\" isBold=\"false\"/></textElement><textFieldExpression><![CDATA[\"Page \"+$V{PAGE_NUMBER}+\" of\"]]>");
        o.print("</textFieldExpression></textField><textField pattern=\"EEEEE dd MMMMM yyyy\"><reportElement style=\"Column header\" x=\"22\" y=\"1\" ");
        o.print("width=\"197\" height=\"20\" forecolor=\"#736343\" uuid=\"fbce24bb-3cb1-44a3-8eec-8c067ddbe5b5\"/>");
        o.print("<textElement verticalAlignment=\"Middle\"><font size=\"10\" isBold=\"false\"/></textElement>");
        o.print("<textFieldExpression><![CDATA[$F{tgl}]]></textFieldExpression></textField></frame></band></pageFooter>");
    }

    private static void sumary1(PrintStream o) {
        o.print("<summary><band height=\"70\" splitType=\"Stretch\"><property name=\"com.jaspersoft.studio.unit.height\" value=\"px\"/><textField ");
        o.print("isStretchWithOverflow=\"true\"><reportElement style=\"Detail\" x=\"220\" y=\"0\" width=\"160\" height=\"15\" uuid=\"8d10a67c-");
        o.print("5492-465a-924c-30dc4671b1a4\"><property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"7ff05b59");
        o.print("-4174-4afe-ba16-5ded85d016fd\"/></reportElement><textElement textAlignment=\"Right\"/><textFieldExpression><![CDATA[$F{tot}]]>");
        o.print("</textFieldExpression></textField><textField isStretchWithOverflow=\"true\"><reportElement style=\"Detail\" x=\"220\" y=\"15\" ");
        o.print("width=\"160\" height=\"15\" uuid=\"f39e8744-eadd-4efd-ac52-0b032719c5e3\"><property name=\"com.jaspersoft.studio");
        o.print(".spreadsheet.connectionID\" value=\"676e76f7-2409-43c9-9612-b8525eb274fe\"/></reportElement><textElement textAlignment=\"Right\"/>");
        o.print("<textFieldExpression><![CDATA[$F{byr}]]></textFieldExpression></textField><textField isStretchWithOverflow=\"true\">");
        o.print("<reportElement style=\"Detail\" x=\"220\" y=\"30\" width=\"160\" height=\"15\" uuid=\"07eb568a-7c5d-4d3a-ae7b-1f8afa24dcd2\">");
        o.print("<property name=\"com.jaspersoft.studio.spreadsheet.connectionID\" value=\"2bb4245e-5ca3-404f-b344-122613089494\"/>");
        o.print("</reportElement><textElement textAlignment=\"Right\"/><textFieldExpression><![CDATA[$F{kbl}]]></textFieldExpression>");
        o.print("</textField><staticText><reportElement style=\"Column header\" x=\"0\" y=\"0\" width=\"100\" height=\"15\" uuid=\"90bdf754");
        o.print("-e8dc-4892-9421-986444f3fedc\"/><text><![CDATA[Total]]></text></staticText><staticText><reportElement style=\"Column header\" ");
        o.print("x=\"0\" y=\"15\" width=\"100\" height=\"15\" uuid=\"e36b60ee-da79-4e27-a09a-442926e9accb\"/><text><![CDATA[Bayar]]></text>");
        o.print("</staticText><staticText><reportElement style=\"Column header\" x=\"0\" y=\"30\" width=\"100\" height=\"15\" uuid=\"926d2031");
        o.print("-d70d-4963-a6b4-6269b8336f10\"/><text><![CDATA[Kembalian]]></text></staticText></band></summary></jasperReport>");
    }
}
