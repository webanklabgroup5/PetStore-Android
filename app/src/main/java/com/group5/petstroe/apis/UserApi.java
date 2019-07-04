package com.group5.petstroe.apis;

import com.group5.petstroe.base.BaseActivity;
import com.group5.petstroe.models.Status;
import com.group5.petstroe.models.User;
import com.group5.petstroe.utils.ExecutorUtils;

import okhttp3.HttpUrl;

import static com.group5.petstroe.apis.Constans.CODE_USER_GET_USER_INFO_API;
import static com.group5.petstroe.apis.Constans.CODE_USER_SIGN_IN_API;
import static com.group5.petstroe.apis.Constans.CODE_USER_SIGN_OUT_API;
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
        String user_name;
        String credit;
        String password;

        SignUpForm(String account, String balance, String password) {
            this.user_name = account;
            this.credit = balance;
            this.password = password;
        }
    }
    public void signUp(String account, String balance, String password, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                HttpUrl url = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(HOST)
                        .port(PORT)
                        .encodedPath(PATH_SIGN_UP)
                        .build();
                GeneralClient<SignUpForm, Status> client = new GeneralClient<>(url, SignUpForm.class, Status.class);
                Result<Status> result = client.post(new SignUpForm(account, balance, password));
                activity.runOnUiThread(result, CODE_USER_SIGN_UP_API);
            }
        });
    }


    static class SignInForm {
        String type;
        String user_name;
        String password;

        SignInForm(String account, String password) {
            this.type = "1";
            this.user_name = account;
            this.password = password;
        }
    }
    static class SignInResultForm {
        int status;
        User user;
    }
    public void signIn(String account, String password, BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                HttpUrl url = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(HOST)
                        .port(PORT)
                        .encodedPath(PATH_SIGN_IN)
                        .build();
                GeneralClient<SignInForm, SignInResultForm> client = new GeneralClient<>(url, SignInForm.class, SignInResultForm.class);
                Result<SignInResultForm> result = client.post(new SignInForm(account, password));
                if (result.isOk()) {
                    activity.runOnUiThread(Result.ok(result.get().user), CODE_USER_SIGN_IN_API);
                } else {
                    activity.runOnUiThread(result, CODE_USER_SIGN_IN_API);
                }
            }
        });
    }

    public void signOut(BaseActivity activity) {
        ExecutorUtils.getSingleThreadExecutor().execute(() -> {
            HttpUrl url = new HttpUrl.Builder()
                    .scheme(PROTOCOL)
                    .host(HOST)
                    .port(PORT)
                    .encodedPath(PATH_SIGN_OUT)
                    .build();
            GeneralClient<Object, Status> client = new GeneralClient<>(url, null, Status.class);
            Result<Status> result = client.post(null);
            activity.runOnUiThread(result, CODE_USER_SIGN_OUT_API);
        });
    }


    public void getUserInfo(String id, BaseActivity activity) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(HOST)
                .port(PORT)
                .encodedPath(PATH_GET_USER_INFO)
                .addEncodedQueryParameter("user_id", id)
                .build();
        GeneralClient<Object, User> client = new GeneralClient<>(url, null, User.class);
        Result<User> result = client.get();
        activity.runOnUiThread(result, CODE_USER_GET_USER_INFO_API);
    }
}
