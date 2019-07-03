package com.group5.petstroe.utils;

import org.jetbrains.annotations.NotNull;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class OkHttpClientUtils {
    private static OkHttpClient clientInstance;

    private OkHttpClientUtils() {
    }

    public static synchronized OkHttpClient getInstance() {
        if (clientInstance == null) {
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            clientInstance = new OkHttpClient.Builder()
                    .cookieJar(new CookieJar() {
                        private Map<String, List<Cookie>> cookieMap = new HashMap<>();

                        @Override
                        public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
                            cookieMap.put(httpUrl.host(), list);
                        }

                        @NotNull
                        @Override
                        public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
                            List<Cookie> cookies = cookieMap.get(httpUrl.host());
                            return cookies != null ? cookies : new ArrayList<Cookie>();
                        }
                    })
                    .build();
        }
        return clientInstance;
    }
}
