package com.example.projectmusicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectmusicapp.R;

public class HomeFragment extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //onCrateView trong Fragment dùng để gắn View cho fragment này
        // view dùng để gắn layout cho phần fragment và lát nữa có thể lấy cái view này mình có thể tương tác những cái view ở bên trong layout của fragment
        view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        return  view;
    }
}
