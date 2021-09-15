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
import com.example.projectmusicapp.Model.BangXepHang;
import com.example.projectmusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class BangXepHangAdapter extends RecyclerView.Adapter<BangXepHangAdapter.ViewHolder>{
    Context context;
    ArrayList<BangXepHang> mangbangxephang;
    View view;

    public BangXepHangAdapter(Context context , ArrayList<BangXepHang> mangbangxephang){
        this.context = context;
        this.mangbangxephang = mangbangxephang;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view =inflater.inflate(R.layout.dong_bangxephang,parent,false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position ){
        BangXepHang bangXepHang = mangbangxephang.get(position);
        holder.txtbangxephang.setText(bangXepHang.getTenBangXepHang());
        Picasso.with(context).load(bangXepHang.getHinhBangXepHang()).into(holder.imgbangxephang);
        view.setOnClickListener(view ->{
            Intent intent = new Intent(context,DanhsachbaihatActivity.class);
            intent.putExtra("intentbangxephang" , mangbangxephang.get(position));
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount(){
        return mangbangxephang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgbangxephang;
        TextView txtbangxephang;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imgbangxephang =itemView.findViewById(R.id.imageviewbangxephang);
            txtbangxephang = itemView.findViewById(R.id.textviewbangxephang);
        }
    }
}
