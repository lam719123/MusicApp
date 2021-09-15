package com.example.projectmusicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmusicapp.DanhsachbaihatActivity;
import com.example.projectmusicapp.Model.ThinhHanh;
import com.example.projectmusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThinhHanhAdapter extends RecyclerView.Adapter<ThinhHanhAdapter.ViewHolder> {
    Context context;
    ArrayList<ThinhHanh> mangthinhhanh;
    View view;

    public ThinhHanhAdapter(Context context , ArrayList<ThinhHanh> mangthinhhanh){
        this.context = context;
        this.mangthinhhanh = mangthinhhanh;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view =inflater.inflate(R.layout.dong_thinhhanh,parent,false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position ){
        ThinhHanh thinhHanh = mangthinhhanh.get(position);
        holder.txttenthinhhanh.setText(thinhHanh.getTenThinhHanh());
        Picasso.with(context).load(thinhHanh.getHinhThinhHanh()).into(holder.imgthinhhanh);
        view.setOnClickListener(view ->{
            Intent intent = new Intent(context,DanhsachbaihatActivity.class);
            intent.putExtra("intentthinhhanh" , mangthinhhanh.get(position));
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount(){
        return mangthinhhanh.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgthinhhanh;
        TextView txttenthinhhanh;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imgthinhhanh =itemView.findViewById(R.id.imageviewthinhhanh);
            txttenthinhhanh = itemView.findViewById(R.id.textviewthinhhanh);
        }
    }
}
