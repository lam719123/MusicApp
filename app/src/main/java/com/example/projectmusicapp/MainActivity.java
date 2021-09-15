package com.example.projectmusicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import com.example.projectmusicapp.Adapter.MainViewPagerAdapter;
import com.example.projectmusicapp.Fragment.DashboardFragment;
import com.example.projectmusicapp.Fragment.HomeFragment;
import com.example.projectmusicapp.Fragment.LoadingDialog;
import com.example.projectmusicapp.Fragment.PremiumFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Catch();
        init();
        final LoadingDialog loadingdialog = new LoadingDialog (MainActivity.this);
        loadingdialog.StartLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(() -> loadingdialog.dismissDialog(), 3000);

    }
    private void init(){
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new HomeFragment(),"");
        mainViewPagerAdapter.addFragment(new DashboardFragment(),"");
        mainViewPagerAdapter.addFragment(new PremiumFragment(),"");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconlogo);
    }

    private void Catch(){
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}