package com.group5.petstroe.models;

import java.io.Serializable;

public class Order implements Serializable {
    public int id;
    public int price;
    public int status;
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
}
