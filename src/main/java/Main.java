import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.shoppingCart.ShoppingCart;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static java.lang.Integer.parseInt;
import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();

        // Always add generic routes to the end
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());

        // Equivalent with above
        get("/index", (req, res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderProducts(req, res));
        });

        // Post request clears the shopping cart
        post("/index", (req, res) -> {
            ShoppingCart.getInstance().dropCartItems();
            return new ThymeleafTemplateEngine().render(ProductController.renderProducts(req, res));
        });

        get("/cartreview", (req, res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderCartReview(req, res));
        });
        get("/checkout", (req, res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderCheckout(req, res));
        });
        post("/payment", (req, res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderPayment(req, res));

        });
        get("/category", (req, res) -> {
            System.out.println(req.queryParams("id"));
            int id = Integer.parseInt(req.queryParams("id"));
            return new ThymeleafTemplateEngine().render(ProductController.renderProductCategory(req, res, id));
        });

        get("/supplier", (req, res) -> {
            System.out.println(req.queryParams("id"));
            int id = parseInt(req.queryParams("id"));
            return new ThymeleafTemplateEngine().render(ProductController.renderSupplier(req, res, id));
        });

        post("/addtocart", (req, res) -> {
            String id = req.queryParams().iterator().next();
            ShoppingCart.getInstance().addItem(Integer.parseInt(id));
            return new ThymeleafTemplateEngine().render(ProductController.renderProducts(req, res));
        });

        post("/removeitem", (req, res) -> {
            String itemId = req.queryParams().iterator().next();
            ShoppingCart.getInstance().removeProduct(Integer.parseInt(itemId));
            return new ThymeleafTemplateEngine().render(ProductController.renderCartReview(req, res));
        });
        post("/setitemamount", (req, res) -> {
            String itemId = req.queryParams().iterator().next();
            String itemAmount = req.queryParams(itemId);
            ShoppingCart.getInstance().getCartItemById(Integer.parseInt(itemId)).setAmount(Integer.parseInt(itemAmount));
            return new ThymeleafTemplateEngine().render(ProductController.renderCartReview(req, res));
        });

        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ShoppingCart cart = ShoppingCart.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
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
        ProductCategory adult = new ProductCategory("Adult", "Hardware", "XXX XXX XXX");
        productCategoryDataStore.add(adult);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good" +
                " parental controls. Helpful technical support.", tablet, amazon));

        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless " +
                "Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great " +
                "value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Libidoe", 1.99f, "USD", "This is a good deal though!", adult, eBay));
        productDataStore.add(new Product("LEKA Soft toy, ball, multicolor", 1.99f, "USD", "Fabric: 100 % polyester." +
                "Filling: Polyester fiber fill. Recommended for all ages.", ball, ikea));


        productDataStore.add(new Product("Pokemon ball", 12, "USD", "Gotta catch ‘em all! If you want to catch a Bulbasaur," +
                "Weedle, Rattata or Jigglypuff Pokemon you’ll need a Pokeball, and this officially licensed Poke Ball Plush is ideal for any Pokemen trainers!", ball, eBay));
        productDataStore.add(new Product("Yoda Soft Ball", 8.5f, "USD", "Star Wars fans will love these " +
                "fantastic talking plush toys! Choose from Yoda, R2D2, Darth Vader and Chewbacca characters. ", ball, eBay));
        productDataStore.add(new Product("Ty Beanie Ballz Fable Unicorn Plush", 16.99f, "USD", "\n" +
                "Beanie Ballz are a little wild and whacky, toss 'em and they always land on their feet." +
                " Collect them all.", ball, amazon));

        cart.addItem(1);
        cart.addItem(2);
        cart.addItem(3);
        cart.addItem(4);
        cart.addItem(5);
        cart.addItem(6);
        cart.addItem(7);
    }
}
