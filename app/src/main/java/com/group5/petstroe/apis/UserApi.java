package com.group5.petstroe.apis;

import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.User;
import com.group5.petstroe.utils.ExecutorUtils;

import okhttp3.HttpUrl;

import static com.group5.petstroe.apis.Constans.CODE_USER_SIGN_IN_API;
import static com.group5.petstroe.apis.Constans.CODE_USER_SIGN_UP_API;
import static com.group5.petstroe.apis.Constans.HOST;
import static com.group5.petstroe.apis.Constans.PATH_GET_USER_INFO;
import static com.group5.petstroe.apis.Constans.PATH_SIGN_IN;
import static com.group5.petstroe.apis.Constans.PATH_SIGN_OUT;
import static com.group5.petstroe.apis.Constans.PATH_SIGN_UP;
import static com.group5.petstroe.apis.Constans.PORT;
import static com.group5.petstroe.apis.Constans.PROTOCOL;

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

    public void signUp(String account, String balance, String password) {
        ExecutorUtils.getSingleThreadExecutor().execute(() -> {
            HttpUrl url = new HttpUrl.Builder()
                    .scheme(PROTOCOL)
                    .host(HOST)
                    .port(PORT)
                    .encodedPath(PATH_SIGN_UP)
                    .build();
            GeneralClient<SignUpForm, Object> client = new GeneralClient<>(url, SignUpForm.class, null);
            client.post(new SignUpForm(account, balance, password));
        });
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
                .scheme(PROTOCOL)
                .host(HOST)
                .port(PORT)
                .encodedPath(PATH_SIGN_IN)
                .build();
        GeneralClient<SignInForm, User> client = new GeneralClient<>(url, SignInForm.class, User.class);
        Result<User> result = client.post(new SignInForm(account, password));
        return result;
    }
    public void signIn(String account, String password, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(() -> activity.runOnUiThread(signIn(account, password), CODE_USER_SIGN_IN_API));
    }

    public void signOut() {
        ExecutorUtils.getSingleThreadExecutor().execute(() -> {
            HttpUrl url = new HttpUrl.Builder()
                    .scheme(PROTOCOL)
                    .host(HOST)
                    .port(PORT)
                    .encodedPath(PATH_SIGN_OUT)
                    .build();
            GeneralClient<Object, Object> client = new GeneralClient<>(url, null, null);
            client.post(null);
        });
    }

    private Result<User> getUserInfo(String id) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(HOST)
                .port(PORT)
                .encodedPath(PATH_GET_USER_INFO)
                .build();
        GeneralClient<String, User> client = new GeneralClient<>(url, String.class, User.class);
        Result<User> result = client.post(id);
        return result;
    }
}
