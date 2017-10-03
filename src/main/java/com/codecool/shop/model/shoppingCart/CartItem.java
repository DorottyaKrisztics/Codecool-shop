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

    /**
     * Sets a new amount if it is valid.
     *
     * @param amount
     */
    public void setAmount(int amount) {
        int difference = amount - this.amount;
        ShoppingCart sc = ShoppingCart.getInstance();

        // Zero or negative input removes this object
        if (amount <= 0) {
            sc.totalItemCount -= this.amount;
            sc.getCartItems().remove(this);
        }
        else {
            if (difference < this.amount) {
                difference = -difference;
            }
            sc.totalItemCount += difference;
            this.amount += difference;
        }
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
}
