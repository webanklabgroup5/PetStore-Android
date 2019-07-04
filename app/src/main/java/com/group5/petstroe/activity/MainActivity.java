package com.group5.petstroe.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.group5.petstroe.R;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.apis.UserApi;
import com.group5.petstroe.base.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;

public class MainActivity extends BaseActivity {
    private final int OPEN_ALBUM_CODE = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

////        SignInActivity.startActivity(this);
//        PetActivity.startActivity(this);
    }

    private void test() {
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
    }

    private void openAlbum() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_ALBUM_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case OPEN_ALBUM_CODE:
                if (resultCode == RESULT_OK) {
                    File file = new File(data.getData().getPath());
                }
                break;
            default:
                break;
        }
    }
}
