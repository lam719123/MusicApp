package com.example.projectmusicapp.Service;

import com.example.projectmusicapp.Model.BaiHat;
import com.example.projectmusicapp.Model.BangXepHang;
import com.example.projectmusicapp.Model.NgheSi;
import com.example.projectmusicapp.Model.PhoBien;
import com.example.projectmusicapp.Model.Playlist;
import com.example.projectmusicapp.Model.ThinhHanh;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {
    @GET("playlistcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("nghesicurrent.php")
    Call<List<NgheSi>> GetNgheSiCurrent();

    @GET("thinhhanhcurrent.php")
    Call<List<ThinhHanh>> GetThinhHanhCurrent();

    @GET("phobiencurrent.php")
    Call<List<PhoBien>> GetPhoBienCurrent();

    @GET("bangxephangcurrent.php")
    Call<List<BangXepHang>> GetBangXepHangCurrent();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatplaylist(@Field("idplaylist") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatnghesi(@Field("idnghesi") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatthinhhanh(@Field("idthinhhanh") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatphobien(@Field("idphobien") String id);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihatbangxephang(@Field("idbangxephang") String id);

    @FormUrlEncoded
    @POST("timkiembaihat.php")
    Call<List<BaiHat>> GetTimKiembaihat(@Field("tukhoa") String tukhoa);
}
