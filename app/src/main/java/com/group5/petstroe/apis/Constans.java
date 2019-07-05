package com.group5.petstroe.apis;

import okhttp3.MediaType;

public class Constans {

    public static final String PROTOCOL = "http";
    public static final String HOST = "ali.theproudsoul.cn";
    public static final int PORT = 22222;
    public static final MediaType CONTENT_TYPE = MediaType.parse("application/json;charset=UTF-8");

    public static final String PATH_APP_UPLOAD_FILE = "/upload";
    public static final int CODE_APP_UPLOAD_FILE_API = 1;

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

    public static final String PATH_GET_PET_LIST = "/user/petlist";
    public static final int CODE_PET_GET_PET_LIST_API = 203;

    public static final String PATH_GET_ON_SALE_PET = "/market/petlist";
    public static final int CODE_STORE_GET_ON_SALE_PET_API = 301;

    public static final String PATH_BUY_PET = "/purchase";
    public static final int CODE_STORE_BUY_PET_API = 302;

    public static final String PATH_GET_ORDER_LIST = "/user/tradelist";
    public static final int CODE_ORDER_GET_ORDER_LIST_API = 401;

    public static final String PATH_ARBITRATION = "/arbitration";
    public static final int CODE_ORDER_ARBITRATION_API = 402;

    public static final String PATH_GET_ARBITRATION_LIST = "arbitrationlist";
    public static final int CODE_ORDER_GET_ARBITRATION_LIST_API = 403;
}
