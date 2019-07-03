package com.group5.petstroe.models;

import java.io.Serializable;

public class Status implements Serializable {
    public int status;

    public boolean isOk() {
        return status == 1;
    }
}
