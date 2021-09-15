package com.example.projectmusicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmusicapp.DanhsachbaihatActivity;
import com.example.projectmusicapp.Model.NgheSi;
import com.example.projectmusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NgheSiAdapter extends RecyclerView.Adapter<NgheSiAdapter.ViewHolder> {
    Context context;
    ArrayList<NgheSi> mangnghesi;
    View view;

    public NgheSiAdapter(Context context, ArrayList<NgheSi> mangnghesi) {
        this.context = context;
        this.mangnghesi = mangnghesi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dong_nghesi,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        NgheSi ngheSi = mangnghesi.get(position);
        holder.txttennghesi.setText(ngheSi.getTenNgheSi());
        Picasso.with(context).load(ngheSi.getHinhNgheSi()).into(holder.imgnghesi);
        view.setOnClickListener(view -> {
            Intent intent = new Intent(context, DanhsachbaihatActivity.class);
            intent.putExtra("intentnghesi", mangnghesi.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mangnghesi.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgnghesi;
        TextView txttennghesi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgnghesi = itemView.findViewById(R.id.imageviewnghesi);
            txttennghesi = itemView.findViewById(R.id.textviewnghesi);
        }
    }
}
