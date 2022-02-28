package me.khryszkiewicz.pablibraryapp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MenuBooks implements MenuInterface {
    private Connection conn;
    private Scanner scan = new Scanner(System.in);
    private Statement stmt;

    public MenuBooks(Connection conn) throws SQLException {
        this.conn = conn;
        stmt = conn.createStatement();
    }

    public void displayMenu() throws SQLException {
        System.out.println("[MENU APLIKACJI]");
        System.out.println("[TABELA KSIAZKI]");
        System.out.println("[1] Dodaj KSIAZKE");
        System.out.println("[2] Usun KSIAZKE");
        System.out.println("[3] Edytuj KSIAZKE");
        System.out.println("[4] Wyswietl KSIAZKE.");
        switch (scan.nextInt()) {
            case 1:
                this.insert();
                break;

            case 2:
                this.delete();
                break;
            case 3:
                this.update();
                break;
            case 4:
                this.select();
                break;

            default:
                break;
        }

    }

    @Override
    public void insert() throws SQLException {

    }

    @Override
    public void delete() throws SQLException {

    }

    @Override
    public void update() throws SQLException {

    }

    @Override
    public void select() throws SQLException {

    }
}
