package com.codecool.shop.dao.implementation;

import com.codecool.shop.DbConnection;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    private static SupplierDaoJdbc instance = null;

    private SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        System.out.println("norbi még mindig király");
    }

    @Override
    public Supplier find(int id) {
        List<List<String>> findSupplier = execute("SELECT " +
                "id, " +
                "name, " +
                "description " +
                    "FROM product_supplier WHERE id="+ id + ";");
        Supplier supplier =  new Supplier(findSupplier.get(0).get(1),findSupplier.get(0).get(2));
        System.out.println(supplier);
        return supplier;
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> getAll = new ArrayList<>();
        List<List<String>> allProductSupplier = execute("SELECT " +
                "id," +
                "name," +
                "description " +
                "FROM product_supplier");
        for (List<String> strings : allProductSupplier) {
            int id =  Integer.parseInt(strings.get(0));
            Supplier supplier =  new Supplier(strings.get(1),strings.get(2));
            supplier.setId(id);
            getAll.add(supplier);


        }
        return getAll;

    }

    private List<List<String>> execute(String query){
        List<List<String>> productSuppliers = new ArrayList<>();

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
                productSuppliers.add(row);
            } return productSuppliers;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
