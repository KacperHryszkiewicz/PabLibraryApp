package me.khryszkiewicz.pablibraryapp;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FrameListaKsiazek extends JFrame {

    private FrameMenuGlowne frameMenuGlowne;

    private DBConnection dbConnection;

    private Connection conn;

    private Toolkit zestaw;

    private Dimension wymiary;

    public FrameListaKsiazek(FrameMenuGlowne frameMenuGlowne){
        this.frameMenuGlowne = frameMenuGlowne;
        dbConnection = frameMenuGlowne.getDbConnection();
        conn = frameMenuGlowne.getConn();
        zestaw = frameMenuGlowne.getZestaw();
        wymiary = frameMenuGlowne.getWymiary();

        setVisible(true);
        this.setLayout(new BorderLayout());

        JLabel labelTitleFrame = new JLabel("LISTA KSIAZEK");


        JToolBar toolbar = new JToolBar("toolbar");


        JButton buttonOdswiez = new JButton("ODŚWIEŻ");

        toolbar.add(buttonOdswiez);


        Statement stmt = null;

        String query = "SELECT books.title AS Tytul, CONCAT(authors.name,\" \", authors.surname) AS Autor FROM books inner join authors on books.authorId = authors.id";

        int n = 50;


        String[][] data = new String[n][n];
        int i = 0;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                String title = rs.getString("Tytul");
                String author = rs.getString("Autor");


                data[i][0] = title;
                data[i][1] = author;




                System.out.println(title+" "+ author);
                i++;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        buttonOdswiez.addActionListener(e -> {
            this.setVisible(false);
            new FrameListaKsiazek(this.frameMenuGlowne);

        });

        String column[] = {"Tytul", "Autor"};

        JLabel pasekStanu = new JLabel("Jestes w oknie listy ksiazek.");
        pasekStanu.setBackground(Color.WHITE);
        pasekStanu.setOpaque(true);



        JTable tableBooks = new JTable(data, column);
        JScrollPane scrollPane = new JScrollPane(tableBooks);

        this.setVisible(true);
        this.add(labelTitleFrame, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(toolbar,BorderLayout.WEST);
        this.add(pasekStanu, BorderLayout.PAGE_END);
        this.setPreferredSize(new Dimension(800,600));
        pack();
    }
}
