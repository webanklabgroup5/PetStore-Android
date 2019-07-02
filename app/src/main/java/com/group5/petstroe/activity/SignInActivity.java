package com.group5.petstroe.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.group5.petstroe.R;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.group5.petstroe.utils.ActivityUtils.CODE_SIGN_UP;

public class SignInActivity extends BaseActivity {

    @BindView(R.id.et_account) EditText etAccount;
    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.btn_sign_in) Button btnSignIn;
    @BindView(R.id.tv_sign_up) TextView tvSignUp;

    private String account;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(SignInActivity.this);
    }

    @OnClick({R.id.btn_sign_in, R.id.tv_sign_up})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                if (isInfoOk()) {
                    shortToast("ok");
                }
                break;
            case R.id.tv_sign_up:
                SignUpActivity.startActivityForResult(this);
                break;
            default:
                break;
        }
    }

    private boolean isInfoOk() {
        account = etAccount.getText().toString().trim();
        if (StringUtils.isNullOrEmpty(account)) {
            shortToast("用户名不能为空！");
            return false;
        }
        password = etPassword.getText().toString().trim();
        if (StringUtils.isNullOrEmpty(password)) {
            shortToast("请输入密码！");
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CODE_SIGN_UP:
                break;
            default:
                break;
        }
    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, SignInActivity.class));
    }
}
