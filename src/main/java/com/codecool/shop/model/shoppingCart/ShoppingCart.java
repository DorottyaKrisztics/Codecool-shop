package com.codecool.shop.model.shoppingCart;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private static List<CartItem> cartItems = new ArrayList<>();
    private int totalItemCount;
    private static ShoppingCart instance = null;

    private ShoppingCart() {
    }

    public static ShoppingCart getInstance() {
        if (instance == null) return new ShoppingCart();
        else return instance;
    }

    /**
     * Adds a single item to the shopping cart if there is only one item having this id.
     * If there is more than one item with this id, it increases the item counter by one.
     *
     * @param productId product to add
     */
    public void addItem(int productId) {
        boolean found = false;
        Product product = ProductDaoMem.getInstance().find(productId);
        if (product != null) {
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().equals(product)) {
                    cartItem.incAmount();
                    found = true;
                    break;
                }
            }
            if (!found) cartItems.add(new CartItem(product));
        }
        totalItemCount++;
    }

    /**
     * Removes a single item from the shopping cart if there is only one item having this id.
     * If there is more than one item with this id, it decreases the item counter by one.
     *
     * @param productId id of the product to remove
     */
    public void removeItem(int productId) {
        CartItem item = getCartItemById(productId);
        if (item != null) {
            int count = item.getAmount();
            if (count == 1) cartItems.remove(item);
            else item.decAmount();
        }
        totalItemCount--;
    }

    /**
     * Returns the total number of items in the cart.
     */
    public int getTotalItemCount() {
        return totalItemCount;
    }

    /**
     * Returns a list of all shopping cart items.
     *
     * @return all items that are in the shopping cart
     */
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    /**
     * Finds the cart item having this id.
     *
     * @param productId id of the product to find
     * @return If found, returns the item. If not found, returns null.
     */
    private CartItem getCartItemById(int productId) {
        return cartItems.stream().filter(cartItem ->
                cartItem.getProduct().getId() == productId)
                .findFirst().orElse(null);
    }

    /**
     * Returns the total amount to pay
     *
     * @return total amount to pay
     */
    public float getAllPrice() {
        float result = 0;
        for (CartItem cartItem : cartItems) {
            result += cartItem.getAmount() * cartItem.getProduct().getDefaultPrice();
        }
        return result;
    }
}

