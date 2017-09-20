package com.codecool.shop.model.shoppingCart;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    List<CartItem> cartItems = new ArrayList<>();
    private int totalItemCount;

    /**
     * Adds a single item to the shopping cart.
     * If there is mor with this id,
     * it increases the item counter by oe than one itemne.
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
     * Removes a single item from the shopping cart.
     * If there is more than one item with this id,
     * it decreases the item counter by one.
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
}

