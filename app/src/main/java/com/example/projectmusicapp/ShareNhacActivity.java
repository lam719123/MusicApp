package com.example.projectmusicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class ShareNhacActivity extends AppCompatActivity {

//    private TextView tvLinkShare;
    //private Button btnShareImage;
    private Button btnShareLink;
    private ImageView imgShare;
    private String imgShareFB;
    private Uri selectedImageUri;
    private String linkShareFB;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_nhac);

        Intent intent = getIntent();
        linkShareFB = (String) intent.getStringExtra("linkShare");
        imgShareFB = (String) intent.getStringExtra("imgShare");
        Picasso.with(ShareNhacActivity.this).load(imgShareFB).into(imgShare);

        addControl();
        addEvent();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == this.RESULT_OK) {
            if(data != null)
            {
                selectedImageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    imgShare.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void addEvent() {
        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runTimePermission();
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });
        /*btnShareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String capTion = "Học Lập Trình Android";
                ShareFbLayoutActivity.sharePhoto(bitmap,capTion);
            }
        });*/
        btnShareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "Lập Trinh Android";
                String imageThumnal = "http://androidcoban.com/wp-content/uploads/2016/07/hoc_lap_trinh_android.png";
                //String linkShare = tvLinkShare.getText().toString();
                String linkShare = linkShareFB;
                ShareFbLayoutActivity.shareLinkFB(title,linkShare,imageThumnal);
            }
        });
    }

    private void addControl() {
//        tvLinkShare = (TextView) findViewById(R.id.tv_link);
        //btnShareImage = (Button) findViewById(R.id.btn_share_image);
        btnShareLink = (Button) findViewById(R.id.btn_share_link);
        imgShare = (ImageView) findViewById(R.id.img_share);
    }

    // Check permistion cho android 6.0

    public void runTimePermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }
    }
}
