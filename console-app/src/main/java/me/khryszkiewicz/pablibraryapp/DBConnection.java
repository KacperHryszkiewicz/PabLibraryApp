package me.khryszkiewicz.pablibraryapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public DBConnection() {
    }

    public Connection getConn() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:55555/library_project?user=root&serverTimezone=UTC");

            System.out.println("Jest połączenie :)");

            // conn.close();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }


        return conn;
    }

}