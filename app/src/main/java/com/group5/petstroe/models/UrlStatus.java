package com.group5.petstroe.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UrlStatus implements Serializable {
    @SerializedName("status")
    public int status;
    @SerializedName("url")
    public String url;
}
