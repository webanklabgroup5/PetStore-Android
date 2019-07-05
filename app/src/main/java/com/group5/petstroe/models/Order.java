package com.group5.petstroe.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order implements Serializable {
    @SerializedName("id")
    public String id;
    @SerializedName("price")
    public int price;
    @SerializedName(value = "status", alternate = {"trade_status"})
    public int status;
    @SerializedName("pet")
    public OwnerPet pet;

    public String getStatus() {
        switch (this.status) {
            case 1:
                return "仲裁中";
            case 21:
                return "仲裁成功";
            case 20:
                return "仲裁失败";
            default:
                return "未知状态";
        }
    }

    public int getIntId() {
        return Integer.parseInt(this.id);
    }
}
