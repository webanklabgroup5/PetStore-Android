package com.group5.petstroe.utils;

import okhttp3.OkHttpClient;

public class OkHttpClientUtils {
    private static OkHttpClient clientInstance;

    private OkHttpClientUtils() {
    }

    public static synchronized OkHttpClient getInstance() {
        if (clientInstance == null) {
            clientInstance = new OkHttpClient.Builder().build();
        }
        return clientInstance;
    }
}
