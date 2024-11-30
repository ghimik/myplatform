package com.myplatform.myplatform.endpoints;

public class EmptyRequestException extends RuntimeException {

    private String request = "";

    public EmptyRequestException() {}

    public EmptyRequestException(String request) {
        this.request = request;
    }

    @Override
    public String getMessage() {
        return "Empty request: " + request;
    }
}
