package com.example.myapplication.Utils;

import com.example.myapplication.Service.ApiEcommerceShop;
import com.example.myapplication.Service.RetrofitClient;

public class Utils {

    public static final String linkImg = "http://172.20.10.3:8000/uploads/";

    public static final String URL = "http://172.20.10.3:8000/api/";
//    public static final String HOME = URL+"/api";
//    public static final String LOGIN = HOME + "/login";
//    public static final String REGISTER = HOME .+"/register";

    public static ApiEcommerceShop getService(){
        return RetrofitClient.getInstance(URL).create(ApiEcommerceShop.class);
    }


}
