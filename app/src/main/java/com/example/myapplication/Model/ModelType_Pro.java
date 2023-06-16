package com.example.myapplication.Model;

import java.util.List;

public class ModelType_Pro {
    int detail_id, id_product, color_id;
    String color, detail_qty;

    List<ModelType_Pro> ModelType_Pro;

    public ModelType_Pro(int detail_id, int id_product, int color_id, String color, String detail_qty) {
        this.detail_id = detail_id;
        this.id_product = id_product;
        this.color_id = color_id;
        this.color = color;
        this.detail_qty = detail_qty;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDetail_qty() {
        return detail_qty;
    }

    public void setDetail_qty(String detail_qty) {
        this.detail_qty = detail_qty;
    }

    public List<com.example.myapplication.Model.ModelType_Pro> getModelType_Pro() {
        return ModelType_Pro;
    }

    public void setModelType_Pro(List<com.example.myapplication.Model.ModelType_Pro> modelType_Pro) {
        ModelType_Pro = modelType_Pro;
    }
}
