package com.group5.petstroe.apis;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private enum ResultType {
        Ok, Err
    }

    private ResultType type;
    private T inner;
    private String msg;

    private Result() {}
    private Result(T t) {
        this.type = ResultType.Ok;
        this.inner = t;
    }
    private Result(String msg) {
        this.type = ResultType.Err;
        this.msg = msg;
    }

    static <T> Result<T> ok(T t) {
        return new Result<>(t);
    }
    static <T> Result<T> err(String msg) {
        return new Result<>(msg);
    }

    public boolean isOk() {
        return this.type == ResultType.Ok;
    }

    public T get() {
        return this.inner;
    }

    public String getErrMsg() {
        return this.msg;
    }
}
