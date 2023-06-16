package com.example.myapplication.Activity;

import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.UserModel;
import com.example.myapplication.R;
import com.example.myapplication.Service.ApiEcommerceShop;
import com.example.myapplication.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText taikhoan, matkhau;
    Button btnDNhap;
    TextView DKy;


    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing id.
    public static final String IDUSER_KEY = "iduser_key";
    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    public static int id_user;
    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";
    public static SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String keylogout = getIntent().getStringExtra("keylogout");
        if (keylogout == "logout"){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putInt(IDUSER_KEY, 0);
            editor.putString(EMAIL_KEY, null);
            editor.putString(PASSWORD_KEY, null);
            // to save our data with key and value.
            editor.commit();
            editor.clear();
        }


        init();
    }

    public void init(){
        taikhoan = findViewById(R.id.taikhoan);
        matkhau = findViewById(R.id.matkhau);
        btnDNhap = findViewById(R.id.btndangnhap);
        DKy = findViewById(R.id.txtdangky);

        btnDNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void signin(View view){
        String username = taikhoan.getText().toString();
        String password = matkhau.getText().toString();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "Nhap tai khoan!", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Nhap mat khau!!", Toast.LENGTH_SHORT).show();
        }
        else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

    }
    public void signup(View view){
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

    private void login(){
        String user_email = taikhoan.getText().toString().trim();
        String user_password = matkhau.getText().toString().trim();

        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<UserModel> call =apiEcommerceShop.getLogin(user_email, user_password);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.code() == 200){
//                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                    List<UserModel> ArrayUser = response.body().getUserLogin();
//                    Toast.makeText(LoginActivity.this, "Success" + ArrayUser.get(0).getId(), Toast.LENGTH_SHORT).show();
                    for (UserModel item:response.body().getUserModel()){
                        Toast.makeText(LoginActivity.this, "success"+item.getUser_id(), Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putInt(IDUSER_KEY, item.getUser_id());
                        // to save our data with key and value.
                        editor.commit();
                    }

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Fail" + response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "404"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}