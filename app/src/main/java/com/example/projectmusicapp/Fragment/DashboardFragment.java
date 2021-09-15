package com.example.projectmusicapp.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmusicapp.Adapter.TimKiemAdapter;
import com.example.projectmusicapp.Model.BaiHat;
import com.example.projectmusicapp.R;
import com.example.projectmusicapp.Service.APIService;
import com.example.projectmusicapp.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment{
    View view;
    RecyclerView recyclerViewtim;
    TextView textViewnull;
    androidx.appcompat.widget.Toolbar toolbar;
    TimKiemAdapter timKiemAdapter;
    ArrayList<BaiHat> mangbaihat;
    ScrollView scrollView;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);


        recyclerViewtim = view.findViewById(R.id.recyclerviewtimkiem);
        textViewnull = view.findViewById(R.id.textviewtimkiemnull);
        toolbar = view.findViewById(R.id.toilbartimkiem);
        scrollView = view.findViewById(R.id.scollview);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        return  view;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_timkiem, menu);
        MenuItem menuItem = menu.findItem(R.id.menutimkiem);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recyclerViewtim.setBackgroundColor(Color.BLACK);
                TimKiemBaiHat(s);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void TimKiemBaiHat(String query){
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetTimKiembaihat(query);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                if (mangbaihat.size() > 0){
                    timKiemAdapter = new TimKiemAdapter(getActivity(), mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewtim.setLayoutManager(linearLayoutManager);
                    recyclerViewtim.setAdapter(timKiemAdapter);
                    textViewnull.setVisibility(View.GONE);
                    scrollView.setVisibility(View.GONE);
                    recyclerViewtim.setVisibility(View.VISIBLE);
                }else {
                    recyclerViewtim.setVisibility(View.GONE);
                    textViewnull.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
