package com.myplatform.myplatform.embedded.request.http;

public class RawHttpRequestBody extends HttpRequestBody {

    private final String body;

    public RawHttpRequestBody(String body) {
        this.body = body;
    }

    @Override
    public String getStringRepresentation() {
        return body;
    }
}
