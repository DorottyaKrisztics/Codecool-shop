package com.codecool.shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<ResultSet> execute(String query, boolean isQuery) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ) {
            if (isQuery) {
                List<ResultSet> resultSets = new ArrayList<>();
                statement.executeQuery(query);

                ResultSet rs = statement.getResultSet();
                while (rs.next()) {
                    resultSets.add(rs);
                }
                return resultSets;
            }
            else {
                statement.executeUpdate(query);
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}