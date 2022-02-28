package me.khryszkiewicz.pablibraryapp;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Menu {

    private Scanner scan;
    private Connection conn;
    private Statement stmt;
    private MenuUsers menuUsers;
    private MenuPublishers menuPublishers;
    private MenuAuthors menuAuthors;
    private MenuBooks menuBooks;


    public Menu(Connection conn) throws SQLException, IOException {
        scan = new Scanner(System.in);
        this.conn = conn;
        stmt = conn.createStatement();
        menuUsers = new MenuUsers(conn);
        menuAuthors = new MenuAuthors(conn);
        menuBooks = new MenuBooks(conn);
        menuPublishers = new MenuPublishers(conn);
    }

    public void displayMenu() throws Exception {
        System.out.println();
        System.out.println("[MENU APLIKACJI]");
        System.out.println("[WYBIERZ TABELE]");
        System.out.println("[1] Uzytkownicy");
        System.out.println("[2] Ksiazki");
        System.out.println("[3] Autorzy");
        System.out.println("[4] Wydawnictwa");
        System.out.println("[5] Wyjscie");
        switch (scan.nextInt()) {
            case 1:
                menuUsers.displayMenu();
                this.displayMenu();
                break;
            case 2:
                menuBooks.displayMenu();
                this.displayMenu();
                break;
            case 3:
                menuAuthors.displayMenu();
                this.displayMenu();
                break;
            case 4:
                menuPublishers.displayMenu();
                this.displayMenu();
                break;
            case 5:
                break;
            default:
                System.out.println("[SYSTEM]Nieprawidlowa wartosc.");
                this.displayMenu();

        }

    }


}