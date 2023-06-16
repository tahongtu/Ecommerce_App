package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.Item_checkout_Adapter;
import com.example.myapplication.Model.Model_Cart;
import com.example.myapplication.R;
import com.example.myapplication.Service.ApiEcommerceShop;
import com.example.myapplication.Utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {

    RecyclerView rec_checkout;

    RadioButton radioButton, radioButton1, radioButton2;
    RadioGroup radioGroup;
    String method_pay="";
    int id_ship_cf = 0;

    ProgressDialog pd;

    TextView tv_qty_item, tv_total, tv_discount;
    Button btn_checkout;

    String tong;
    Item_checkout_Adapter item_checkout_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        init();
        pd = new ProgressDialog(this);

         tong = getIntent().getStringExtra("tong");
        tv_total.setText(tong);
        tv_discount.setText(tong);

       // LoadTotal_checkout();
        LoadItemCart_Checkout();


        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);

            // Gắn sự kiện OnClickListener cho mỗi RadioButton

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int text = ((RadioButton)view).getId();
//                    Toast.makeText(CheckoutActivity.this, "" +text, Toast.LENGTH_SHORT).show();
                    if (text ==2131296885){

//                        Toast.makeText(CheckoutActivity.this, "1:" +text, Toast.LENGTH_SHORT).show();
                        method_pay = "Cash on Delivery";
                    }else if (text ==2131296886){

                        method_pay = "Momo";
                    }else if (text ==2131296887){
                        method_pay = "Credit / Debit Card";
                    }

//                    Toast.makeText(Checkout_Activity.this, ":" +text, Toast.LENGTH_SHORT).show();
//                    method_pay =  ((RadioButton)view).getText().toString();
                }
            });
        }
    }
    private void LoadItemCart_Checkout() {
        LinearLayoutManager layoutManager_top = new LinearLayoutManager(CheckoutActivity.this);
        layoutManager_top.setOrientation(RecyclerView.VERTICAL);
        rec_checkout.setLayoutManager(layoutManager_top);

        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<Model_Cart> callback = apiEcommerceShop.LoadItem_Checkout(MainActivity.id_user);
        callback.enqueue(new Callback<Model_Cart>() {
            @Override
            public void onResponse(Call<Model_Cart> call, Response<Model_Cart> response) {
                if (response.code() == 200) {
                    List<Model_Cart> cart = response.body().getModel_Cart();
                    item_checkout_adapter = new Item_checkout_Adapter(cart, CheckoutActivity.this);
                    rec_checkout.setAdapter(item_checkout_adapter);
                    item_checkout_adapter.notifyDataSetChanged();
                    int i=0;
                    for(Model_Cart item:response.body().getModel_Cart()){
                        i++;
                    }
                    tv_qty_item.setText(i+" mặt hàng");
                }
            }


            @Override
            public void onFailure(Call<Model_Cart> call, Throwable throwable) {
                Toast.makeText(CheckoutActivity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ChevkoutItemCart() {
        pd.show();
        pd.setMessage("Paying ...");
        Double Pt_dis = 0.33;
        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<Void> callback = apiEcommerceShop.ChekoutItemCart(MainActivity.id_user, method_pay);
        callback.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(CheckoutActivity.this, "Checkout Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CheckoutActivity.this, Checkout_Success_Activity.class);
                    startActivity(intent);
                    finish();
                    pd.dismiss();

                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Toast.makeText(CheckoutActivity.this, "failll" + throwable, Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }


    public void init(){
        rec_checkout = findViewById(R.id.rcv_item_checkout);

        radioGroup = findViewById(R.id.radioGroup);
        radioButton = findViewById(R.id.radioButton);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);

        tv_qty_item = findViewById(R.id.tv_qty_item);
        tv_total = findViewById(R.id.tv_total);
        tv_discount = findViewById(R.id.tv);
        btn_checkout = findViewById(R.id.btn_checkout);

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(method_pay =="") {
                    Toast.makeText(CheckoutActivity.this, "PleaseChoose Method Payment", Toast.LENGTH_SHORT).show();
                }else{
                        ChevkoutItemCart();
                }

            }
        });

    }
}