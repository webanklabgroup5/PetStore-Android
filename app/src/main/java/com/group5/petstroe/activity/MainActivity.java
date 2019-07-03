package com.group5.petstroe.activity;

import android.os.Bundle;

import com.group5.petstroe.R;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SignInActivity.startActivity(this);
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
    }
}
