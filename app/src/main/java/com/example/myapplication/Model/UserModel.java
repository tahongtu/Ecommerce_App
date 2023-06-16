package com.example.myapplication.Model;

import java.util.List;

public class UserModel {
    int user_id;
    String user_name;
    String bd;
    String sex;
    String phone;
    String gmail;

    List<UserModel> UserModel;

    public UserModel() {
    }

    public UserModel(int user_id, String user_name, String bd, String sex, String phone, String gmail) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.bd = bd;
        this.sex = sex;
        this.phone = phone;
        this.gmail = gmail;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public List<com.example.myapplication.Model.UserModel> getUserModel() {
        return UserModel;
    }

    public void setUserModel(List<com.example.myapplication.Model.UserModel> userModel) {
        UserModel = userModel;
    }
}
