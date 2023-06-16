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

public class SanphamPhobienAdapter extends RecyclerView.Adapter<SanphamPhobienAdapter.ViewHolder> {

    private Context context;
    private List<ProductModel> list;

    public SanphamPhobienAdapter(Context context, List<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SanphamPhobienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.populaprd_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanphamPhobienAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        DecimalFormat df = new DecimalFormat("#,###,###");
        Glide.with(context).load(Utils.linkImg + list.get(position).getImage()).into(holder.imgPr);
        holder.txtTensp.setText(list.get(position).getNameproduct());
        holder.txtgiasp.setText(String.valueOf(df.format(list.get(position).getProprice()))+"đ");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detail", list.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPr;
        TextView txtTensp, txtgiasp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPr = itemView.findViewById(R.id.imgsp_item);
            txtTensp = itemView.findViewById(R.id.tensp_item);
            txtgiasp = itemView.findViewById(R.id.giasp_item);
        }
    }
}
