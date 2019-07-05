package com.group5.petstroe.activity;

import android.content.Intent;
import android.os.Bundle;

import com.group5.petstroe.R;
import com.group5.petstroe.base.GlobalUser;
import com.group5.petstroe.utils.ActivityUtils;

import static com.group5.petstroe.utils.ActivityUtils.CODE_PET_ACTIVITY;
import static com.group5.petstroe.utils.ActivityUtils.CODE_SIGN_IN_ACTIVITY;

import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;


public class MainActivity extends BaseActivity {
    private boolean isSignIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isSignIn) {
            PetActivity.startActivityForResult(this);
        } else {
            SignInActivity.startActivityForResult(this);
        }
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) { }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CODE_PET_ACTIVITY:
                SignInActivity.startActivityForResult(this);
                break;
            case CODE_SIGN_IN_ACTIVITY:
                isSignIn = (GlobalUser.user != null);
                if (isSignIn) {
                    PetActivity.startActivityForResult(this);
                } else {
                    ActivityUtils.finishAllActivity();
                }
                break;
            default:
                break;

        }
    }
}
