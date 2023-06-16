package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.myapplication.Interface.itemOnclickListenner_ItemCart;
import com.example.myapplication.Model.Model_Cart;
import com.example.myapplication.R;
import com.example.myapplication.Service.ApiEcommerceShop;
import com.example.myapplication.Utils.Utils;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemCart_Adapter extends RecyclerView.Adapter<ItemCart_Adapter.PlaceViewHolder> {


    private List<Model_Cart> modelCarts;
    private Context context;
    itemOnclickListenner_ItemCart itemOnclickListenner_itemCart;


    public ItemCart_Adapter(List<Model_Cart> modelCarts, Context context, itemOnclickListenner_ItemCart itemOnclickListenner_itemCart) {
        this.modelCarts = modelCarts;
        this.context = context;
        this.itemOnclickListenner_itemCart = itemOnclickListenner_itemCart;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemcart_product,viewGroup,false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Model_Cart cateModel = modelCarts.get(position);
        if (cateModel == null){
            return;
        }
        int id_cart = modelCarts.get(position).getCart_id();
        int id_product = modelCarts.get(position).getProduct_id();
        int id_type = modelCarts.get(position).getDetail_product_id();

        String checkCart = modelCarts.get(position).getStatus();

        holder.tv_qty.setText(modelCarts.get(position).getCart_qty());


        if (Integer.parseInt(checkCart) == 1){
            holder.checkBox.setChecked(true);
        }else if (Integer.parseInt(checkCart) == 0){
            holder.checkBox.setChecked(false);
        }


        holder.name_pro_item.setText(modelCarts.get(position).getName_item());
        holder.tv_typro.setText(modelCarts.get(position).getNamecolor()+modelCarts.get(position).getNamesize());

        Glide.with(context).load(Utils.linkImg + modelCarts.get(position).getImage_item()).into(holder.image_item);

        String qty_kho_item = modelCarts.get(position).getQty_kho_item();

        String price_item = modelCarts.get(position).getPrice_item();
        String Qty_item = modelCarts.get(position).getCart_qty();
        int aaa = Integer.parseInt(Qty_item)*Integer.parseInt(price_item);
        holder.tv_price.setText(String.valueOf(aaa));

        holder.tv_OuttoSock.setVisibility(View.GONE);
        if (Integer.parseInt(Qty_item) > Integer.parseInt(qty_kho_item)){
            int checkked =0;
            holder.tv_OuttoSock.setVisibility(View.VISIBLE);
            holder.checkBox.setVisibility(View.GONE);
            holder.tv_typro.setVisibility(View.GONE);
            holder.tv_price.setVisibility(View.GONE);
            checked_iteamcart(id_cart,checkked);


        }


        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked()){
                    int checkked = 1;
                    checked_iteamcart(id_cart,checkked);
                }else{
                    int checkked = 0;
                    checked_iteamcart(id_cart,checkked);
                }
            }
        });

        holder.btn_dow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int abc = Integer.parseInt(holder.tv_qty.getText().toString()) - 1;
                if(Integer.parseInt(holder.tv_qty.getText().toString()) > 1){
                    holder.tv_qty.setText(String.valueOf(abc));
                    update_qty_itemCart(id_cart, Integer.parseInt(holder.tv_qty.getText().toString()));
                    itemOnclickListenner_itemCart.Onclickitem_update();
                }

            }
        });
        holder.btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(holder.tv_qty.getText().toString()) < Integer.parseInt(qty_kho_item)){
                    int abc = Integer.parseInt(holder.tv_qty.getText().toString()) + 1;
                    holder.tv_qty.setText(String.valueOf(abc));
                    update_qty_itemCart(id_cart, Integer.parseInt(holder.tv_qty.getText().toString()));
                    itemOnclickListenner_itemCart.Onclickitem_update();
                }else{
                    Toast.makeText(context, "out of stock" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clear_IteamCart(id_cart);
            }
        });


    }

    public void checked_iteamcart(int id_cart, int checked){
        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<Void> callback = apiEcommerceShop.Update_checkItemCart(id_cart, checked);
        callback.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    itemOnclickListenner_itemCart.Onclickitem_update();
//                  Toast.makeText(context,  checked+"/"+id_cart , Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
            }
        });
    }

    public void update_qty_itemCart(int id_cart, int ty_item){
        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<Void> callback = apiEcommerceShop.Update_QtyItemCart(id_cart, ty_item);
        callback.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
             if (response.isSuccessful()){
                 itemOnclickListenner_itemCart.Onclickitem_update();
             }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
            }
        });
    }

    public void Clear_IteamCart(int id_cart){
        ApiEcommerceShop apiEcommerceShop = Utils.getService();
        Call<Void> callback = apiEcommerceShop.Clear_IteamCart(id_cart);
        callback.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "Delete Item Success" , Toast.LENGTH_SHORT).show();
                    itemOnclickListenner_itemCart.Onclickitem_delete();
                }

            }
            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
            }
        });
    }


    @Override
    public int getItemCount() {
        return modelCarts.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private ImageView image_item;
        private TextView name_pro_item,tv_typro, tv_price, tv_OuttoSock, tv_qty;
        private ImageButton btn_clear,btn_dow,btn_up;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox =  itemView.findViewById(R.id.checkBox);
            image_item =  itemView.findViewById(R.id.image_item);
            name_pro_item =  itemView.findViewById(R.id.name_pro_item);
            tv_typro =  itemView.findViewById(R.id.tv_typro);
            tv_price =  itemView.findViewById(R.id.tv_price);
            tv_OuttoSock =  itemView.findViewById(R.id.tv_OuttoSock);
            tv_qty =  itemView.findViewById(R.id.tv_qty);
            btn_clear =  itemView.findViewById(R.id.btn_clear);
            btn_dow =  itemView.findViewById(R.id.btn_dow);
            btn_up =  itemView.findViewById(R.id.btn_up);


        }
    }
}
