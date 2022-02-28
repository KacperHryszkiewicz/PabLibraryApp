package me.khryszkiewicz.pablibraryapp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public interface MenuInterface {
    Connection conn = null;
    Scanner scan = new Scanner(System.in);
    Statement stmt = null;

    void displayMenu() throws Exception;

    void insert() throws SQLException;

    void delete() throws SQLException;

    void update() throws SQLException;

    void select() throws SQLException;
}
