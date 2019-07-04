package com.group5.petstroe.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    @SerializedName("id")
    public String id;
    @SerializedName(value = "name", alternate = "user_name")
    public String name;
    @SerializedName(value = "role", alternate = {"type"})
    public int role;
    @SerializedName(value = "balance", alternate = {"credit"})
    public int balance;
    @SerializedName(value = "pets", alternate = {"pet_list", "petList"})
    public List<OwnerPet> pets;

    public int getIntId() {
        return Integer.parseInt(this.id);
    }
}
