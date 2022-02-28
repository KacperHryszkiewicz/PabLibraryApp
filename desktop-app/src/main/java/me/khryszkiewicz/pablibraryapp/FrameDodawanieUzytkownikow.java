package me.khryszkiewicz.pablibraryapp;

import javafx.scene.layout.BorderRepeat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FrameDodawanieUzytkownikow extends JFrame {

    private FrameMenuGlowne frameMenuGlowne;

    private DBConnection dbConnection;

    private Connection conn;

    private Toolkit zestaw;

    private Dimension wymiary;

    public FrameDodawanieUzytkownikow(FrameMenuGlowne frameMenuGlowne) {
        this.frameMenuGlowne = frameMenuGlowne;
        dbConnection = frameMenuGlowne.getDbConnection();
        conn = frameMenuGlowne.getConn();
        zestaw = frameMenuGlowne.getZestaw();
        wymiary = frameMenuGlowne.getWymiary();
        setVisible(true);
        this.setLayout(new BorderLayout());
        JPanel panelImie = new JPanel();
        JPanel panelNazwisko = new JPanel();
        JPanel panelData = new JPanel();


        this.setPreferredSize(new Dimension(800, 500));


        JLabel labelDodajUzytkownika = new JLabel("DODAJ UŻYTKOWNIKA");

        JLabel labelImie = new JLabel("IMIE:");
        JLabel labelNazwisko = new JLabel("NAZWISKO:");
        JLabel labelUrodziny = new JLabel("URODZINY:");
        JButton submitAuthorButton = new JButton("DODAJ UZYTKOWNIKA");


        JTextField textImie = new JTextField("Wierzchosława");
        textImie.setPreferredSize(new Dimension(300,30));



        JTextField textNazwisko = new JTextField("Achmistrowicz-Wachmistrowicz");
        textNazwisko.setPreferredSize(new Dimension(300,30));

        JTextField textRok = new JTextField("2022");
        textRok.setPreferredSize(new Dimension(100,30));

        JTextField textMiesiac = new JTextField("12");
        textMiesiac.setPreferredSize(new Dimension(100,30));

        JTextField textDzien = new JTextField("30");
        textDzien.setPreferredSize(new Dimension(100,30));


        panelImie.add(labelImie);
        panelImie.add(textImie);

        panelNazwisko.add(labelNazwisko);
        panelNazwisko.add(textNazwisko);

        panelData.add(labelUrodziny);
        panelData.add(textRok);
        panelData.add(textMiesiac);
        panelData.add(textDzien);


        submitAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String imie = textImie.getText();
                imie.trim();
                String nazwisko = textNazwisko.getText();
                nazwisko.trim();
                String urodzinyRok = textRok.getText();
                urodzinyRok.trim();
                String urodzinyMiesiac = textMiesiac.getText();
                urodzinyMiesiac.trim();
                String urodzinyDzien = textDzien.getText();
                urodzinyDzien.trim();
                Connection conn = dbConnection.getConn();


                String query = "INSERT INTO user values(null,? , ?, ?, null);";
                PreparedStatement preparedStmt = null;
                try {
                    preparedStmt = conn.prepareStatement(query);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    preparedStmt.setString(1, imie);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    preparedStmt.setString(2, nazwisko);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    preparedStmt.setString(3, "" + urodzinyRok + "-" + urodzinyMiesiac + "-" + urodzinyDzien);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    preparedStmt.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                setVisible(false);
            }


        });
        JLabel pasekStanu = new JLabel("Dodajesz uzytkownika.");
        pasekStanu.setBackground(Color.WHITE);
        pasekStanu.setOpaque(true);

        JPanel panel = new JPanel();
        panel.add(panelImie, BorderLayout.NORTH);
        panel.add(panelNazwisko, BorderLayout.CENTER);
        panel.add(panelData, BorderLayout.SOUTH);

//        this.add(labelDodajUzytkownika, BorderLayout.PAGE_START);
//        this.add(panelImie);
//        this.add(panelNazwisko);
//        this.add(panelData);
        this.add(panel);
        this.add(submitAuthorButton,BorderLayout.NORTH);
        this.add(pasekStanu, BorderLayout.PAGE_END);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        pack();
//


//        getContentPane().add(pasekStanu, BorderLayout.SOUTH);
//        pack();
//    jDialog.getContentPane().add(frameMenuGlowne);
//    pack();
//    jDialog.setVisible(true);
//    setLocationRelativeTo(null);
    }
}
