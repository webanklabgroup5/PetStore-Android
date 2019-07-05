package com.group5.petstroe.apis;

import android.util.Log;

import com.google.gson.Gson;
import com.group5.petstroe.utils.OkHttpClientUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.group5.petstroe.apis.Constans.CONTENT_TYPE;

public class GeneralClient<Req, Res> {
    private static final String TAG = "GeneralClientTag";

    private Gson gson;
    private HttpUrl url;
    private Class<Req>reqClass;
    private Class<Res>resClass;

    private GeneralClient() {}

    protected GeneralClient(HttpUrl url, Class<Req> reqClass, Class<Res> resClass) {
        this.gson = new Gson();
        this.url = url;
        this.reqClass = reqClass;
        this.resClass = resClass;
    }

    private Result<Res> parse(String json) {
        try {
            return Result.ok(gson.fromJson(json, resClass));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
            return Result.err("JSON解析失败" + json);
        }
    }

    protected Result<Res> get() {
        OkHttpClient client = OkHttpClientUtils.getInstance();
        Request request = new Request.Builder()
                .url(this.url)
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String responseBodyString = responseBody.string();
                Log.e("fktag", responseBodyString);
                return parse(responseBodyString);
            } else {
                return Result.err(response.code() + "响应无内容体");
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
            return Result.err("Http IO请求错误");
        }
    }

    protected Result<Res> post(Req req) {
        OkHttpClient client = OkHttpClientUtils.getInstance();
        RequestBody requestBody = RequestBody.create(CONTENT_TYPE, gson.toJson(req));
        Request request = new Request.Builder()
                .url(this.url)
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String responseBodyString = responseBody.string();
                Log.e("fktag", responseBodyString);
                return parse(responseBodyString);
            } else {
                return Result.err(response.code() + "响应无内容体");
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
            return Result.err("Http IO请求错误");
        }
    }

    protected Result<Res> uploadPost(File file) {
        OkHttpClient client = OkHttpClientUtils.getInstance();
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data;application/octet-stream;"), file))
                .build();
        Request request = new Request.Builder()
                .url(this.url)
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczpcL1wvZ2xhc3Nlcy1kZXYua3NoLmZ1blwvYXBpXC9hdXRoXC91c2Vyc1wvbG9naW4iLCJpYXQiOjE1NjIxMTMwNjMsImV4cCI6MTU5ODExMzA2MywibmJmIjoxNTYyMTEzMDYzLCJqdGkiOiJiSm1ST2s0YkdUcTRDdDVxIiwic3ViIjoxLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.ONB-HYJWZU6mfbKxKoJc7u8Ttbn2KTVSwPcvmKmfitQ")
                .post(multipartBody)
                .build();
        String s = request.body().contentType().toString();
        Log.e(TAG, "request body:" + s);
        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String responseBodyString = responseBody.string();
                Log.e("fktag", responseBodyString);
                return parse(responseBodyString);
            } else {
                return Result.err(response.code() + "响应无内容体");
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
            return Result.err("Http IO请求错误");
        }
    }
}
