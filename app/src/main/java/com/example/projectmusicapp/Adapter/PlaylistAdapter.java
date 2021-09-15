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
import com.example.projectmusicapp.Model.Playlist;
import com.example.projectmusicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> mangplaylist;
    View view;

    public PlaylistAdapter(Context context , ArrayList<Playlist> mangplaylist){
        this.context = context;
        this.mangplaylist = mangplaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view =inflater.inflate(R.layout.dong_playlist,parent,false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position ){
        Playlist playlist = mangplaylist.get(position);
        holder.txttenplaylist.setText(playlist.getTenPlaylist());
        Picasso.with(context).load(playlist.getHinhPlaylist()).into(holder.imgplaylist);
        view.setOnClickListener(view ->{
            Intent intent = new Intent(context,DanhsachbaihatActivity.class);
            intent.putExtra("intentplaylist" , mangplaylist.get(position));
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount(){
        return mangplaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgplaylist;
        TextView txttenplaylist;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imgplaylist =itemView.findViewById(R.id.imageviewplaylist);
            txttenplaylist = itemView.findViewById(R.id.textviewplaylist);
        }
    }
}
