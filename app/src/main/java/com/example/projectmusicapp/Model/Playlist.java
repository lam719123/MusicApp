package com.example.projectmusicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {
    @SerializedName("IdPlaylist")
    @Expose
    private String idPlaylist;
    @SerializedName("TenPlayList")
    @Expose
    private String ten;
    @SerializedName("HinhNen")
    @Expose
    private String hinhPlaylist;



    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getTenPlaylist() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhPlaylist() {
        return hinhPlaylist;
    }

    public void setHinhPlaylist(String hinhPlaylist) {
        this.hinhPlaylist = hinhPlaylist;
    }
}
