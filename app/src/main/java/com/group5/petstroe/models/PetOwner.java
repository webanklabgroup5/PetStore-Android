package com.group5.petstroe.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PetOwner implements Serializable {
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName(value = "balance", alternate = {"credit"})
    public int balance;

    public int getIntId() {
        return Integer.parseInt(this.id);
    }
}
