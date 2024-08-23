package com.udacity.jwdnd.course1.cloudstorage.dto;

public class ResponseDownload<T> extends Response {
    private T data;

    public ResponseDownload(String message, boolean isSuccess) {
        super(message, isSuccess);
    }

    public ResponseDownload(String message, boolean isSuccess, T data) {
        super(message, isSuccess);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
