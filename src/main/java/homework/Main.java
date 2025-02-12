package homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/apart?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "password1";

    static Connection connection;

    public static void main(String[] args) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());


            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            if (connection != null) {
                System.out.println("Connected to the database successfully");
            }

            ApartmentDao apartmentDao = new ApartmentDaoImpl(connection);

            createTable(apartmentDao);
            showUserInterface(apartmentDao);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connection closed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void createTable(ApartmentDao apartmentDao) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS apart (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "district VARCHAR(50) NOT NULL, " +
                "address VARCHAR(100) NOT NULL, " +
                "area DECIMAL(5,2) NOT NULL, " +
                "rooms INT NOT NULL, " +
                "price DECIMAL(10,2) NOT NULL" +
                ")";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(createTableSQL);
    }

    private static void showUserInterface(ApartmentDao apartmentDao) {
        try {
            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Show all apartments");
                System.out.println("2. Search by address");
                System.out.println("3. Search by room count");
                System.out.println("4. Fill the table with 5 sample entries");
                System.out.println("5. Clear all entries");
            }
        }
    }
