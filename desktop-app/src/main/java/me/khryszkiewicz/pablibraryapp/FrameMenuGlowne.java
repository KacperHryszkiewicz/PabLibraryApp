package me.khryszkiewicz.pablibraryapp;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FrameMenuGlowne extends JFrame {
    private DBConnection dbConnection;
    private Connection conn;
    private Toolkit zestaw;
    private Dimension wymiary;

    public DBConnection getDbConnection() {
        return dbConnection;
    }

    public Connection getConn() {
        return conn;
    }

    public Toolkit getZestaw() {
        return zestaw;
    }

    public Dimension getWymiary() {
        return wymiary;
    }

    public FrameMenuGlowne()  {
        dbConnection = new DBConnection();
        conn = dbConnection.getConn();
        zestaw = Toolkit.getDefaultToolkit();
        wymiary = zestaw.getScreenSize();

        this.setLayout(new BorderLayout());

        JPanel panelButtons = new JPanel();

        this.setPreferredSize(new Dimension(1200,800));

        JLabel pasekStanu = new JLabel("Jestes w menu glownym.");

        JButton buttonDodajKsiazke = new JButton("DODAJ KSIAZKE");

        JButton buttonListaKsiazek = new JButton("LISTA KSIAZEK");

        JButton buttonDodajUzytkownikow = new JButton("DODAJ UŻYTKOWNIKÓW");

        buttonDodajUzytkownikow.addActionListener(e -> {
            new FrameDodawanieUzytkownikow(this);
        });


        JToolBar toolbar = new JToolBar("toolbar");


        JButton buttonOdswiez = new JButton("ODŚWIEŻ");

        toolbar.add(buttonOdswiez);


        this.add(toolbar, BorderLayout.WEST);

        JMenuBar menuBar = new JMenuBar();

        JMenu jmenu = new JMenu("File");

        JMenuItem refreshMenuItem = new JMenuItem("Odśwież");

        JMenuItem exitMenuItem =  new JMenuItem("Wyjscie");
        jmenu.add(refreshMenuItem);
        jmenu.add(exitMenuItem);


        refreshMenuItem.addActionListener(e ->{     this.setVisible(false);
            new FrameMenuGlowne();});

        exitMenuItem.addActionListener(e -> { System.exit(0);});

        menuBar.add(jmenu);
        menuBar.setBackground(Color.WHITE);
        this.setJMenuBar(menuBar);

        JLabel labelBiblioteka = new JLabel("BIBLIOTEKA");
        panelButtons.add(labelBiblioteka, BorderLayout.NORTH);


        Statement stmt = null;

        String query = "SELECT * FROM user";

        int n = 50;


        String[][] data = new String[n][n];
        int i = 0;
        try {
            stmt = this.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                String userID = rs.getString("ID");
                String userName = rs.getString("Name");
                String userSurname = rs.getString("Surname");
                String userDateBirth = rs.getString("DateBirth");
//                String userlibraryID = rs.getString("libraryID");


                data[i][0] = userID;
                data[i][1] = userName;
                data[i][2] = userSurname;
                data[i][3] = userDateBirth;


                System.out.println(userID + " " + userName + " " + userSurname + " " + userDateBirth + " " );
                i++;
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        String column[] = {"ID", "Imie", "Nazwisko", "Data urodzenia",};


        JTable tableUzytkownicy = new JTable(data, column);


        JScrollPane scrollPane = new JScrollPane(tableUzytkownicy);


        buttonOdswiez.addActionListener(e -> {
            this.setVisible(false);
           new FrameMenuGlowne();

        });

        buttonListaKsiazek.addActionListener(e -> {new FrameListaKsiazek(this);});

        buttonDodajKsiazke.addActionListener(e ->{new FrameDodawanieKsiazki(this);});


        this.add(scrollPane, BorderLayout.CENTER);

        panelButtons.add(buttonDodajKsiazke);
        panelButtons.add(buttonDodajUzytkownikow);
        panelButtons.add(buttonListaKsiazek);
        this.add(panelButtons, BorderLayout.NORTH);


        pasekStanu.setBackground(Color.WHITE);
        pasekStanu.setOpaque(true);


        getContentPane().add(pasekStanu, BorderLayout.SOUTH);
        pack();


        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
