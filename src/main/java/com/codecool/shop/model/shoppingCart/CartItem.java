package com.codecool.shop.model.shoppingCart;

import com.codecool.shop.model.Product;

/**
 * A CartItem stores a Product and the amount of this Product.
 */
class CartItem {

    private Product product;
    private int amount;

    CartItem(Product product) {
        this.product = product;
        this.amount = 1;
    }

    protected int getAmount() {
        return amount;
    }

    Product getProduct() {
        return product;
    }

    protected void incAmount() {
        amount++;
    }

    protected void decAmount() {
        amount--;
    }

    protected void setAmount(int amount) {
        this.amount = amount;
    }
}
