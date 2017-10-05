package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;

import com.codecool.shop.model.shoppingCart.CartItem;
import com.codecool.shop.model.shoppingCart.ShoppingCart;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductDao productDataStore2 = ProductDaoJdbc.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore2 = ProductCategoryDaoJdbc.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        SupplierDao supplierDataStore2 = SupplierDaoJdbc.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore2.getAll());
        params.put("products", productDataStore2.getAll());
        params.put("supplier", supplierDataStore2.getAll());
        params.put("totalitemcount", ShoppingCart.getInstance().getTotalItemCount());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductCategory(Request req, Response res, int id) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductDao productDataStore2 = ProductDaoJdbc.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore2 = ProductCategoryDaoJdbc.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        SupplierDao supplierDataStore2 = SupplierDaoJdbc.getInstance();

        Map params = new HashMap<>();
        params.put("allcategory", productCategoryDataStore2.getAll());
        params.put("allsupplier", supplierDataStore2.getAll());
        params.put("category", productCategoryDataStore2.find(id));
        params.put("products", productDataStore2.getBy(productCategoryDataStore2.find(id)));
        return new ModelAndView(params, "product/category");

    }

    public static ModelAndView renderSupplier(Request req, Response res, int id) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductDao productDataStore2 = ProductDaoJdbc.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore2 = ProductCategoryDaoJdbc.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        SupplierDao supplierDataStore2 = SupplierDaoJdbc.getInstance();
        Map params = new HashMap<>();

        params.put("allsupplier", supplierDataStore2.getAll());
        params.put("supplier", supplierDataStore2.find(id));
        params.put("category", productCategoryDataStore2.getAll());
        params.put("products", productDataStore2.getBy(supplierDataStore2.find(id)));
        return new ModelAndView(params, "product/supplier");

    }


    public static ModelAndView renderCartReview(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        List<CartItem> cartItems = ShoppingCart.getInstance().getCartItems();
        float totalPrice = ShoppingCart.getInstance().getAllPrice();

        Map params = new HashMap<>();
        params.put("totalprice", totalPrice);
        params.put("cartitems", cartItems);
        params.put("totalquantity", ShoppingCart.getInstance().getTotalItemCount());
        return new ModelAndView(params, "product/cartReview");
    }

    public static ModelAndView renderCheckout(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        List<CartItem> cartItems = ShoppingCart.getInstance().getCartItems();
        float totalPrice = ShoppingCart.getInstance().getAllPrice();

        Map params = new HashMap<>();
        params.put("totalfee", totalPrice);
        return new ModelAndView(params, "product/checkout");
    }

    public static ModelAndView renderPayment(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        List<CartItem> cartItems = ShoppingCart.getInstance().getCartItems();
        float totalPrice = ShoppingCart.getInstance().getAllPrice();

        Map params = new HashMap<>();
        params.put("totalfee", totalPrice);
        return new ModelAndView(params, "product/payment");
    }


}
