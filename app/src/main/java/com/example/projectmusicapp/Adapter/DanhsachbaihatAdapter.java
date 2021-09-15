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

import com.example.projectmusicapp.Model.BaiHat;
import com.example.projectmusicapp.PlayNhacActivity;
import com.example.projectmusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> mangbaihat;
    View view;

    public DanhsachbaihatAdapter(Context context, ArrayList<BaiHat> mangbaihat){
        this.context = context;
        this.mangbaihat = mangbaihat;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent , int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dong_danh_sach_bai_hat, parent , false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder , final int position){
        BaiHat baiHat = mangbaihat.get(position);
        holder.txttenbaihat.setText(baiHat.getTenBaiHat());
        holder.txttencasi.setText(baiHat.getTenCaSi());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.hinhbaihat);

    }

    @Override
    public int getItemCount(){
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hinhbaihat , tim ;
        TextView txttenbaihat , txttencasi;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txttenbaihat = itemView.findViewById(R.id.textViewtenbaihat);
            txttencasi = itemView.findViewById(R.id.textViewtencasi);
            hinhbaihat = itemView.findViewById(R.id.imageViewhinhbaihat);
            tim = itemView.findViewById(R.id.imageViewtimdanhsachbaihat);

            itemView.setOnClickListener(view ->{
                Intent intent = new Intent(context , PlayNhacActivity.class);
                intent.putExtra("cakhuc" , mangbaihat.get(getAdapterPosition()));
                context.startActivity(intent);
            });
        }
    }
}

