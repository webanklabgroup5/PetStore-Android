package com.group5.petstroe.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorUtils {
    private static ExecutorService singleThreadExecutor;
    private static ExecutorService cachedThreadPool;

    private ExecutorUtils() {}

    public static synchronized ExecutorService getSingleThreadExecutor() {
        if (singleThreadExecutor == null) {
            singleThreadExecutor = Executors.newSingleThreadExecutor();
        }
        return singleThreadExecutor;
    }

    public static synchronized ExecutorService getCachedThreadPool() {
        if (cachedThreadPool == null) {
            cachedThreadPool = Executors.newCachedThreadPool();
        }
        return cachedThreadPool;
    }
}
