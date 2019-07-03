package com.group5.petstroe.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.group5.petstroe.apis.Result;
import com.group5.petstroe.utils.ActivityUtils;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.removeActivity(this);
    }

    protected abstract <T> void onUiThread(Result<T> result, int resultCode);

    public <T> void runOnUiThread(Result<T> result, int resultCode) {
        runOnUiThread(() -> onUiThread(result, resultCode));
    }

    protected void shortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void longToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
