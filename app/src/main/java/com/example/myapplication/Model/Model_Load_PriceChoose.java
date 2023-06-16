package com.example.myapplication.Model;

import java.util.List;

public class Model_Load_PriceChoose {
    String pice_pro, qty_kho;
    List<Model_Load_PriceChoose> Model_Load_PriceChoose;

    public Model_Load_PriceChoose(String pice_pro, String qty_kho) {
        this.pice_pro = pice_pro;
        this.qty_kho = qty_kho;
    }

    public String getPice_pro() {
        return pice_pro;
    }

    public void setPice_pro(String pice_pro) {
        this.pice_pro = pice_pro;
    }

    public String getQty_kho() {
        return qty_kho;
    }

    public void setQty_kho(String qty_kho) {
        this.qty_kho = qty_kho;
    }

    public List<com.example.myapplication.Model.Model_Load_PriceChoose> getModel_Load_PriceChoose() {
        return Model_Load_PriceChoose;
    }

    public void setModel_Load_PriceChoose(List<com.example.myapplication.Model.Model_Load_PriceChoose> model_Load_PriceChoose) {
        Model_Load_PriceChoose = model_Load_PriceChoose;
    }
}
