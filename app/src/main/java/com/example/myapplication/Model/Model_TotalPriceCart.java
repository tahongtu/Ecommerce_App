package com.example.myapplication.Model;

import java.util.List;

public class Model_TotalPriceCart {
    String total;
    List<Model_TotalPriceCart> Model_TotalPriceCart;

    public Model_TotalPriceCart(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<com.example.myapplication.Model.Model_TotalPriceCart> getModel_TotalPriceCart() {
        return Model_TotalPriceCart;
    }

    public void setModel_TotalPriceCart(List<com.example.myapplication.Model.Model_TotalPriceCart> model_TotalPriceCart) {
        Model_TotalPriceCart = model_TotalPriceCart;
    }
}
