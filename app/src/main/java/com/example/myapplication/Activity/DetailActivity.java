package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.Adapter.Adapter_Size_Product;
import com.example.myapplication.Adapter.Adapter_Type_Product;
import com.example.myapplication.Adapter.BinhluanAdapter;
import com.example.myapplication.Adapter.DanhmucAdapter;
import com.example.myapplication.Interface.ItemClicklistener_Size;
import com.example.myapplication.Interface.ItemClicklistener_Type;
import com.example.myapplication.Model.BinhluanModel;
import com.example.myapplication.Model.DanhmucModel;
import com.example.myapplication.Model.ModelSize_Pro;
import com.example.myapplication.Model.ModelType_Pro;
import com.example.myapplication.Model.Model_Load_PriceChoose;
import com.example.myapplication.Model.ProductModel;
import com.example.myapplication.R;
import com.example.myapplication.Service.ApiEcommerceShop;
import com.example.myapplication.Utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    public String  qty_kho_choose;
    int id_product, id_details;
    int price_asd;
    /////////////////// khai bao cho bottom add to cart
    private TextView price_product, nametype_chosse, qtykho, name_type, name_size,tv_qty;
    private ImageView image_pro_bt;
    private LinearLayout LN_type_product, LN_size_product;
    private RecyclerView rcv_type, rcv_size;
    private ImageButton btn_up, btn_dow;
    View view1, view2;
    NestedScrollView bottom2;
    public ApiEcommerceShop apiEcommerceShop = Utils.getService();
    String nametype_choose = "", namsize_choose, price_pro_chooose;
    int id_type = 0, id_size = 0;
    ////////// rcv type and size product ////////////
    RecyclerView recyclerView_type;
    Adapter_Type_Product adapter_type_product ;
    List<ModelType_Pro> modelType_pros;

    RecyclerView recyclerView_Size;
    Adapter_Size_Product adapter_size_product ;
    List<ModelSize_Pro> modelSize_pros;

    ItemClicklistener_Type ItemClicklistener_type;
    ItemClicklistener_Size ItemClicklistener_size;
    ///////////////////////////////////////////
    ImageView img_spdetail;
    TextView tensp, mota, giamoi, giacu, soluong;
    LinearLayout themgiohang, muahang;
    ImageView additem, removeitem;
    Toolbar toolbar;



    ProductModel productModel;

    BinhluanAdapter binhluanAdapter;
    List<BinhluanModel> binhluanModelList;

    RecyclerView rec_binhluan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        anhxa();
        setDetail();
        getComment();



    }

    public void anhxa(){
        img_spdetail = findViewById(R.id.detail_img);
        tensp = findViewById(R.id.detail_name);

        mota = findViewById(R.id.detail_info);
        giamoi = findViewById(R.id.newPrice);
        giacu = findViewById(R.id.oldPrice);
        giacu.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        themgiohang = findViewById(R.id.themgiohang);
        muahang = findViewById(R.id.muahang);

        rec_binhluan = findViewById(R.id.rec_binhluan);
        rec_binhluan.setLayoutManager(new LinearLayoutManager(DetailActivity.this, RecyclerView.VERTICAL, false));
        themgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickBuyOrAddtocart(0);
            }
        });
        muahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickBuyOrAddtocart(1);
            }
        });

    }

    private void ClickBuyOrAddtocart(int i) {
        id_type=0;
        id_size=0;


        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(DetailActivity.this, R.style.BottomsheetTheme);
        view2 = LayoutInflater.from(DetailActivity.this).inflate(R.layout.bottom_add_buy_product, bottom2);
        bottomSheetDialog.setContentView(view2);



        image_pro_bt = view2.findViewById(R.id.image_pro);
        price_product = view2.findViewById(R.id.price_product);
        qtykho = view2.findViewById(R.id.qtykho);

        LN_type_product = view2.findViewById(R.id.type_product);
        name_type = view2.findViewById(R.id.name_type);
        rcv_type = view2.findViewById(R.id.rcv_type);

        LN_size_product = view2.findViewById(R.id.size_product);
        name_size = view2.findViewById(R.id.name_size);
        rcv_size = view2.findViewById(R.id.rcv_size);

        btn_up = view2.findViewById(R.id.btn_up);
        tv_qty = view2.findViewById(R.id.tv_qty);
        btn_dow = view2.findViewById(R.id.btn_dow);
        tv_qty.setText("1");

        qtykho.setText("Kho: 0" );
        btn_dow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int abc = Integer.parseInt(tv_qty.getText().toString()) - 1;
                if(Integer.parseInt(tv_qty.getText().toString()) > 1){
                    tv_qty.setText(String.valueOf(abc));
                }

            }
        });
        btn_up.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Integer.parseInt(tv_qty.getText().toString()) < Integer.parseInt(qty_kho_choose)){
                    int abc = Integer.parseInt(tv_qty.getText().toString()) + 1;
                    tv_qty.setText(String.valueOf(abc));
                }else{
                    Toast.makeText(DetailActivity.this, "out of stock" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        ////////////rcv //////////////

        Button btn_add_toCart = view2.findViewById(R.id.btn_add_toCart);

        btn_add_toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(qty_kho_choose) == 0){
                    Toast.makeText(DetailActivity.this, "out of stock " , Toast.LENGTH_SHORT).show();
                }else{
                    if( id_type == 0 || id_size == 0){
                        Toast.makeText(DetailActivity.this, "Please choose type and size product" , Toast.LENGTH_SHORT).show();
                    }else{
                        if(i == 0){
                                saveadd_tocart(MainActivity.id_user, id_product, id_details, Integer.parseInt(tv_qty.getText().toString()));
                            bottomSheetDialog.dismiss();
                        }else if(i==1){
                            Toast.makeText(DetailActivity.this, " Buy success " , Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.dismiss();
//                                BuyProduct();

                        }
                    }
                }

            }
        });

        ////////////////// type////////////////////////
        GridLayoutManager layoutManager1 = new GridLayoutManager(view2.getContext(), 4);
        recyclerView_type = view2.findViewById(R.id.rcv_type);
        recyclerView_type.setLayoutManager(layoutManager1);


        ////////////////// size ////////////////////////
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(view2.getContext());
        layoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView_Size = view2.findViewById(R.id.rcv_size);
        recyclerView_Size.setLayoutManager(layoutManager2);

        /////////////////// /////////////////////////


            LN_type_product.setVisibility(View.VISIBLE);
            LN_size_product.setVisibility(View.VISIBLE);
            load_size_product();
            load_type_product();


        getDetails_product_bottoms();
        bottomSheetDialog.show();

    }

    private void saveadd_tocart(int id_user, int id_pro, int id_details, int qty) {
        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<Void> call =apiEcommerceShop.Add_to_cart(id_user, id_pro, id_details, qty,price_asd);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    Toast.makeText(DetailActivity.this, "add Success", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(DetailActivity.this, "Fail" + response.body(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "404"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void load_type_product(){

        Call<ModelType_Pro> callback = apiEcommerceShop.LoadTypeProduct(id_product);
        callback.enqueue(new Callback<ModelType_Pro>() {
            @Override
            public void onResponse(Call<ModelType_Pro> call, Response<ModelType_Pro> response) {
                if (response.isSuccessful()){
                    List<ModelType_Pro> modelType = response.body().getModelType_Pro();
                    adapter_type_product = new Adapter_Type_Product(modelType, view2.getContext(), ItemClicklistener_type);
                    recyclerView_type.setAdapter(adapter_type_product);
                    adapter_type_product.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ModelType_Pro> call, Throwable throwable) {
                Toast.makeText(DetailActivity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });


        ItemClicklistener_type = new ItemClicklistener_Type() {
            @Override
            public void Onclick_Type(int id_detail, int id,String name, String Qty_pro) {
                recyclerView_type.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter_type_product.notifyDataSetChanged();
                    }
                });

                id_type = id;
                qtykho.setText("Kho: "+Qty_pro);
                nametype_choose = name;
                qty_kho_choose = Qty_pro;
                id_details = id_detail;

//                Toast.makeText(view2.getContext(), "id_type" + id, Toast.LENGTH_SHORT).show();
            }

        };

    }
    public void load_size_product(){
        Call<ModelSize_Pro> callback = apiEcommerceShop.LoadSizeProduct(id_product);
        callback.enqueue(new Callback<ModelSize_Pro>() {
            @Override
            public void onResponse(Call<ModelSize_Pro> call, Response<ModelSize_Pro> response) {
                if (response.isSuccessful()){
                    List<ModelSize_Pro> modelSize = response.body().getModelSize_Pro();
                    adapter_size_product = new Adapter_Size_Product(modelSize, view2.getContext(), ItemClicklistener_size);
                    recyclerView_Size.setAdapter(adapter_size_product);
                    adapter_size_product.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModelSize_Pro> call, Throwable throwable) {
                Toast.makeText(DetailActivity.this, "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });


        ItemClicklistener_size = new ItemClicklistener_Size() {
            @Override
            public void Onclick_Size(int id ,String name, String Qty_pro) {
                if(id_type == 0){
                    Toast.makeText(view2.getContext(), "Please Choose Type Product", Toast.LENGTH_SHORT).show();
                }else{

                    recyclerView_Size.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter_size_product.notifyDataSetChanged();
                        }
                    });
                    id_size =id;
                    qtykho.setText("Kho: "+Qty_pro);
                    namsize_choose = name;
                    qty_kho_choose = Qty_pro;

                }
            }
        };

    }




    public void setDetail(){
        productModel = (ProductModel) getIntent().getSerializableExtra("detail");
        id_product = productModel.getId_product();
        DecimalFormat df = new DecimalFormat("#,###,###");

        Glide.with(getApplicationContext()).load(Utils.linkImg + productModel.getImage()).into(img_spdetail);
        tensp.setText(productModel.getNameproduct());
        mota.setText(productModel.getInformation());
        giamoi.setText(String.valueOf(df.format(productModel.getProprice())) + "đ");
        price_asd = productModel.getProprice();
        giacu.setText(String.valueOf(df.format(productModel.getPrice())) +"đ");
    }
    private void getDetails_product_bottoms() {
        Glide.with(getApplicationContext()).load(Utils.linkImg + productModel.getImage()).into(image_pro_bt);

        tensp.setText(productModel.getNameproduct());
        mota.setText(productModel.getInformation());

        DecimalFormat df = new DecimalFormat("#,###,###");
        price_product.setText(String.valueOf(df.format(productModel.getProprice())) + "đ");

//            name_type.setText(title_type);


    }

    public void getComment(){

        binhluanModelList = new ArrayList<>();

        apiEcommerceShop = Utils.getService();
        Call<BinhluanModel> call = apiEcommerceShop.getComment(productModel.getId_product());
        call.enqueue(new Callback<BinhluanModel>() {
            @Override
            public void onResponse(Call<BinhluanModel> call, Response<BinhluanModel> response) {
                if (response.code() == 200){

                    List<BinhluanModel>  binhluanModelList = response.body().getBinhluanModel();



                    binhluanAdapter = new BinhluanAdapter(DetailActivity.this, binhluanModelList);
                    rec_binhluan.setAdapter(binhluanAdapter);
                    binhluanAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<BinhluanModel> call, Throwable t) {

            }
        });

    }



}