package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.ItemCart_Adapter;
import com.example.myapplication.Interface.itemOnclickListenner_ItemCart;
import com.example.myapplication.Model.Model_Cart;
import com.example.myapplication.Model.Model_Load_Qty_Cart;
import com.example.myapplication.Model.Model_TotalPriceCart;
import com.example.myapplication.R;
import com.example.myapplication.Service.ApiEcommerceShop;
import com.example.myapplication.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    LinearLayout ln_noitem, nav_bottom;
    TextView tv_total_cart, tv_price_cart, tv_discount;
    Button btn_checkout;

    itemOnclickListenner_ItemCart itemOnclickListenner_itemCart;

    String tong;


    RecyclerView recyclerView_itemcart;
    ItemCart_Adapter itemCart_adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ln_noitem = findViewById(R.id.ln_noitem);
        nav_bottom = findViewById(R.id.nav_bottom);

        tv_total_cart = findViewById(R.id.tv_total_cart);
        tv_price_cart = findViewById(R.id.tv_price_cart);
        btn_checkout = findViewById(R.id.btn_checkout);

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                intent.putExtra("tong", tong);
                startActivity(intent);
            }
        });

        ImageButton backc = findViewById(R.id.backc);
        backc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        LinearLayoutManager layoutManager_top = new LinearLayoutManager(CartActivity.this);
        layoutManager_top.setOrientation(RecyclerView.VERTICAL);
        recyclerView_itemcart = findViewById(R.id.rcv_item_cart);
        recyclerView_itemcart.setLayoutManager(layoutManager_top);

        load_Iteam_cart();
        load_total();

        itemOnclickListenner_itemCart = new itemOnclickListenner_ItemCart() {
            @Override
            public void Onclickitem_update() {
                load_total();
                load_Iteam_cart();
            }
            @Override
            public void Onclickitem_delete() {
                load_Iteam_cart();
                load_total();
                LoadQty_Cart();
            }
        };
        LoadQty_Cart();
    }

    public void LoadQty_Cart(){
        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<Model_Load_Qty_Cart> callback = apiEcommerceShop.Get_Qty_Cart(MainActivity.id_user);
        callback.enqueue(new Callback<Model_Load_Qty_Cart>() {
            @Override
            public void onResponse(Call<Model_Load_Qty_Cart> call, Response<Model_Load_Qty_Cart> response) {
                if (response.isSuccessful()){
                    for(Model_Load_Qty_Cart item:response.body().getModel_Load_Qty_Cart()){
                        TextView tv_Qty_cart = findViewById(R.id.tv_Qty_cart);
                        tv_Qty_cart.setText(item.getQty_cart());
                    }
                }
            }

            @Override
            public void onFailure(Call<Model_Load_Qty_Cart> call, Throwable throwable) {
                Toast.makeText(CartActivity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void load_Iteam_cart() {
        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<Model_Cart> callback = apiEcommerceShop.LoadProductCart(MainActivity.id_user);
        callback.enqueue(new Callback<Model_Cart>() {
            @Override
            public void onResponse(Call<Model_Cart> call, Response<Model_Cart> response) {
                if (response.code() == 200) {
                    List<Model_Cart> cart = response.body().getModel_Cart();
                    itemCart_adapter = new ItemCart_Adapter(cart, CartActivity.this, itemOnclickListenner_itemCart);
                    recyclerView_itemcart.setAdapter(itemCart_adapter);
                    itemCart_adapter.notifyDataSetChanged();

                    ln_noitem.setVisibility(View.GONE);
                    nav_bottom.setVisibility(View.VISIBLE);

                }else{
                    ln_noitem.setVisibility(View.VISIBLE);
                    nav_bottom.setVisibility(View.GONE);


                }
            }

            @Override
            public void onFailure(Call<Model_Cart> call, Throwable throwable) {
                Toast.makeText(CartActivity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void load_total() {
        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<Model_TotalPriceCart> callback = apiEcommerceShop.Get_Total_Cart(MainActivity.id_user);
        callback.enqueue(new Callback<Model_TotalPriceCart>() {
            @Override
            public void onResponse(Call<Model_TotalPriceCart> call, Response<Model_TotalPriceCart> response) {
                if (response.isSuccessful()) {
                    for(Model_TotalPriceCart item:response.body().getModel_TotalPriceCart()){
                        if (item.getTotal() == "0"){
                            tv_discount.setVisibility(View.GONE);
                        }
                        tv_total_cart.setText(item.getTotal());
//                        Double price = Integer.parseInt(item.getTotal()) * 0.67;
//                        Double abc = (double) Math.round(price * 10) / 10;
                        tv_price_cart.setText("Tổng tiền: "+item.getTotal());
                        tong = item.getTotal();

                    }

                }
            }

            @Override
            public void onFailure(Call<Model_TotalPriceCart> call, Throwable throwable) {
                Toast.makeText(CartActivity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }
}