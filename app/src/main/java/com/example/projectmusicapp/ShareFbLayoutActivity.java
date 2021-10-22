package com.example.projectmusicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.applinks.AppLinkData;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.AppInviteDialog;
import com.facebook.share.widget.ShareDialog;
import org.json.JSONObject;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import bolts.AppLinks;

public class ShareFbLayoutActivity extends AppCompatActivity {

    private static ShareFbLayoutActivity shareFbLayoutActivity;
    private CallbackManager callbackManager;
    private FacebookCallback<LoginResult> loginResult;
    private FacebookCallback<AppInviteDialog.Result> appInviteDialogResult;
    private String linkShare;
    private String imgShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_fb_layout);

        Intent intent = getIntent();
        linkShare = (String) intent.getStringExtra("linkShare");
        imgShare = (String) intent.getStringExtra("imgShare");

        printKeyHash(ShareFbLayoutActivity.this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareFbLayoutActivity = this;
        initFaceBook();
        LoginManager.getInstance().registerCallback(callbackManager, loginResult);
        Button loginButton = (Button) findViewById(R.id.btnLoginFb);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFaceBook();
            }
        });
        printKeyHash(this);
        Uri targetUrl = AppLinks.getTargetUrlFromInboundIntent(this, getIntent());
        if (targetUrl != null) {
            Log.i("Activity", "App Link Target URL: " + targetUrl.toString());
        } else {
            AppLinkData.fetchDeferredAppLinkData(this,
                    new AppLinkData.CompletionHandler() {
                        @Override
                        public void onDeferredAppLinkDataFetched(
                                AppLinkData appLinkData) {
                        }
                    });
        }
    }
    public void initFaceBook () {
        loginResult = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Login thành công xử lý tại đây
                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object,
                                                    GraphResponse response) {
                                // Application code
                                String name = object.optString(getString(R.string.name));
                                String id = object.optString(getString(R.string.id));
                                String email = object.optString(getString(R.string.email));
                                String link = object.optString(getString(R.string.link));
                                URL imageURL = extractFacebookIcon(id);
                                Log.d("name: ",name);
                                Log.d("id: ",id);
                                Log.d("email: ",email);
                                Log.d("link: ",link);
                                Log.d("imageURL: ",imageURL.toString());

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString(getString(R.string.fields), getString(R.string.fields_name));
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        };
    }
    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (android.content.pm.Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }
    public URL extractFacebookIcon(String id) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL imageURL = new URL("http://graph.facebook.com/" + id
                    + "/picture?type=large");
            return imageURL;
        } catch (Throwable e) {
            return null;
        }
    }
    //Hàm check login facebook
    public boolean isLoggedInFaceBook() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
    //Login facebook with permisstion
    public void loginFaceBook() {

        LoginManager.getInstance().logInWithReadPermissions(shareFbLayoutActivity,
                Arrays.asList("pages_manage_posts","public_profile","email"));
        Intent intent = new Intent(ShareFbLayoutActivity.this, ShareNhacActivity.class);
        intent.putExtra("linkShare", linkShare);
        intent.putExtra("imgShare" , imgShare);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public static void shareLinkFB(String title, String linkShare, String imgThumnal) {
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentTitle(title)
                .setImageUrl(Uri.parse(imgThumnal))
                .setContentUrl(Uri.parse(linkShare))
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag("#AndroidCoBan.Com")
                        .build())
                .build();
        ShareDialog.show(shareFbLayoutActivity, content);
    }

    /**
     * Share Photo
     * @param b Hình ảnh dạng bitmap
     * @param caption thêm caption
     */
    public static void sharePhoto(Bitmap b, String caption) {
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(b)
                .setCaption(caption)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo).build();
        ShareDialog.show(shareFbLayoutActivity, content);
    }
}