package com.group5.petstroe.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Arbitration implements Serializable {

    public class User implements Serializable {
        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName(value = "balance", alternate = {"credit"})
        public String balance;

        public int getIntId() {
            return Integer.parseInt(this.id);
        }
    }

    public String id;
    public int price;
    public String date;
    User seller;
    User buyer;
    OwnerPet pet;
}
