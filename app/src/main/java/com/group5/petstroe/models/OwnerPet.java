package com.group5.petstroe.models;

import java.io.Serializable;

public class OwnerPet implements Serializable {
    public int id;
    public String name;
    public int species;
    public int status;
    public String birthday;
    public int price;
    public String description;
    public String url;

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
}
