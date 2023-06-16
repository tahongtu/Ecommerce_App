package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Model.BinhluanModel;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Utils;

import java.util.List;

public class BinhluanAdapter extends RecyclerView.Adapter<BinhluanAdapter.ViewHolder> {

    Context context;

    List<BinhluanModel> list;

    public BinhluanAdapter(Context context, List<BinhluanModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BinhluanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhluanAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(Utils.linkImg + list.get(position).getImage()).into(holder.avata_bl);
        holder.tennguoiBL.setText(list.get(position).getUser_name());
        holder.ngaythangBL.setText(list.get(position).getDate_comment());
        holder.noidungBL.setText(list.get(position).getComment());

        holder.ratingBar.setRating(list.get(position).getStar());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView avata_bl;
        RatingBar ratingBar;
        TextView tennguoiBL, ngaythangBL, noidungBL;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            avata_bl = itemView.findViewById(R.id.avata_BL);
            tennguoiBL = itemView.findViewById(R.id.tennguoiBL);
            ngaythangBL = itemView.findViewById(R.id.ngaythangBL);
            noidungBL = itemView.findViewById(R.id.noidungBL);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
