package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.Adapter.SanphamPhobienAdapter;
import com.example.myapplication.Model.ProductModel;
import com.example.myapplication.R;
import com.example.myapplication.Service.ApiEcommerceShop;
import com.example.myapplication.Utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllActivity extends AppCompatActivity {

    RecyclerView rec_All;
    Toolbar toolbar;
    String category2 = null;
    SanphamPhobienAdapter sanphamPhobienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        category2 = getIntent().getStringExtra("category2");
        int cat = getIntent().getIntExtra("category2", 0);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        rec_All = findViewById(R.id.rec_show_all);
        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        rec_All.setLayoutManager(new GridLayoutManager(ShowAllActivity.this, 2));

        if (cat == 0){
            Call<ProductModel> call2 = apiEcommerceShop.getProduct();
            call2.enqueue(new Callback<ProductModel>() {
                @Override
                public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {

                    if (response.code() == 200){

                        List<ProductModel> productModelList = response.body().getProductModel();

                        sanphamPhobienAdapter = new SanphamPhobienAdapter(ShowAllActivity.this, productModelList);
                        rec_All.setAdapter(sanphamPhobienAdapter);
                        sanphamPhobienAdapter.notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(Call<ProductModel> call, Throwable t) {

                }
            });
        } else {
            Call<ProductModel> call2 = apiEcommerceShop.proCategory(cat);
            call2.enqueue(new Callback<ProductModel>() {
                @Override
                public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {

                    if (response.code() == 200){

                        List<ProductModel> productModelList = response.body().getProductModel();

                        sanphamPhobienAdapter = new SanphamPhobienAdapter(ShowAllActivity.this, productModelList);
                        rec_All.setAdapter(sanphamPhobienAdapter);
                        sanphamPhobienAdapter.notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(Call<ProductModel> call, Throwable t) {

                }
            });
        }


    }
}