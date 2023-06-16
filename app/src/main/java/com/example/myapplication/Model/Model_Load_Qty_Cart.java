package com.example.myapplication.Model;

import java.util.List;

public class Model_Load_Qty_Cart {
    String qty_cart;
    List<Model_Load_Qty_Cart> Model_Load_Qty_Cart;

    public Model_Load_Qty_Cart(String qty_cart) {
        this.qty_cart = qty_cart;
    }

    public String getQty_cart() {
        return qty_cart;
    }

    public void setQty_cart(String qty_cart) {
        this.qty_cart = qty_cart;
    }

    public List<com.example.myapplication.Model.Model_Load_Qty_Cart> getModel_Load_Qty_Cart() {
        return Model_Load_Qty_Cart;
    }

    public void setModel_Load_Qty_Cart(List<com.example.myapplication.Model.Model_Load_Qty_Cart> model_Load_Qty_Cart) {
        Model_Load_Qty_Cart = model_Load_Qty_Cart;
    }
}
