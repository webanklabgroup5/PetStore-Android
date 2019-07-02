package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.group5.petstroe.base.BaseActivity;

import static com.group5.petstroe.utils.ActivityUtils.CODE_SIGN_UP;

public class SignUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void startActivityForResult(Context context) {
        ((Activity) context).startActivityForResult(new Intent(context, SignUpActivity.class), CODE_SIGN_UP);
    }
}
