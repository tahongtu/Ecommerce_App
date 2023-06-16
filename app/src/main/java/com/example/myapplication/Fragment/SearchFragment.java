package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapter.SanphamMoiAdapter;
import com.example.myapplication.Adapter.SanphamPhobienAdapter;
import com.example.myapplication.Model.ProductModel;
import com.example.myapplication.R;
import com.example.myapplication.Service.ApiEcommerceShop;
import com.example.myapplication.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {



    public SearchFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView_pro_all;
    SanphamMoiAdapter sanphamMoiAdapter;
    SanphamPhobienAdapter sanphamPhobienAdapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_search, container, false);

        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(), 2);
        recyclerView_pro_all = view.findViewById(R.id.rcv_Search_Product);
        recyclerView_pro_all.setLayoutManager(layoutManager2);

        SearchView sv = (SearchView) view.findViewById(R.id.svpro);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query.trim())){
                    get_product_all(query);
                }else{
                    get_product_all("all");
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                if (!TextUtils.isEmpty(query.trim())){
                    get_product_all(query);
                }else{

                    get_product_all("all");
                }
                return false;
            }
        });
        get_product_all("all");
        return view;
    }

    public void get_product_all(String key){

        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<ProductModel> call1 = apiEcommerceShop.Search_Product(key);
        call1.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {

                if (response.code() == 200){
                    List<ProductModel>  productModelList = response.body().getProductModel();

                    sanphamPhobienAdapter = new SanphamPhobienAdapter(getContext(), productModelList);
                    recyclerView_pro_all.setAdapter(sanphamPhobienAdapter);
                    sanphamPhobienAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {

            }
        });
    }

    public void reload(){
        get_product_all("all");
    }
}