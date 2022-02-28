package me.khryszkiewicz.pablibraryapp;

import java.sql.*;
import java.util.Scanner;

public class MenuUsers implements MenuInterface {
    Connection conn;
    Scanner scan = new Scanner(System.in);
    Statement stmt;

    public MenuUsers(Connection conn) throws SQLException {
        this.conn = conn;
        stmt = conn.createStatement();
    }

    public void displayMenu() throws Exception {
        System.out.println("[MENU APLIKACJI]");
        System.out.println("[TABELA UZYTKOWNICY]");
        System.out.println("[1] Dodaj uzytkownika");
        System.out.println("[2] Usun uzytkownika");
        System.out.println("[3] Edytuj uzytkownika");
        System.out.println("[4] Wyswietl uzytkownikow.");
        System.out.println("[5] Menu");
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
        System.out.println("[Dodawanie]");

        System.out.println("Podaj dzien urodzenia(liczba):");
        int dayBirth = scan.nextInt();
        System.out.println("Podaj miesiac urodzenia(liczba):");
        int monthBirth = scan.nextInt();
        System.out.println("Podaj rok urodzenia(liczba):");
        int yearBirth = scan.nextInt();
        System.out.println("Podaj imie:");
        String name;
        scan.nextLine();
        name = scan.nextLine();
//                name = scan.nextLine();
        System.out.println("Podaj nazwisko:");
        String surname = new String(scan.nextLine());


        String queryInsert = new String("INSERT INTO user values(null,? , ?, ?, null);");
        PreparedStatement insertStatement = conn.prepareStatement(queryInsert);
        insertStatement.setString(1, name);
        insertStatement.setString(2, surname);
        insertStatement.setString(3, "" + yearBirth + "-" + monthBirth + "-" + dayBirth);
        System.out.println(insertStatement.toString());
        insertStatement.executeUpdate();


    }

    @Override
    public void delete() throws SQLException {
        System.out.println("[Usuwanie]");
        System.out.println("Podaj id uzytkownika do usunieca:");
        int idToDelete = scan.nextInt();
        String queryDelete = "Delete from user where id=" + idToDelete;
//                PreparedStatement deleteStatement = conn.prepareStatement(queryDelete);
        stmt.execute(queryDelete);
    }

    @Override
    public void update() throws SQLException {
        System.out.println("[Edytowanie]");
        System.out.println("Podaj id uzytkownika do edycji:");
        int idToUpdate = scan.nextInt();

        System.out.println("Podaj dzien urodzenia(liczba):");
        int dayBirthToUpdate = scan.nextInt();
        System.out.println("Podaj miesiac urodzenia(liczba):");
        int monthBirthToUpdate = scan.nextInt();
        System.out.println("Podaj rok urodzenia(liczba):");
        int yearBirthToUpdate = scan.nextInt();
        System.out.println("Podaj imie:");
        String nameToUpdate;
        nameToUpdate = scan.nextLine();
        nameToUpdate = scan.nextLine();
        System.out.println("Podaj nazwisko:");
        String surnameToUpate = new String(scan.nextLine());


        String queryUpdate = new String("UPDATE user SET name= ?,surname= ?,datebirth= ? where id = ? ");
        PreparedStatement updateStatement = conn.prepareStatement(queryUpdate);
        updateStatement.setString(1, nameToUpdate);
        updateStatement.setString(2, surnameToUpate);
        updateStatement.setString(3, "" + yearBirthToUpdate + "-" + monthBirthToUpdate + "-" + dayBirthToUpdate + "");
        updateStatement.setInt(4, idToUpdate);
        System.out.println(updateStatement.toString());
        updateStatement.executeUpdate();


    }

    @Override
    public void select() throws SQLException {
        System.out.println("[Wyswietlanie]");


        ResultSet rs = stmt.executeQuery("SELECT * FROM user");
        ResultSetMetaData md = rs.getMetaData();
        System.out.print("|");
        for (int i = 1; i <= md.getColumnCount(); i++) {
            System.out.printf("%-15s|", md.getColumnName(i) + "");
        }
        System.out.println();

        while (rs.next()) {
            System.out.print("|");
            for (int i = 1; i <= md.getColumnCount(); i++) {

                System.out.printf("%-15s|", rs.getString(i) + "");
            }
            System.out.println();
        }
    }


}
