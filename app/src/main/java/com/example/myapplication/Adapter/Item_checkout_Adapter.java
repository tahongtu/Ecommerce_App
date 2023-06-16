package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Model.Model_Cart;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Utils;

import java.util.List;

public class Item_checkout_Adapter extends RecyclerView.Adapter<Item_checkout_Adapter.ViewHolder> {

    private List<Model_Cart> modelCarts;
    private Context context;

    public Item_checkout_Adapter(List<Model_Cart> modelCarts, Context context) {
        this.modelCarts = modelCarts;
        this.context = context;
    }

    @NonNull
    @Override
    public Item_checkout_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_checkout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Item_checkout_Adapter.ViewHolder holder, int position) {

        Model_Cart cateModel = modelCarts.get(position);
        if (cateModel == null){
            return;
        }
        int id_cart = modelCarts.get(position).getCart_id();
        int id_product = modelCarts.get(position).getProduct_id();
        int id_type = modelCarts.get(position).getDetail_product_id();

        String checkCart = modelCarts.get(position).getStatus();

        holder.tv_qty.setText(modelCarts.get(position).getCart_qty());


        holder.name_pro_item.setText(modelCarts.get(position).getName_item());
        holder.tv_typro.setText(modelCarts.get(position).getNamecolor()+modelCarts.get(position).getNamesize());

        Glide.with(context).load(Utils.linkImg + modelCarts.get(position).getImage_item()).into(holder.image_item);
        int total = Integer.parseInt(modelCarts.get(position).getPrice_item()) * Integer.parseInt(modelCarts.get(position).getCart_qty()) ;
        holder.tv_price.setText(String.valueOf(total));



    }

    @Override
    public int getItemCount() {
        return modelCarts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image_item;
        private TextView name_pro_item,tv_typro, tv_price, tv_qty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_item =  itemView.findViewById(R.id.image_item);
            name_pro_item =  itemView.findViewById(R.id.name_pro_item);
            tv_typro =  itemView.findViewById(R.id.tv_typro);
            tv_price =  itemView.findViewById(R.id.tv_price);
            tv_qty =  itemView.findViewById(R.id.tv_qty);
        }
    }
}
