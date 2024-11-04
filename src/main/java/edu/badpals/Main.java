package edu.badpals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/paises", "root", "root");

            System.out.println("Connected to MySQL database.");

            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM t_paises ORDER BY Nombre ASC");

            while (rs.next()) {
                System.out.println(rs.getString("Nombre"));
            }

            Statement s2 = conexion.createStatement();
            int rowsInserted = s2.executeUpdate("INSERT INTO t_paises (NOMBRE, HABITANTES, CAPITAL, MONEDA) " +
                    "VALUES ('Sri Lanka', 21919000, 'Sri Jayawardenapura Kotte', 'Rupia de Sri Lanka');");

            System.out.println("Rows inserted: " + rowsInserted);

            Statement s3 = conexion.createStatement();
            int rowsUpdated = s3.executeUpdate("UPDATE t_paises " +
                    "SET NOMBRE = 'China', " +
                    "    HABITANTES = 1402000000, " +
                    "    CAPITAL = 'Beijing', " +
                    "    MONEDA = 'Yuan' " +
                    "WHERE NOMBRE = 'Sri Lanka';");
            System.out.println("Rows updated: " + rowsUpdated);

            Statement s4 = conexion.createStatement();
            int rowsDeleted = s4.executeUpdate("DELETE FROM t_paises WHERE NOMBRE = 'China';");
            System.out.println("Rows deleted: " + rowsDeleted);

        } catch (Exception e) {
            System.err.println("SQL error occurred.");
            e.printStackTrace();
        }
    }
}