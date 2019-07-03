package com.group5.petstroe.apis;

import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.User;
import com.group5.petstroe.utils.ExecutorUtils;

import okhttp3.HttpUrl;

import static com.group5.petstroe.apis.Constans.CODE_USER_SIGN_IN_API;
import static com.group5.petstroe.apis.Constans.CODE_USER_SIGN_UP_API;

public enum  UserApi {
    INSTANCE;

    static class SignUpForm {
        String account;
        String balance;
        String password;

        SignUpForm(String account, String balance, String password) {
            this.account = account;
            this.balance = balance;
            this.password = password;
        }
    }

    private Result<User> signUp(String account, String balance, String password) {
        HttpUrl url = new HttpUrl.Builder()
                .build();
        GeneralClient<SignUpForm, User> client = new GeneralClient<>(url, SignUpForm.class, User.class);
        Result<User> result = client.post(new SignUpForm(account, balance, password));
        return result;
    }
    public void signUp(String account, String balance, String password, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(() -> activity.runOnUiThread(signUp(account, balance, password), CODE_USER_SIGN_UP_API));
    }


    static class SignInForm {
        String account;
        String password;

        SignInForm(String account, String password) {
            this.account = account;
            this.password = password;
        }
    }

    private Result<User> signIn(String account, String password) {
        HttpUrl url = new HttpUrl.Builder()
                .build();
        GeneralClient<SignInForm, User> client = new GeneralClient<>(url, SignInForm.class, User.class);
        Result<User> result = client.post(new SignInForm(account, password));
        return result;
    }
    public void signIn(String account, String password, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(() -> activity.runOnUiThread(signIn(account, password), CODE_USER_SIGN_IN_API));
    }
}
