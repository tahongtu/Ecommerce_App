package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.Model.UserModel;
import com.example.myapplication.R;
import com.example.myapplication.Service.ApiEcommerceShop;
import com.example.myapplication.Utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    EditText ten, ngaysinh, gmail, sdt;
    RadioButton nam, nu;
    RadioGroup rg;
    Button btnUpdate;
    Toolbar toolbar;

    String sex;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        init();
        setNgaysinh();
        getUserInfor();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateUser();
            }
        });


    }

    public void init(){
        ten = findViewById(R.id.tenUpdate);
        ngaysinh = findViewById(R.id.bdUpdate);
        nam = findViewById(R.id.namcheck);
        nu = findViewById(R.id.nucheck);
        gmail = findViewById(R.id.gmailUpdate);
        sdt = findViewById(R.id.sdtUpdate);
        rg = findViewById(R.id.radiogroup);
        btnUpdate = findViewById(R.id.btnOke);

    }

    public void getUserInfor(){

        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<UserModel> call =apiEcommerceShop.getUser(MainActivity.id_user);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.code() == 200){
                    List<UserModel> userModels = response.body().getUserModel();
                    ten.setText(userModels.get(0).getUser_name());
                    ngaysinh.setText(userModels.get(0).getBd());
                    sex = userModels.get(0).getSex();
                    gmail.setText(userModels.get(0).getGmail());
                    sdt.setText(userModels.get(0).getPhone());
                }
                else {
                    Toast.makeText(UpdateActivity.this, "Fail" + response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "404"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void UpdateUser(){
        String user_name = ten.getText().toString().trim();
        String bd = ngaysinh.getText().toString().trim();
        if (nam.isChecked()){
            sex = nam.getText().toString();

        } else if (nu.isChecked()) {
            sex = nu.getText().toString();
        }

        String mail = gmail.getText().toString().trim();
        String phone = sdt.getText().toString().trim();

        Toast.makeText(this, sex, Toast.LENGTH_SHORT).show();

        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<Void> call =apiEcommerceShop.UpdateUser(MainActivity.id_user, user_name, bd, sex, phone, mail);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(UpdateActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(UpdateActivity.this, "Fail" + response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "404"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setNgaysinh(){
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        ngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UpdateActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(){
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        ngaysinh.setText(dateFormat.format(myCalendar.getTime()));
    }
}