package com.group5.petstroe.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group5.petstroe.R;
import com.group5.petstroe.models.User;
import com.group5.petstroe.utils.ActivityUtils;

import static com.group5.petstroe.utils.ActivityUtils.CODE_PET_ACTIVITY;
import static com.group5.petstroe.utils.ActivityUtils.CODE_SIGN_IN_ACTIVITY;

public class MainActivity extends AppCompatActivity {

    private boolean isSignIn = false;
    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isSignIn) {
            PetActivity.startActivityForResult(this, user);
        } else {
            SignInActivity.startActivityForResult(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CODE_PET_ACTIVITY:
                ActivityUtils.finishAllActivity();
            case CODE_SIGN_IN_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    isSignIn = data.getBooleanExtra("result", false);
                    if (isSignIn) {
                        user = (User)data.getExtras().get("user");
                        PetActivity.startActivityForResult(this, user);
                    }
                }
                break;
            default:
                break;
        }
    }
}
