import static java.lang.Integer.parseInt;
import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import com.codecool.shop.model.shoppingCart.ShoppingCart;

import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;


public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");


        // Always add generic routes to the end
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());
        // Equivalent with above
        get("/index", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderProducts(req, res));
        });

        get("/cartReview", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderCartReview(req, res));
        });
        get("/checkout", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderCheckout(req, res));
        });
        post("/payment", (Request req, Response res ) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderPayment(req, res));

        });
        get("/category", (Request req, Response res) -> {
            System.out.println(req.queryParams("id"));
            int id = Integer.parseInt(req.queryParams("id"));
            return new ThymeleafTemplateEngine().render(ProductController.renderProductCategory(req, res, id));
        });

        get("/supplier", (Request req, Response res) -> {
            System.out.println(req.queryParams("id"));
            int id = parseInt(req.queryParams("id"));
            return new ThymeleafTemplateEngine().render(ProductController.renderSupplier(req, res, id));
        });

        post("/addtocart", (request, response) -> {
            String id = request.queryParams().iterator().next();
            ShoppingCart.getInstance().addItem(Integer.parseInt(id));
            return "id added: " + id;
        });

        post("/index", (Request req, Response res) -> {
            ShoppingCart.getInstance().dropCartItems();
            return new ThymeleafTemplateEngine().render(ProductController.renderProducts(req, res));
        });

        post("/removeItem", (request, response) -> {
            String itemId = request.queryParams().iterator().next();
            ShoppingCart.getInstance().removeProduct(Integer.parseInt(itemId));
            return new ThymeleafTemplateEngine().render( ProductController.renderCartReview(request, response) );
        });
        post("/setItemAmount", (request, response) -> {
            String itemId = request.queryParams().iterator().next();
            String itemAmount = request.queryParams(itemId);
            ShoppingCart.getInstance().getCartItemById(Integer.parseInt(itemId)).setAmount(Integer.parseInt(itemAmount));

            return new ThymeleafTemplateEngine().render( ProductController.renderCartReview(request, response) );
        });



        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier Porn = new Supplier("Porn", "XXX");
        supplierDataStore.add(Porn);
        Supplier eBay = new Supplier("eBay", "The biggest online store ever");
        supplierDataStore.add(eBay);
        Supplier ikea = new Supplier("IKEA", "Interior design");
        supplierDataStore.add(ikea);


        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to " +
                "tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory ball = new ProductCategory("Ball", "Toys", "A stuffed soft ball for playing in the class room");
        productCategoryDataStore.add(ball);
        ProductCategory Adult = new ProductCategory("Adult", "Hardware", "XXX XXX XXX");
        productCategoryDataStore.add(Adult);


        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good" +
                " parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless " +
                "Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great " +
                "value for media consumption.", tablet, amazon));

        productDataStore.add(new Product("Anal intruder X", 89, "USD","XXX XXX XXX", Adult, Porn));
        productDataStore.add(new Product("LEKA Soft toy, ball, multicolor", 1.99f, "USD", "Fabric: 100 % polyester." +
                "Filling: Polyester fiber fill. Recommended for all ages.", ball, ikea));
        productDataStore.add(new Product("Pokemon ball", 12, "USD", "Gotta catch ‘em all! If you want to catch a Bulbasaur," +
                "Weedle, Rattata or Jigglypuff Pokemon you’ll need a Pokeball, and this officially licensed Poke Ball Plush is ideal for any Pokemen trainers!", ball, eBay));
        productDataStore.add(new Product("Yoda Soft Ball", 8.5f, "USD", "Star Wars fans will love these " +
                "fantastic talking plush toys! Choose from Yoda, R2D2, Darth Vader and Chewbacca characters. ", ball, eBay));
        productDataStore.add(new Product("Ty Beanie Ballz Fable Unicorn Plush", 16.99f, "USD", "\n" +
                "Beanie Ballz are a little wild and whacky, toss 'em and they always land on their feet." +
                " Collect them all.", ball, amazon));


        ShoppingCart.getInstance().addItem(1);
        ShoppingCart.getInstance().addItem(2);
        ShoppingCart.getInstance().addItem(3);
        ShoppingCart.getInstance().addItem(2);
        ShoppingCart.getInstance().addItem(2);
    }


}
