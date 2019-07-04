package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.group5.petstroe.R;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.group5.petstroe.utils.ActivityUtils.CODE_APPLY_BALANCE_ACTIVITY;

public class ApplyBalanceActivity extends BaseActivity {

    @BindView(R.id.et_balance) EditText etBalance;
    @BindView(R.id.btn_apply) Button btnApply;

    private String balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_balance);
        ButterKnife.bind(this);
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) { }

    @OnClick(R.id.btn_apply)
    void onClick() {
        if (isInfoOk()) {
            shortToast("clicked");
        }
    }

    private boolean isInfoOk() {
        return true;
    }

    public static void startActivityForResult(Context context) {
        Intent intent = new Intent(context, ApplyBalanceActivity.class);
        ((Activity) context).startActivityForResult(intent, CODE_APPLY_BALANCE_ACTIVITY);
    }

    private void finishActivityWithResult() { }
}
