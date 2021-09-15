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

import com.example.projectmusicapp.Model.PhoBien;
import com.example.projectmusicapp.R;
import com.example.projectmusicapp.Service.APIService;
import com.example.projectmusicapp.Service.Dataservice;
import com.example.projectmusicapp.Adapter.PhoBienAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PhoBien extends Fragment {
    View view;
    PhoBienAdapter phoBienAdapter;
    RecyclerView recyclerViewPhobien;
    TextView tenPhoBien;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phobien, container, false);
        recyclerViewPhobien = view.findViewById(R.id.recyclerviewphobien);
        tenPhoBien = view.findViewById(R.id.txtphobien);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<PhoBien>> callback = dataservice.GetPhoBienCurrent();
        callback.enqueue(new Callback<List<PhoBien>>() {
            @Override
            public void onResponse(Call<List<PhoBien>> call, Response<List<PhoBien>> response) {
                ArrayList<PhoBien> mangphobien = (ArrayList<PhoBien>) response.body();
                phoBienAdapter = new PhoBienAdapter(getActivity(), mangphobien);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewPhobien.setLayoutManager(linearLayoutManager);
                recyclerViewPhobien.setAdapter(phoBienAdapter);
            }

            @Override
            public void onFailure(Call<List<PhoBien>> call, Throwable t) {

            }

        });
    }
}
