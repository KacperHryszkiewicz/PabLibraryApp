package me.khryszkiewicz.pablibraryapp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MenuAuthors implements MenuInterface {
    private Connection conn;
    private Scanner scan;
    private Statement stmt;

    public MenuAuthors(Connection conn) throws SQLException {
        this.conn = conn;
        scan = new Scanner(System.in);
        stmt = conn.createStatement();
    }

    @Override
    public void displayMenu() throws Exception {
        System.out.println("[MENU APLIKACJI]");
        System.out.println("[TABELA AUTORZY]");
        System.out.println("[1] Dodaj autora");
        System.out.println("[2] Usun autora");
        System.out.println("[3] Edytuj autora");
        System.out.println("[4] Wyswietl autora.");
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

