package com.example.myapplication.Model;

import java.util.ArrayList;
import java.util.List;

public class DanhmucModel {
   int id_category2;
   String name2;

   List<DanhmucModel> DanhmucModel;

    public DanhmucModel() {
    }

    public DanhmucModel(int id_category2, String name2) {
        this.id_category2 = id_category2;
        this.name2 = name2;
    }

    public int getId_category2() {
        return id_category2;
    }

    public void setId_category2(int id_category2) {
        this.id_category2 = id_category2;
    }



    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public List<com.example.myapplication.Model.DanhmucModel> getDanhmucModel() {
        return DanhmucModel;
    }

    public void setDanhmucModel(List<com.example.myapplication.Model.DanhmucModel> danhmucModel) {
        DanhmucModel = danhmucModel;
    }
}
