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

public class TimKiemAdapter extends RecyclerView.Adapter<TimKiemAdapter.ViewHolder>{
        Context context;
        ArrayList<BaiHat> mangbaihat;


    public TimKiemAdapter(Context context , ArrayList<BaiHat> mangbaihat){
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View view = inflater.inflate(R.layout.dong_tim_kiem, viewGroup, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position ){
            BaiHat baiHat = mangbaihat.get(position);
            holder.txttentimkiem.setText(baiHat.getTenBaiHat());
            holder.txttencasitimkiem.setText(baiHat.getTenCaSi());
            Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imganhtimkiem);

    }
        @Override
        public int getItemCount(){
            return mangbaihat.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imganhtimkiem;
            TextView txttentimkiem , txttencasitimkiem;

            public ViewHolder(@NonNull View itemView){
                super(itemView);

                imganhtimkiem =itemView.findViewById(R.id.imageViewhinhtimkiem);
                txttencasitimkiem = itemView.findViewById(R.id.textViewtencasitimkiem);
                txttentimkiem =itemView.findViewById(R.id.textViewtentimkiem);

                itemView.setOnClickListener(view ->{
                    Intent intent = new Intent(context , PlayNhacActivity.class);
                    intent.putExtra("cakhuc" , mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                });
            }
        }
}