package com.example.myapplication.Model;

import java.util.List;

public class BinhluanModel {
    String image;
    String user_name;
    float star;
    String date_comment;
    String comment;

    List<BinhluanModel> BinhluanModel;

    public BinhluanModel() {
    }

    public BinhluanModel(String image, String user_name, float star, String date_comment, String comment) {
        this.image = image;
        this.user_name = user_name;
        this.star = star;
        this.date_comment = date_comment;
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public String getDate_comment() {
        return date_comment;
    }

    public void setDate_comment(String date_comment) {
        this.date_comment = date_comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<com.example.myapplication.Model.BinhluanModel> getBinhluanModel() {
        return BinhluanModel;
    }

    public void setBinhluanModel(List<com.example.myapplication.Model.BinhluanModel> binhluanModel) {
        BinhluanModel = binhluanModel;
    }
}
