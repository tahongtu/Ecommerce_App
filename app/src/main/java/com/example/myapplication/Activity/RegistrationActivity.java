package com.example.myapplication.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.DangkyModel;
import com.example.myapplication.R;
import com.example.myapplication.Service.ApiEcommerceShop;
import com.example.myapplication.Utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    EditText hoten, matkhau2, taikhoan, matkhau, ngaysinh;
    Button btnDKy;
    TextView DNhap;
    final Calendar myCalendar = Calendar.getInstance();

    ApiEcommerceShop apiEcommerceShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();
        setNgaysinh();

    }

    private void dangky() {
//        StringRequest request = new StringRequest(Request.Method.POST, Constant.REGISTER, response -> {
//
//            try {
//                JSONObject object = new JSONObject(response);
//                if (object.getBoolean("success")){
//                    JSONObject user = object.getJSONObject("user_name");
//                    SharedPreferences userPref = getApplicationContext().getSharedPreferences("user_name", getApplicationContext().MODE_PRIVATE);
//                    SharedPreferences.Editor editor = userPref.edit();
////                    editor.putString("")
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }, error -> {
//            error.printStackTrace();
//        }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return super.getParams();
//            }
//        };
//
//        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//        queue.add(request);

        String user_email = taikhoan.getText().toString().trim();
        String user_name = hoten.getText().toString().trim();
        String user_password = matkhau.getText().toString().trim();
        String bd = ngaysinh.getText().toString().trim();

        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<Void> call =apiEcommerceShop.dangky(user_email, user_password, user_name, bd);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    Toast.makeText(RegistrationActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
//                    finish();
                }
                else {
                    Toast.makeText(RegistrationActivity.this, "Fail" + response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, "404"+t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void init(){
        matkhau2 = findViewById(R.id.matkhau2);
        hoten = findViewById(R.id.hoten);
        taikhoan = findViewById(R.id.taikhoan);
        matkhau = findViewById(R.id.matkhau);
        ngaysinh = findViewById(R.id.ngaysinh);
        DNhap = findViewById(R.id.txtdangnhap);
        btnDKy = findViewById(R.id.btndangky);

        btnDKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangky();
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
                new DatePickerDialog(RegistrationActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(){
        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        ngaysinh.setText(dateFormat.format(myCalendar.getTime()));
    }


    public void signup(View view){
        String username = taikhoan.getText().toString();
        String password = matkhau.getText().toString();
        String repassword = matkhau2.getText().toString();
        String fullname = hoten.getText().toString();
        String ngsinh = ngaysinh.getText().toString();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "Nhap tai khoan!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Nhap mat khau!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(repassword)){
            Toast.makeText(this, "Nhap Email cua ban!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(fullname)){
            Toast.makeText(this, "Nhap ho ten!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(ngsinh)){
            Toast.makeText(this, "Nhap ngay sinh!!", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
        }

    }
    public void signin(View view){
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }
}