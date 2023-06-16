package com.example.myapplication.Model;

import java.io.Serializable;
import java.util.List;

public class ProductModel implements Serializable {
    int id_product;
    int id_category2;
    String nameproduct, image, information;
    int quantity, price, proprice, sell;

    List<ProductModel> ProductModel;

    public ProductModel() {
    }

    public ProductModel(int id_product, int id_category2, String nameproduct, String image, String information, int quantity, int price, int proprice, int sell) {
        this.id_product = id_product;
        this.id_category2 = id_category2;
        this.nameproduct = nameproduct;
        this.image = image;
        this.information = information;
        this.quantity = quantity;
        this.price = price;
        this.proprice = proprice;
        this.sell = sell;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getId_category2() {
        return id_category2;
    }

    public void setId_category2(int id_category2) {
        this.id_category2 = id_category2;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProprice() {
        return proprice;
    }

    public void setProprice(int proprice) {
        this.proprice = proprice;
    }

    public int getSell() {
        return sell;
    }

    public void setSell(int sell) {
        this.sell = sell;
    }

    public List<com.example.myapplication.Model.ProductModel> getProductModel() {
        return ProductModel;
    }

    public void setProductModel(List<com.example.myapplication.Model.ProductModel> productModel) {
        ProductModel = productModel;
    }
}
