package com.example.myapplication.Model;

import com.google.gson.annotations.SerializedName;

public class DangkyModel {

    @SerializedName("user_id")
    String user_id;
    @SerializedName("user_name")
    String user_name;
    @SerializedName("user_email")
    String user_email;
    @SerializedName("user_password")
    String user_password;
    @SerializedName("bd")
    String bd;

    public DangkyModel() {
    }

    public DangkyModel(String user_id, String user_email, String user_name, String user_password, String bd) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_password = user_password;
        this.bd = bd;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }
}
