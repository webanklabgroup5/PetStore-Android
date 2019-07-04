package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.group5.petstroe.R;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.apis.UserApi;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.Status;
import com.group5.petstroe.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.group5.petstroe.utils.ActivityUtils.CODE_SIGN_UP_ACTIVITY;
import static com.group5.petstroe.apis.Constans.CODE_USER_SIGN_UP_API;

public class SignUpActivity extends BaseActivity {

    @BindView(R.id.et_account) EditText etAccount;
    @BindView(R.id.et_balance) EditText etBalance;
    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.et_password_confirm) EditText etPasswordConfirm;
    @BindView(R.id.btn_sign_up) Button btnSignUp;

    private String account;
    private String balance;
    private String password;
    private String passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
        switch (resultCode) {
            case CODE_USER_SIGN_UP_API:
                shortToast("请求已发送！");
                finishActivityWithResult();
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.btn_sign_up)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up:
                if (isInfoOk()) {
                    UserApi.INSTANCE.signUp(account, balance, password, this);
                }
                break;
            default:
                break;
        }
    }

    private boolean isInfoOk() {
        account = etAccount.getText().toString().trim();
        if (StringUtils.isNullOrEmpty(account)) {
            shortToast("用户名不能为空");
            return false;
        }
        balance = etBalance.getText().toString().trim();
        if (StringUtils.isNullOrEmpty(balance)) {
            shortToast("开户积分不能为空");
            return false;
        }
        password = etPassword.getText().toString().trim();
        if (StringUtils.isNullOrEmpty(password)) {
            shortToast("请输入密码");
            return false;
        }
        passwordConfirm = etPasswordConfirm.getText().toString().trim();
        if (StringUtils.isNullOrEmpty(passwordConfirm)) {
            shortToast("请确认密码");
            return false;
        } else if (!passwordConfirm.equals(password)) {
            shortToast("两次密码不一致");
            return false;
        }
        return true;
    }

    public static void startActivityForResult(Context context) {
        ((Activity) context).startActivityForResult(new Intent(context, SignUpActivity.class), CODE_SIGN_UP_ACTIVITY);
    }

    private void finishActivityWithResult() {
        finish();
    }
}
