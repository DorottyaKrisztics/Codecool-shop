package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

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
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(1));
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        return new ModelAndView(params, "product/index");
    }


    public static ModelAndView renderCartReview(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        List<CartItem> cartItems = ShoppingCart.getInstance().getCartItems();
        float totalPrice = ShoppingCart.getInstance().getAllPrice();

        Map params = new HashMap<>();
        params.put("totalprice", totalPrice);
        params.put("cartitems", cartItems);
        return new ModelAndView(params, "product/cartReview");
    }



}
