package com.example.projectmusicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmusicapp.Adapter.ThinhHanhAdapter;
import com.example.projectmusicapp.Model.ThinhHanh;
import com.example.projectmusicapp.R;
import com.example.projectmusicapp.Service.APIService;
import com.example.projectmusicapp.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ThinhHanh extends Fragment {
    View view;
    ThinhHanhAdapter thinhHanhAdapter;
    RecyclerView recyclerViewthinhhanh;
    TextView tenthinhHanh;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thinhhanh, container, false);
        recyclerViewthinhhanh = view.findViewById(R.id.recyclerviewthinhhanh);
        tenthinhHanh = view.findViewById(R.id.txtthinhhanh);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<ThinhHanh>> callback = dataservice.GetThinhHanhCurrent();
        callback.enqueue(new Callback<List<ThinhHanh>>() {
            @Override
            public void onResponse(Call<List<ThinhHanh>> call, Response<List<ThinhHanh>> response) {
                ArrayList<ThinhHanh> mangthinhhanh = (ArrayList<ThinhHanh>) response.body();
                thinhHanhAdapter = new ThinhHanhAdapter(getActivity(), mangthinhhanh);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewthinhhanh.setLayoutManager(linearLayoutManager);
                recyclerViewthinhhanh.setAdapter(thinhHanhAdapter);
            }

            @Override
            public void onFailure(Call<List<ThinhHanh>> call, Throwable t) {

            }
        });
    }
}
