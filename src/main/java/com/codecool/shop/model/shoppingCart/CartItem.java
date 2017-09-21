package com.codecool.shop.model.shoppingCart;

import com.codecool.shop.model.Product;

/**
 * A CartItem stores a Product and the amount of this Product.
 */
public class CartItem {

    private Product product;
    private int amount;

    CartItem(Product product) {
        this.product = product;
        this.amount = 1;
    }

    public int getAmount() {
        return amount;
    }

    public Product getProduct() {
        return product;
    }

    protected void incAmount() {
        amount++;
    }

    protected void decAmount() {
        amount--;
    }

    public void setAmount(int amount) {
        if (amount <= 0){
            ShoppingCart.getInstance().getCartItems().remove(this);
        }
        this.amount = amount;
    }
}
