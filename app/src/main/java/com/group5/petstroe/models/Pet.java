package com.group5.petstroe.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pet implements Serializable {

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("species")
    public int species;
    @SerializedName("status")
    public int status;
    @SerializedName("birthday")
    public String birthday;
    @SerializedName("price")
    public int price;
    @SerializedName("description")
    public String description;
    @SerializedName(value = "url", alternate = {"img_url"})
    public String url;
    @SerializedName("remark")
    public String remark;
    @SerializedName("owner")
    public PetOwner owner;
    @SerializedName(value = "upTime", alternate = {"up_time"})
    public String upTime;

    public String getSpecies() {
        switch (this.species) {
            case 1:
                return "狗";
            case 2:
                return "猫";
            case 3:
                return "仓鼠";
            case 4:
                return "兔";
            case 5:
                return "恐龙";
            default:
                return "未知物种";
        }
    }

    public String getStatus() {
        switch (this.status) {
            case 0:
                return "下架";
            case 1:
                return "上架";
            default:
                return "未知状态";
        }
    }

    public int getIntId() {
        return Integer.parseInt(this.id);
    }
}
