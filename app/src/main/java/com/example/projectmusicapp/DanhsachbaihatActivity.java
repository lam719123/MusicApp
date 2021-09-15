package com.example.projectmusicapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmusicapp.Adapter.DanhsachbaihatAdapter;
import com.example.projectmusicapp.Model.BaiHat;
import com.example.projectmusicapp.Model.BangXepHang;
import com.example.projectmusicapp.Model.NgheSi;
import com.example.projectmusicapp.Model.PhoBien;
import com.example.projectmusicapp.Model.Playlist;
import com.example.projectmusicapp.Model.ThinhHanh;
import com.example.projectmusicapp.Service.APIService;
import com.example.projectmusicapp.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerViewdanhsachbaihat;
    FloatingActionButton floatingActionButton;
    TextView txtcollapsing;
    Playlist playlist = null;
    NgheSi ngheSi = null;
    PhoBien phoBien = null;
    ThinhHanh thinhHanh = null;
    BangXepHang bangXepHang = null;
    ImageView imgdanhsachcakhuc;
    ArrayList<BaiHat> mangbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);

        AnhXa();

        floatingActionButton.setEnabled(false);

        DataIntent();

        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);

        if (ngheSi != null && !ngheSi.equals("")){
            setValueInView(ngheSi.getHinhNgheSi());
            GetDataNgheSi(ngheSi.getIdNgheSi());
            txtcollapsing.setText(ngheSi.getTenNgheSi());
            getSupportActionBar().setTitle(ngheSi.getTenNgheSi());
        }
        if (playlist != null && !playlist.equals("")){
            setValueInView( playlist.getHinhPlaylist());
            GetDataPlaylist(playlist.getIdPlaylist());
            txtcollapsing.setText(playlist.getTenPlaylist());
            getSupportActionBar().setTitle(playlist.getTenPlaylist());
        }

        if (thinhHanh != null && !thinhHanh.equals("")){
            setValueInView(thinhHanh.getHinhThinhHanh());
            GetDataThinhHanh(thinhHanh.getIdThinhHanh());
            txtcollapsing.setText(thinhHanh.getTenThinhHanh());
            getSupportActionBar().setTitle(thinhHanh.getTenThinhHanh());
        }
        if (phoBien != null && !phoBien.equals("")){
            setValueInView(phoBien.getHinhPhoBien());
            GetDataPhoBien(phoBien.getIdPhoBien());
            txtcollapsing.setText(phoBien.getTenPhoBien());
            getSupportActionBar().setTitle(phoBien.getTenPhoBien());
        }
        if (bangXepHang != null && !bangXepHang.equals("")){
            setValueInView(bangXepHang.getHinhBangXepHang());
            GetDataBangXepHang(bangXepHang.getIdBangXepHang());
            txtcollapsing.setText(bangXepHang.getTenBangXepHang());
            getSupportActionBar().setTitle(bangXepHang.getTenBangXepHang());
        }

        floatActionButtonClick();
    }

    private void GetDataBangXepHang(String idBangXepHang) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatbangxephang(idBangXepHang);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataPhoBien(String idPhoBien) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatphobien(idPhoBien);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist(String idPlaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatplaylist(idPlaylist);
        callback.enqueue(new Callback<List<BaiHat>>() {

            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataThinhHanh(String idThinhHanh) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhsachbaihatthinhhanh(idThinhHanh);
        callback.enqueue(new Callback<List<BaiHat>>(){
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat =(ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }

        });
    }

    private void GetDataNgheSi(String id) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> call = dataservice.GetDanhsachbaihatnghesi(id);
        call.enqueue(new Callback<List<BaiHat>>(){
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this, mangbaihat);
                recyclerViewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String hinh) {
        Picasso.with(this).load(hinh).into(imgdanhsachcakhuc);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("intentplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("intentplaylist");
            }else
            if (intent.hasExtra("intentnghesi")){
                ngheSi = (NgheSi) intent.getSerializableExtra("intentnghesi");
            }else
            if (intent.hasExtra("intentthinhhanh")){
                thinhHanh = (ThinhHanh) intent.getSerializableExtra("intentthinhhanh");
            }else
            if (intent.hasExtra("intentphobien")){
                phoBien = (PhoBien) intent.getSerializableExtra("intentphobien");
            }else
            if (intent.hasExtra("intentbangxephang")){
                bangXepHang = (BangXepHang) intent.getSerializableExtra("intentbangxephang");
            }
        }

    }

    private void AnhXa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout  = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsachbaihat);
        recyclerViewdanhsachbaihat= findViewById(R.id.recyclerviewdanhsachbaihat);
        imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
        floatingActionButton= findViewById(R.id.floatingactionbutton);
        txtcollapsing= findViewById(R.id.textViewcollapsing);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationOnClickListener(view -> finish());
    }
    private void floatActionButtonClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(DanhsachbaihatActivity.this, PlayNhacActivity.class);
            intent.putExtra("cacbaihat", mangbaihat);
            startActivity(intent);
        });
    }
}
