package com.example.projectmusicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    Animation logoapp;
    ImageView imgLogo;
    int sp_screen = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logoapp = AnimationUtils.loadAnimation(this,R.anim.anim_splash_logo);

        imgLogo = findViewById(R.id.logo_music_app);

        imgLogo.setAnimation(logoapp);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },sp_screen);
    }
}