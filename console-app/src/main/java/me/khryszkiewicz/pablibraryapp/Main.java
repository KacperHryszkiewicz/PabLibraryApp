package me.khryszkiewicz.pablibraryapp;

public class Main {
    public static void main(String[] args) throws Exception {
        DBConnection dbConnection = new DBConnection();
        Menu menu = new Menu(dbConnection.getConn());

        menu.displayMenu();
    }
}
