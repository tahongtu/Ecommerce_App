package com.example.myapplication.Adapter;

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
import com.example.myapplication.Activity.ShowAllActivity;
import com.example.myapplication.Model.DanhmucModel;
import com.example.myapplication.R;

import java.util.List;

public class DanhmucAdapter extends RecyclerView.Adapter<DanhmucAdapter.ViewHolder> {

    private Context context;
    private List<DanhmucModel> list;

    public DanhmucAdapter(Context context, List<DanhmucModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.danhmuc_list, parent, false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DanhmucModel model = list.get(position);
        if (model == null){
            return;
        }

        String img_link[] = {
                "https://chatuchak.vn/image/cache/catalog/new/icon-quan-ao-nam-200x200.png",
                "https://media.istockphoto.com/id/665064860/vi/vec-to/qu%E1%BA%A7n-%C3%A1o-m%C3%A0u-qu%E1%BA%A7n-jean-jeans.jpg?s=170667a&w=0&k=20&c=QE9d4DoqPxX2KBFPbviK01ReSo67zXhNgJ3Zu0otxH4=",
                "https://cdn-icons-png.flaticon.com/512/1720/1720819.png",
                "https://cdn-icons-png.flaticon.com/512/1926/1926369.png",
                "https://topsivn.s3.ap-southeast-1.amazonaws.com/image_category/VTGwwQpVthk47OgwIaSkypmhT8kdTpc9jn7UZGIe.png",
                "https://img.lovepik.com/free-png/20211211/lovepik-football-icon-free-vector-illustration-material-png-image_401491783_wh1200.png",
                "https://banner2.cleanpng.com/20180304/ekw/kisspng-scalable-vector-graphics-icon-a-riding-helmet-5a9cc80a9fc356.3888479515202242666544.jpg",
                "https://png.pngtree.com/png-vector/20201225/ourlarge/pngtree-safety-helmet-construction-site-safety-png-image_2601420.jpg",
                "https://png.pngtree.com/png-vector/20191023/ourlarge/pngtree-pilot-helmet-icon-cartoon-style-png-image_1839437.jpg"

        };

        Glide.with(context).load(img_link[position]).into(holder.danhmucImg);
        holder.tendanhmuc.setText(list.get(position).getName2());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowAllActivity.class);
                intent.putExtra("category2", list.get(position).getId_category2());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView danhmucImg;
        TextView tendanhmuc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            danhmucImg = itemView.findViewById(R.id.danhmuc_img);
            tendanhmuc = itemView.findViewById(R.id.tendanhmuc);

        }
    }
}
