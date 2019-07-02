package com.group5.petstroe.utils;

public class StringUtils {

    public static boolean isNull(String s) {
        return s == null;
    }

    public static boolean isEmpty(String s) {
        return s != null && s.length() == 0;
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNotNullOrEmpty(String s) {
        return s != null && s.length() > 0;
    }
}
