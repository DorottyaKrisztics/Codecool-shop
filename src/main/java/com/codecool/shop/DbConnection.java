package com.codecool.shop;
import java.sql.*;

public class DbConnection {
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public String executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.executeQuery(query);
            ResultSet rs = statement.getResultSet();
            rs.next();
            return rs.getString("id");

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}