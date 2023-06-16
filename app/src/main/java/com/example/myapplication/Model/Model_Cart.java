package com.example.myapplication.Model;

import java.util.List;

public class Model_Cart {
    int cart_id, user_id, product_id, detail_product_id;
    String cart_qty,sum_cart, namecolor, namesize,Name_item, image_item, price_item, qty_kho_item,status;
   List<Model_Cart> Model_Cart;

    public Model_Cart(int cart_id, int user_id, int product_id, int detail_product_id, String cart_qty, String sum_cart, String namecolor, String namesize, String name_item, String image_item, String price_item, String qty_kho_item, String status) {
        this.cart_id = cart_id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.detail_product_id = detail_product_id;
        this.cart_qty = cart_qty;
        this.sum_cart = sum_cart;
        this.namecolor = namecolor;
        this.namesize = namesize;
        Name_item = name_item;
        this.image_item = image_item;
        this.price_item = price_item;
        this.qty_kho_item = qty_kho_item;
        this.status = status;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getDetail_product_id() {
        return detail_product_id;
    }

    public void setDetail_product_id(int detail_product_id) {
        this.detail_product_id = detail_product_id;
    }

    public String getCart_qty() {
        return cart_qty;
    }

    public void setCart_qty(String cart_qty) {
        this.cart_qty = cart_qty;
    }

    public String getSum_cart() {
        return sum_cart;
    }

    public void setSum_cart(String sum_cart) {
        this.sum_cart = sum_cart;
    }

    public String getNamecolor() {
        return namecolor;
    }

    public void setNamecolor(String namecolor) {
        this.namecolor = namecolor;
    }

    public String getNamesize() {
        return namesize;
    }

    public void setNamesize(String namesize) {
        this.namesize = namesize;
    }

    public String getName_item() {
        return Name_item;
    }

    public void setName_item(String name_item) {
        Name_item = name_item;
    }

    public String getImage_item() {
        return image_item;
    }

    public void setImage_item(String image_item) {
        this.image_item = image_item;
    }

    public String getPrice_item() {
        return price_item;
    }

    public void setPrice_item(String price_item) {
        this.price_item = price_item;
    }

    public String getQty_kho_item() {
        return qty_kho_item;
    }

    public void setQty_kho_item(String qty_kho_item) {
        this.qty_kho_item = qty_kho_item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<com.example.myapplication.Model.Model_Cart> getModel_Cart() {
        return Model_Cart;
    }

    public void setModel_Cart(List<com.example.myapplication.Model.Model_Cart> model_Cart) {
        Model_Cart = model_Cart;
    }
}
