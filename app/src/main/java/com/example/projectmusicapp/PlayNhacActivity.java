package com.example.projectmusicapp;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.projectmusicapp.Adapter.ViewPagerDiaNhac;
import com.example.projectmusicapp.Fragment.Fragment_dia_nhac;
import com.example.projectmusicapp.Model.BaiHat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    androidx.appcompat.widget.Toolbar toolbarplaynhac;
    SeekBar seekBarnhac;
    ImageView imageViewtim;
    TextView textViewtennhac , textViewcasi , textViewrunrime, textViewtatoltime;
    ImageButton imageButtonpreviewnhac,imageButtonplaypausenhac,imageButtonnexnhac;
    ViewPager viewPagerplaynhac;
    boolean dem = false;
    int position = 0;

    boolean next = false;
    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    Fragment_dia_nhac fragment_dia_nhac;
    public static ViewPagerDiaNhac adapternhac ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);

        GetDataFromIntent();

        anhXa();

        overridePendingTransition(R.anim.anim_intent_in, R.anim.anim_intent_out);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        enventClick();

        imageViewtim.setOnClickListener(view -> {
            if (!dem){
                Animation animation = AnimationUtils.loadAnimation(PlayNhacActivity.this, R.anim.anim_timclick);
                imageViewtim.setImageResource(R.drawable.iconloved);
                view.startAnimation(animation);
                dem = true;
            }else {
                imageViewtim.setImageResource(R.drawable.iconlove);
            }
        });
    }

    private void enventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mangbaihat.size() > 0){
                    fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                    handler.removeCallbacks(this);
                }else {
                    handler.postDelayed(this, 300);
                }
            }
        },500);
        imageButtonplaypausenhac.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                imageButtonplaypausenhac.setImageResource(R.drawable.nutpause);
            }else {
                mediaPlayer.start();
                imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
            }
        });

        seekBarnhac.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imageButtonnexnhac.setOnClickListener(view -> {
            if (mangbaihat.size() > 0){
                if (mediaPlayer.isPlaying() || mediaPlayer != null){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                if (position < (mangbaihat.size())){
                    imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                    position++;
                    if (position > mangbaihat.size() - 1){
                        position = 0;
                    }
                    fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                    new playMP3().execute(mangbaihat.get(position).getLinkBaiHat());
                    getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                    UpdateTime();
                }
            }
            imageButtonpreviewnhac.setClickable(false);
            imageButtonnexnhac.setClickable(false);
            Handler handler1 = new Handler();
            handler1.postDelayed(() -> {
                imageButtonpreviewnhac.setClickable(true);
                imageButtonnexnhac.setClickable(true);
            }, 3000);
        });

        imageButtonpreviewnhac.setOnClickListener(view -> {
            if (mangbaihat.size() > 0) {
                if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                if (position < (mangbaihat.size())) {
                    imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
                    position--;
                    if (position < 0) {
                        position = mangbaihat.size() - 1;
                    }
                    fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                    new playMP3().execute(mangbaihat.get(position).getLinkBaiHat());
                    getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                    UpdateTime();
                }
            }
            imageButtonpreviewnhac.setClickable(false);
            imageButtonnexnhac.setClickable(false);
            Handler handler1 = new Handler();
            handler1.postDelayed(() -> {
                imageButtonpreviewnhac.setClickable(true);
                imageButtonnexnhac.setClickable(true);
            }, 3000);
        });
    }



    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null){
            if(intent.hasExtra("cakhuc")){
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baiHat);
            }
        }
        if(intent.hasExtra("cacbaihat")){
            ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
            mangbaihat=baiHatArrayList;
        }
    }
    private void anhXa(){

        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        seekBarnhac = findViewById(R.id.seekBartime);
        viewPagerplaynhac = findViewById(R.id.viewPagerdianhac);
        imageViewtim = findViewById(R.id.imageViewtimplaynhac);
        imageButtonpreviewnhac = findViewById(R.id.imageButtonpreview);
        imageButtonplaypausenhac = findViewById(R.id.imageButtonplaypause);
        imageButtonnexnhac = findViewById(R.id.imageButtonnext);
        textViewtatoltime = findViewById(R.id.textViewtimetotal);
        textViewcasi = findViewById(R.id.textViewtencasiplaynhac);
        textViewtennhac = findViewById(R.id.textViewtenbaihatplaynhac);
        textViewrunrime = findViewById(R.id.textViewruntime);
        fragment_dia_nhac = new Fragment_dia_nhac();

        adapternhac = new ViewPagerDiaNhac(getSupportFragmentManager());

        adapternhac.AddFragment(fragment_dia_nhac);


        viewPagerplaynhac.setAdapter(adapternhac);

        setSupportActionBar(toolbarplaynhac);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setTitleTextColor(Color.BLACK);

        fragment_dia_nhac = (Fragment_dia_nhac) adapternhac.getItem(position);

        if(mangbaihat.size() > 0){
            getSupportActionBar().setTitle(mangbaihat.get(position).getLinkBaiHat());
            new playMP3().onPostExecute(mangbaihat.get(position).getLinkBaiHat());
            imageButtonplaypausenhac.setImageResource(R.drawable.nutplay);
        }
        toolbarplaynhac.setNavigationOnClickListener(view -> {
            mediaPlayer.stop();
            mangbaihat.clear();
            finish();
        });
    }

    private class playMP3 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try{
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(mp -> {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e){
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }
    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewtatoltime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarnhac.setMax(mediaPlayer.getDuration());
        textViewtennhac.setText(mangbaihat.get(position).getTenBaiHat());
        textViewcasi.setText(mangbaihat.get(position).getTenCaSi());
    }

    private void UpdateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    seekBarnhac.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    textViewrunrime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);

                    mediaPlayer.setOnCompletionListener(mediaPlayer -> {
                        next = true;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next){
                    if (position < (mangbaihat.size())) {

                        position++;

                        if (position > mangbaihat.size() - 1) {
                            position = 0;
                        }
                        try {
                            fragment_dia_nhac.PlayNhac(mangbaihat.get(position).getHinhBaiHat());
                            new playMP3().execute(mangbaihat.get(position).getLinkBaiHat());
                            getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    imageButtonpreviewnhac.setClickable(false);
                    imageButtonnexnhac.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(() -> {
                        imageButtonpreviewnhac.setClickable(true);
                        imageButtonnexnhac.setClickable(true);
                    }, 3000);
                    next = false;
                    handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);

    }
}
