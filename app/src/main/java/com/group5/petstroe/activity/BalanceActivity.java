package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group5.petstroe.R;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.group5.petstroe.utils.ActivityUtils.CODE_BALANCE_ACTIVITY;

public class BalanceActivity extends BaseActivity {

    @BindView(R.id.tv_account) TextView tvAccount;
    @BindView(R.id.tv_balance) TextView tvBalance;
    @BindView(R.id.btn_apply) Button btnApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        ButterKnife.bind(this);
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) { }

    @OnClick(R.id.btn_apply)
    void onClick() {
        ApplyBalanceActivity.startActivityForResult(this);
    }

    public static void startActivityForResult(Context context) {
        Intent intent = new Intent(context, BalanceActivity.class);
        ((Activity) context).startActivityForResult(intent, CODE_BALANCE_ACTIVITY);
    }
}
