package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.ModelSize_Pro;
import com.example.myapplication.R;
import com.example.myapplication.Interface.ItemClicklistener_Size;

import java.util.List;

public class Adapter_Size_Product extends RecyclerView.Adapter<Adapter_Size_Product.PlaceViewHolder> {


    private List<ModelSize_Pro> modelSize_pros;
    private Context context;
    ItemClicklistener_Size itemClicklisten;
    int selectPosition = -1;

    public Adapter_Size_Product(List<ModelSize_Pro> modelSize_pros, Context context, ItemClicklistener_Size itemClicklisten) {
        this.modelSize_pros = modelSize_pros;
        this.context = context;
        this.itemClicklisten = itemClicklisten;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_size_pro, viewGroup, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelSize_Pro cateModel = modelSize_pros.get(position);
        if (cateModel == null) {
            return;
        }
        int id = modelSize_pros.get(position).getSize_id();
        String name = modelSize_pros.get(position).getSize();

        holder.title.setText(name);
        holder.title.setChecked(position == selectPosition);
        holder.title.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selectPosition = holder.getAdapterPosition();
                    itemClicklisten.Onclick_Size(id, name, modelSize_pros.get(position).getDetail_qty());

                }
            }
        });

    }


    @Override
    public int getItemCount() {


        if (modelSize_pros != null){
            return modelSize_pros.size();
        }

        return 0;
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        //        private ImageView image_cate;
        private RadioButton title;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.radioButtonsize);


        }
    }
}