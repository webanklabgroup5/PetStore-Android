package com.group5.petstroe.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Status implements Serializable {
    @SerializedName("status")
    public int status;

    public boolean isOk() {
        return status == 1;
    }
}
