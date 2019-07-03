package com.group5.petstroe.models;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    public int id;
    public int status;
    public String name;
    public int role;
    public int balance;
    public List<OwnerPet> pets;
}
