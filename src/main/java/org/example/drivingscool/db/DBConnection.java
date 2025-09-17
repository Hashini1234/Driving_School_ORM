package org.example.drivingscool.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {
    private static DBConnection dbConnection;
    private final Connection connection;

    private final String URL = "jdbc:mysql://localhost:3306/Driving_School";
    private final String USER = "root";
    private final String PASSWORD = "1234";

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static DBConnection getInstance() throws SQLException {
        return dbConnection == null ? dbConnection = new DBConnection() : dbConnection;
    }
    public Connection getConnection() {
        return connection;
    }
}
