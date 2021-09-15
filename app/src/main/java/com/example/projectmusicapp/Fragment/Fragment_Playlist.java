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

import com.example.projectmusicapp.Adapter.PlaylistAdapter;
import com.example.projectmusicapp.Model.Playlist;
import com.example.projectmusicapp.R;
import com.example.projectmusicapp.Service.APIService;
import com.example.projectmusicapp.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Playlist extends Fragment {
    View view;
    PlaylistAdapter playlistAdapter;
    RecyclerView recyclerViewplaylist;
    TextView tenPlaylist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        recyclerViewplaylist = view.findViewById(R.id.recyclerviewplaylist);
        tenPlaylist = view.findViewById(R.id.txtplaylist);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.GetPlaylistCurrentDay();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangplaylist = (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(), mangplaylist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewplaylist.setLayoutManager(linearLayoutManager);
                recyclerViewplaylist.setAdapter(playlistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
}
