package com.example.category2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    private ArrayList<Category> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<Category> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getImageUri())
                .into(holder.imageUri);
        holder.cSeason.setText(arrayList.get(position).getcSeason());
        holder.cColor.setText(arrayList.get(position).getcColor());
        holder.cType.setText(arrayList.get(position).getcType());


    }

    @Override
    public int getItemCount() {
        //삼향 연산자
        return (arrayList !=null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageUri;
        TextView cType;
        TextView cSeason;
        TextView cColor;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageUri = itemView.findViewById(R.id.imageUri);
            this.cType = itemView.findViewById(R.id.cType);
            this.cColor = itemView.findViewById(R.id.cColor);
            this.cSeason = itemView.findViewById(R.id.cSeason);
        }
    }
}