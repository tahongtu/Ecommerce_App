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

import com.example.myapplication.Model.ModelType_Pro;
import com.example.myapplication.R;
import com.example.myapplication.Interface.ItemClicklistener_Type;

import java.util.List;

public class Adapter_Type_Product extends RecyclerView.Adapter<Adapter_Type_Product.PlaceViewHolder> {

//    public static int id ;
    private List<ModelType_Pro> modelType_pros;
    private Context context;
    ItemClicklistener_Type itemClicklistener;
    int selectPosition = -1;

    public Adapter_Type_Product(List<ModelType_Pro> modelType_pros, Context context, ItemClicklistener_Type itemClicklistener) {
        this.modelType_pros = modelType_pros;
        this.context = context;
        this.itemClicklistener = itemClicklistener;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_type_pro,viewGroup,false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelType_Pro cateModel = modelType_pros.get(position);
        if (cateModel == null){
            return;
        }
         int id_color = modelType_pros.get(position).getColor_id();
         int id_details = modelType_pros.get(position).getDetail_id();
//        String iamge = modelType_pros.get(position).getName_type_pro();
        String name = modelType_pros.get(position).getColor();

//
//        try {
//            Picasso.get().load(APIService.CARTEGORY + iamge).placeholder(R.drawable.ic_img).into(holder.image_cate);
//        } catch (Exception e) {
//
//        }
        holder.title.setText(name);
        holder.title.setChecked(position == selectPosition);
        holder.title.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selectPosition = holder.getAdapterPosition();
                    itemClicklistener.Onclick_Type(id_details, id_color, name, modelType_pros.get(position).getDetail_qty());

                }
            }
        });


    }


    @Override
    public int getItemCount() {
        if (modelType_pros != null){
            return modelType_pros.size();
        }

        return 0;
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
//        private ImageView image_cate;
        private RadioButton title;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
//            image_cate =  itemView.findViewById(R.id.cat_img);
            title =  itemView.findViewById(R.id.radioButtontype);


        }
    }
}