package com.group5.petstroe.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.group5.petstroe.R;
import com.group5.petstroe.apis.Result;
import com.group5.petstroe.apis.UserApi;
import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.base.GlobalUser;
import com.group5.petstroe.models.User;
import com.group5.petstroe.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.group5.petstroe.utils.ActivityUtils.CODE_SIGN_IN_ACTIVITY;
import static com.group5.petstroe.utils.ActivityUtils.CODE_SIGN_UP_ACTIVITY;
import static com.group5.petstroe.apis.Constans.CODE_USER_SIGN_IN_API;

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
        etAccount.setText("zpf");
        etPassword.setText("zpfzpf");
    }

    @Override
    protected <T> void onUiThread(Result<T> result, int resultCode) {
        switch (resultCode) {
            /**
             * 登录回调
             */
            case CODE_USER_SIGN_IN_API:
                if (result.isOk()) {
                    zlog("sign in result ok");

                    GlobalUser.user = (User) result.get();
                    finishActivityWithResult();
                } else {
                    zlog("sign in result error");
                }
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.btn_sign_in, R.id.tv_sign_up})
    void onClick(View view) {
        switch (view.getId()) {
            /**
             * 点击登录
             */
            case R.id.btn_sign_in:
                if (isInfoOk()) {
                    UserApi.INSTANCE.signIn(account, password, this);
                }
                break;
            /**
             * 点击跳转开户页面
             */
            case R.id.tv_sign_up:
                SignUpActivity.startActivityForResult(this);
                break;
            default:
                break;
        }
    }

    /**
     * 判断登录信息是否有效
     * @return
     */
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
            case CODE_SIGN_UP_ACTIVITY:
                break;
            default:
                break;
        }
    }

    public static void startActivityForResult(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        ((Activity) context).startActivityForResult(intent, CODE_SIGN_IN_ACTIVITY);
    }

    private void finishActivityWithResult() {
        finish();
    }
}
