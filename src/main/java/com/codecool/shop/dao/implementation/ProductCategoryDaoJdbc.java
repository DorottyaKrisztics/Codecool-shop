package com.codecool.shop.dao.implementation;

import com.codecool.shop.DbConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private List<ProductCategory> DATA = new ArrayList<>();
    private static ProductCategoryDaoJdbc instance = null;

    private ProductCategoryDaoJdbc() {
    }

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null){
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
//        category.setId(DATA.size() + 1);
//        DATA.add(category)
        System.out.println("norbi a király");

    }

    @Override
    public ProductCategory find(int id) {

        List<List<String>> findCategory = execute("SELECT id, name,department,description FROM product_category WHERE id="+ id + ";");
            ProductCategory jozsi =  new ProductCategory(findCategory.get(0).get(1),findCategory.get(0).get(1),findCategory.get(0).get(2));
        System.out.println(jozsi);
            return jozsi;
    }

    @Override
    public void remove(int id) {

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
                row.add(String.valueOf(rs.getInt("id")));
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

    public List<ProductCategory> getAll(){
        List<ProductCategory> getAll = new ArrayList<>();
        List<List<String>> allProductCategory = execute("SELECT  id, name,department,description FROM product_category");
        for (List<String> strings : allProductCategory) {
            int id =  Integer.parseInt(strings.get(0));
           ProductCategory SPKW =  new ProductCategory(strings.get(1),strings.get(2),strings.get(3));
           SPKW.setId(id);
           getAll.add(SPKW);


        }
    return getAll;

    }

}
