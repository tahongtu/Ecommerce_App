package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.myapplication.Activity.CartActivity;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Activity.ShowAllActivity;
import com.example.myapplication.Adapter.DanhmucAdapter;
import com.example.myapplication.Adapter.SanphamMoiAdapter;
import com.example.myapplication.Adapter.SanphamPhobienAdapter;
import com.example.myapplication.Model.DanhmucModel;
import com.example.myapplication.Model.Model_Load_Qty_Cart;
import com.example.myapplication.Model.ProductModel;
import com.example.myapplication.R;
import com.example.myapplication.Service.ApiEcommerceShop;
import com.example.myapplication.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    View view;
    ViewFlipper viewFlipper;
    RecyclerView danhmuc, spmoi, spphobien;
    TextView  spmoiAll, spphobienAll;

    //danhmuc recyclerview
    DanhmucAdapter danhmucAdapter;
    List<DanhmucModel> danhmucModelList;

    //Sanphammoi
    SanphamMoiAdapter sanphamMoiAdapter;
    List<ProductModel> productModelList;

    //SanphamPhobien
    SanphamPhobienAdapter sanphamPhobienAdapter;
    ApiEcommerceShop apiEcommerceShop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        anhxa();
        ActionViewFlipper();
        ShowAll();

        ImageView cart = view.findViewById(R.id.carts);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getContext(), CartActivity.class);
                startActivity(intent2);
            }
        });
        LoadQty_Cart(view);
        return view;
    }

    public void ActionViewFlipper(){
        int image[] = {
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3
        };

        for (int i=0; i<image.length; i++){
            ImageView imageView = new ImageView(this.getContext());
            imageView.setImageResource(image[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

    }
    public void anhxa(){



        viewFlipper = view.findViewById(R.id.img_slide);
        //Danhmuc
        danhmuc = view.findViewById(R.id.rec_danhmuc);
        danhmuc.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        danhmucModelList = new ArrayList<>();

        apiEcommerceShop = Utils.getService();
        Call<DanhmucModel> call = apiEcommerceShop.getDanhmuc();
        call.enqueue(new Callback<DanhmucModel>() {
            @Override
            public void onResponse(Call<DanhmucModel> call, Response<DanhmucModel> response) {
                if (response.code() == 200){

                    danhmucModelList = response.body().getDanhmucModel();
//
//                    for (DanhmucModel item:response.body().getDanhmucModel()){
//                        Toast.makeText(getContext(), "success"+item.getName2(), Toast.LENGTH_SHORT).show();
//                    }


                    danhmucAdapter = new DanhmucAdapter(getContext(), danhmucModelList);
                    danhmuc.setAdapter(danhmucAdapter);
                    danhmucAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DanhmucModel> call, Throwable t) {

            }
        });

        //Sanphammoi

        spmoi = view.findViewById(R.id.spmoi_rec);
        spmoi.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        productModelList = new ArrayList<>();
        Call<ProductModel> call1 = apiEcommerceShop.getProduct();
        call1.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {

                if (response.code() == 200){
                    productModelList = response.body().getProductModel();

                    sanphamMoiAdapter = new SanphamMoiAdapter(getContext(), productModelList);
                    spmoi.setAdapter(sanphamMoiAdapter);
                    sanphamMoiAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {

            }
        });

        //SanphamPhobien
        spphobien = view.findViewById(R.id.spphobien_rec);
        spphobien.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        Call<ProductModel> call2 = apiEcommerceShop.getProduct();
        call2.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {

                if (response.code() == 200){
                    productModelList = response.body().getProductModel();

                    sanphamPhobienAdapter = new SanphamPhobienAdapter(getContext(), productModelList);
                    spphobien.setAdapter(sanphamPhobienAdapter);
                    sanphamPhobienAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {

            }
        });


        spmoiAll = view.findViewById(R.id.spmoiAll);
        spphobienAll = view.findViewById(R.id.spphobienAll);

    }
    public void LoadQty_Cart(View view){
        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<Model_Load_Qty_Cart> callback = apiEcommerceShop.Get_Qty_Cart(MainActivity.id_user);
        callback.enqueue(new Callback<Model_Load_Qty_Cart>() {
            @Override
            public void onResponse(Call<Model_Load_Qty_Cart> call, Response<Model_Load_Qty_Cart> response) {
                if (response.isSuccessful()){
                    for(Model_Load_Qty_Cart item:response.body().getModel_Load_Qty_Cart()){
                        TextView tv_Qty_cart = view.findViewById(R.id.tv_Qty_cart);
                        tv_Qty_cart.setText(item.getQty_cart());
                    }
                }
            }

            @Override
            public void onFailure(Call<Model_Load_Qty_Cart> call, Throwable throwable) {
                Toast.makeText(getContext(), "OnFailure" + throwable, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void ShowAll(){


        spmoiAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowAllActivity.class);
                startActivity(intent);
            }
        });

        spphobienAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowAllActivity.class);
                startActivity(intent);
            }
        });
    }
}