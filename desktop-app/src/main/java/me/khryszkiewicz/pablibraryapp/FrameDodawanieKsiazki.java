package me.khryszkiewicz.pablibraryapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.security.DigestException;
import java.sql.*;

public class FrameDodawanieKsiazki extends JFrame {

    private FrameMenuGlowne frameMenuGlowne;

    private DBConnection dbConnection;

    private Connection conn;

    private Toolkit zestaw;

    private Dimension wymiary;

    public FrameDodawanieKsiazki(FrameMenuGlowne frameMenuGlowne) throws HeadlessException {
        this.frameMenuGlowne = frameMenuGlowne;
        dbConnection = frameMenuGlowne.getDbConnection();
        conn = frameMenuGlowne.getConn();
        zestaw = frameMenuGlowne.getZestaw();
        wymiary = frameMenuGlowne.getWymiary();

        setVisible(true);
        this.setLayout(new BorderLayout());




        JButton buttonDodawanieKsiazki = new JButton("DODAJ KSIAZKE");

        JPanel mainPanel = new JPanel();

        JLabel title = new JLabel("Tytul:");
        JLabel category = new JLabel("Kategoria:");
        JLabel author = new JLabel("Autor:");

        JTextField titleTextField = new JTextField();
        titleTextField.setPreferredSize(new Dimension(300, 30));

        String[] categoryList = {"Horror", "Fantasy", "Thriller", "Documentary", "Romance"};
        JComboBox categoryComboBox = new JComboBox(categoryList);

        Statement stmt = null;
        String query = "Select name, surname from authors";
        String[] dataAuthor = new String[30];
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {


                String authorName = rs.getString("Name");
                String authorSurname = rs.getString("Surname");

                String authorSet = authorName + " " + authorSurname;
                dataAuthor[i] = authorSet;
                i++;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        JComboBox authorComboBox = new JComboBox(dataAuthor);

        JCheckBox checkBox = new JCheckBox("Lektura szkolna:");
        JLabel pasekStanu = new JLabel("Dodajesz ksiazke.");
        pasekStanu.setBackground(Color.WHITE);
        pasekStanu.setOpaque(true);

        mainPanel.add(title);
        mainPanel.add(titleTextField);

        mainPanel.add(category);
        mainPanel.add(categoryComboBox);

        mainPanel.add(author);
        mainPanel.add(authorComboBox);

        mainPanel.add(checkBox);

        buttonDodawanieKsiazki.addActionListener(e -> {
            String titleInsert = titleTextField.getText();
            titleInsert.trim();
            String categoryInsert = (String) categoryComboBox.getSelectedItem();
            int authorInsert = authorComboBox.getSelectedIndex();


            Connection conn = dbConnection.getConn();


            String queryInsert = "INSERT INTO books values(null,?, ?, ?,100);";
            PreparedStatement preparedStmt = null;
            try {
                preparedStmt = conn.prepareStatement(queryInsert);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStmt.setString(1, titleInsert);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStmt.setInt(3, (authorInsert+1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStmt.setString(2, categoryInsert);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        setVisible(false);
        });



        this.add(buttonDodawanieKsiazki, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(pasekStanu, BorderLayout.PAGE_END);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 500));
        this.setLocationRelativeTo(null);
        pack();



    }
}
