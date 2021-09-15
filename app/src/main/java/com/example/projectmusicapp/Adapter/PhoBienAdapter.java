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
import com.example.projectmusicapp.Model.PhoBien;
import com.example.projectmusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhoBienAdapter extends RecyclerView.Adapter<PhoBienAdapter.ViewHolder> {
    Context context;
    ArrayList<PhoBien> arrayListphobien;
    View view;

    public PhoBienAdapter(Context context , ArrayList<PhoBien> arrayListphobien){
        this.context = context;
        this.arrayListphobien = arrayListphobien;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view =inflater.inflate(R.layout.dong_phobien,parent,false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position ){
        PhoBien phoBien = arrayListphobien.get(position);
        holder.txttenphobien.setText(phoBien.getTenPhoBien());
        Picasso.with(context).load(phoBien.getHinhPhoBien()).into(holder.imgphobien);
        view.setOnClickListener(view ->{
            Intent intent = new Intent(context,DanhsachbaihatActivity.class);
            intent.putExtra("intentphobien" , arrayListphobien.get(position));
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount(){
        return arrayListphobien.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgphobien;
        TextView txttenphobien;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imgphobien =itemView.findViewById(R.id.imageviewphobien);
            txttenphobien = itemView.findViewById(R.id.textviewphobien);
        }
    }

}
