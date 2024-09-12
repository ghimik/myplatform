package com.myplatform.myplatform.response.http;

public class HttpResponseStatus {

    private Integer code;

    public HttpResponseStatus(Integer code) {
        this.code = code;
    }

    public HttpResponseStatus() {}

    public Integer getCode() {
        return code;
    }

    public void getCode(Integer status) {
        this.code = status;
    }

    public String getDescription() {
        return switch (code) {
            case 200 -> "OK";
            // ..
            case 400 -> "Bad request";
            case 500 -> "Internal Server Error";
            default -> "";
        };
    }
}
