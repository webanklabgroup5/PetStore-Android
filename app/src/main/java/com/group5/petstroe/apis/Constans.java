package com.group5.petstroe.apis;

import okhttp3.MediaType;

public class Constans {

    public static final String PROTOCOL = "http";
    public static final String HOST = "";
    public static final int PORT = 0;
    public static final MediaType CONTENT_TYPE = MediaType.parse("application/json;charset=UTF-8");

    public static final String PATH_SIGN_UP = "/userapply";
    public static final int CODE_USER_SIGN_UP_API = 101;

    public static final String PATH_SIGN_IN = "/login";
    public static final int CODE_USER_SIGN_IN_API = 102;

    public static final String PATH_SIGN_OUT = "/logout";
    public static final int CODE_USER_SIGN_OUT_API = 103;

    public static final String PATH_GET_USER_INFO = "/userinfo";
    public static final int CODE_USER_GET_USER_INFO_API = 104;

    public static final String PATH_CREATE_PET = "/petadd";
    public static final int CODE_PET_CREATE_PET_API = 201;

    public static final String PATH_CHANGE_PET_STATUS = "/petstatus";
    public static final int CODE_PET_CHANGE_PET_STATUS_API = 202;
}
