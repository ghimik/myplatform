package com.myplatform.myplatform.embedded.response.http;

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
        switch (code) {
            case 200:
                return "OK";
            case 201:
                return "Created";
            // ..
            case 400:
                return "Bad request";
            case 404:
                return "Not found";
            case 500:
                return "Internal Server Error";
            default:
                return "";
        }
    }
}
