package com.codecool.shop.dao.implementation;


import com.codecool.shop.DbConnection;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoJdbc implements ProductDao {


    private static ProductDaoJdbc instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        System.out.println("norbi a király");
    }

    @Override
    public Product find(int id) {
        List<List<String>> findProduct = execute("SELECT " +
                "id, " +
                "name, " +
                "description " +
                "default_price," +
                "product_category," +
                "product_supplier," +
                "image," +
                "currency_type" +
                "FROM product_category WHERE id="+ id + ";");
        List<String> findProductList = findProduct.get(0);
        ProductCategory productCategory = ProductCategoryDaoJdbc.getInstance().find(Integer.parseInt(findProductList.get(5)));
        Supplier supplier = SupplierDaoJdbc.getInstance().find(Integer.parseInt(findProductList.get(6)));
        Product product=  new Product(
                findProductList.get(1),
                Float.parseFloat(findProductList.get(3)),
                findProductList.get(7),
                findProductList.get(2),
                productCategory,
                supplier
                );
        System.out.println(product);

        return product;
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public List<Product> getAll() {
        List<Product> getAll = new ArrayList<>();
        List<List<String>> allProduct = execute("SELECT " +
                "id," +
                "name," +
                "description," +
                "default_price," +
                "product_category," +
                "product_supplier," +
                "image," +
                "currency_type " +
                "FROM product");
        for (List<String> strings : allProduct) {
            ProductCategory productCategory = ProductCategoryDaoJdbc.getInstance().find(Integer.parseInt(strings.get(5)));
            Supplier supplier = SupplierDaoJdbc.getInstance().find(Integer.parseInt(strings.get(6)));

            int id =  Integer.parseInt(strings.get(0));
            Product product =  new Product(
                    strings.get(1),                     //name
                    Float.parseFloat(strings.get(3)),   //price
                    strings.get(4),                     //currency type
                    strings.get(2),                     //description
                    productCategory,
                    supplier);
            product.setId(id);
            getAll.add(product);

        }
        return getAll;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }

    private List<List<String>> execute(String query) {
        List<List<String>> products = new ArrayList<>();

        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement();
        ){
            statement.executeQuery(query);

            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                List<String> row = new ArrayList<>();
                row.add(String.valueOf(rs.getInt("id")));
                row.add(rs.getString("name"));
                row.add(rs.getString("description"));
                row.add(String.valueOf(rs.getInt("default_price")));
                row.add(rs.getString("currency_type"));
                row.add(String.valueOf(rs.getInt("product_category")));
                row.add(String.valueOf(rs.getInt("product_supplier")));
                row.add(rs.getString("image"));
                products.add(row);
            } return products;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
