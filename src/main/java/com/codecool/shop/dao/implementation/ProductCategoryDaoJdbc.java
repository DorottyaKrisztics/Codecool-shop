package com.codecool.shop.dao.implementation;

import com.codecool.shop.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc {

    private static ProductCategoryDaoJdbc instance = null;

    private ProductCategoryDaoJdbc() {
    }

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null){
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    private List<List<String>> execute(String query) {
        List<List<String>> productCategories = new ArrayList<>();

        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement();
        ){
            statement.executeQuery(query);

            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                List<String> row = new ArrayList<>();
                row.add(rs.getString("name"));
                row.add(rs.getString("department"));
                row.add(rs.getString("description"));
                productCategories.add(row);
            } return productCategories;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<List<String>> getAll(){
        return execute("SELECT name,department,description FROM product_category");
    }

}
