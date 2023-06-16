package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.DetailActivity;
import com.example.myapplication.Model.ProductModel;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Utils;

import java.text.DecimalFormat;
import java.util.List;

public class SanphamMoiAdapter extends RecyclerView.Adapter<SanphamMoiAdapter.ViewHolder> {

    private Context context;

    private List<ProductModel> listSpmoi;

    public SanphamMoiAdapter(Context context, List<ProductModel> listSpmoi) {
        this.context = context;
        this.listSpmoi = listSpmoi;
    }

    @NonNull
    @Override
    public SanphamMoiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_products, parent, false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull SanphamMoiAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        DecimalFormat df = new DecimalFormat("#,###,###");
        Glide.with(context).load(Utils.linkImg + listSpmoi.get(position).getImage()).into(holder.img_spmoi);
        holder.txttensp.setText(listSpmoi.get(position).getNameproduct());
        holder.txtgiasp.setText(String.valueOf(df.format(listSpmoi.get(position).getProprice()))+"đ");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detail", listSpmoi.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listSpmoi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_spmoi;
        TextView txttensp, txtgiasp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_spmoi = itemView.findViewById(R.id.img_newsp);
            txttensp = itemView.findViewById(R.id.tenspmoi);
            txtgiasp = itemView.findViewById(R.id.giasp);

        }
    }
}
