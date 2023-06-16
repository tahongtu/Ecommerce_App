package com.example.myapplication.Service;

import android.database.Observable;

import com.example.myapplication.Model.BinhluanModel;
import com.example.myapplication.Model.DangkyModel;
import com.example.myapplication.Model.DanhmucModel;
import com.example.myapplication.Model.ModelSize_Pro;
import com.example.myapplication.Model.ModelType_Pro;
import com.example.myapplication.Model.Model_Cart;
import com.example.myapplication.Model.Model_Load_PriceChoose;
import com.example.myapplication.Model.Model_Load_Qty_Cart;
import com.example.myapplication.Model.Model_TotalPriceCart;
import com.example.myapplication.Model.ProductModel;
import com.example.myapplication.Model.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiEcommerceShop {

    @POST("register")
    Call<Void> dangky(
            @Query("user_email") String user_email,
            @Query("user_password") String user_password,
            @Query("user_name") String user_name,
            @Query("bd") String bd
    );

    @POST("login")
    Call<UserModel> getLogin(
            @Query("user_email") String user_email,
            @Query("user_password") String user_password
    );

    @GET("category2")
    Call<DanhmucModel> getDanhmuc();

    @GET("product")
    Call<ProductModel> getProduct();

    @GET("getComment")
    Call<BinhluanModel> getComment( @Query("product_id") int id_product);


    @GET("LoadTypeProduct")
    Call<ModelType_Pro> LoadTypeProduct(@Query("id_product") int id_product);
    @GET("LoadSizeProduct")
    Call<ModelSize_Pro> LoadSizeProduct(@Query("id_product") int id_product);



    @POST("Search_Product")
    Call<ProductModel> Search_Product(@Query("key_search") String key_search);

    @POST("Add_to_cart")
    Call<Void> Add_to_cart(@Query("user_id") int user_id,
                            @Query("product_id") int product_id,
                            @Query("detail_product_id") int detail_product_id,
                            @Query("cart_qty") int cart_qty,
                           @Query("cart_price") int cart_price);


    @GET("LoadProductCart")
    Call<Model_Cart> LoadProductCart(@Query("id_user") int id_user);

    @POST("Update_checkItemCart")
    Call<Void> Update_checkItemCart(@Query("id_cart") int id_cart, @Query("checkedd") int checkedd);


    @POST("Update_QtyItemCart")
    Call<Void> Update_QtyItemCart(@Query("id_cart") int id_cart, @Query("qty_item") int qty_item);

    @POST("Clear_IteamCart")
    Call<Void> Clear_IteamCart(@Query("id_cart") int id_cart);
    @GET("Get_Qty_Cart")
    Call<Model_Load_Qty_Cart> Get_Qty_Cart(@Query("id_user") int id_user);

    @GET("LoadPriceCheckout")
    Call<Model_TotalPriceCart> Get_Total_Cart(@Query("id_user") int id_user);

    @GET("getUser")
    Call<UserModel> getUser(@Query("user_id") int user_id);

    @POST("UpdateUser")
    Call<Void> UpdateUser(@Query("user_id") int user_id,
                          @Query("user_name") String user_name,
                          @Query("bd") String bd,
                          @Query("sex") String sex,
                          @Query("phone") String phone,
                          @Query("gmail") String gmail);

    @GET("proCategory")
    Call<ProductModel> proCategory(@Query("id_category2") int id_category2);

    @GET("LoadItem_Checkout")
    Call<Model_Cart> LoadItem_Checkout(@Query("id_user") int id_user);

    @POST("ChekoutItemCart")
    Call<Void> ChekoutItemCart(@Query("user_id") int id_user,
                               @Query("method_pay") String method_pay);




}
