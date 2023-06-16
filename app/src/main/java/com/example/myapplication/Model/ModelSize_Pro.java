package com.example.myapplication.Model;

import java.util.List;

public class ModelSize_Pro {
    int id_product, size_id;
    String size, detail_qty;
    List<ModelSize_Pro> ModelSize_Pro;

    public ModelSize_Pro(int id_product, int size_id, String size, String detail_qty) {
        this.id_product = id_product;
        this.size_id = size_id;
        this.size = size;
        this.detail_qty = detail_qty;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDetail_qty() {
        return detail_qty;
    }

    public void setDetail_qty(String detail_qty) {
        this.detail_qty = detail_qty;
    }

    public List<com.example.myapplication.Model.ModelSize_Pro> getModelSize_Pro() {
        return ModelSize_Pro;
    }

    public void setModelSize_Pro(List<com.example.myapplication.Model.ModelSize_Pro> modelSize_Pro) {
        ModelSize_Pro = modelSize_Pro;
    }
}
