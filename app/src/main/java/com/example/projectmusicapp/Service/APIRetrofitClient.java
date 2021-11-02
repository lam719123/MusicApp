package com.example.projectmusicapp.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//khoi tao protrofit để tương tác vs phía Server
public class APIRetrofitClient {
    //su dung OkHttp ket noi toi server
    private static Retrofit retrofit = null;
    public static Retrofit getClient(String base_url){ //truyen vao duong dan Url
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(1000, TimeUnit.MILLISECONDS)
                .writeTimeout(1000, TimeUnit.MILLISECONDS)
                .connectTimeout(1000, TimeUnit.MILLISECONDS) //ngat ket noi neu doi qua lau
                //thu ket noi lai khi connect fail
                .retryOnConnectionFailure(true)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();

        Gson gson = new GsonBuilder().setLenient().create(); //Khi mà dữ liệu trả về phía server nó sẽ là những dữ liệu API, Gson để convert những từ khóa bên API thành từ khóa bên phía Java
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))// convert nhung du lieu cua API thành bien của Java
                .build();
        return retrofit;
    }
}
