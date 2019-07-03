package com.group5.petstroe.models;

import java.io.Serializable;

public class Order implements Serializable {
    public int id;
    public int price;
    public int status;
    public OwnerPet pet;
}
