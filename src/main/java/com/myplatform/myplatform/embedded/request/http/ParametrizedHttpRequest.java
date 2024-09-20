package com.myplatform.myplatform.embedded.request.http;


import java.lang.reflect.Type;

public class ParametrizedHttpRequest extends HttpRequest {

    private final ParametrizedHttpRequestBody body;

    public ParametrizedHttpRequest(HttpRequestHead head, ParametrizedHttpRequestBody body) {
        super(head);
        this.body = body;
    }

    @Override
    public ParametrizedHttpRequestBody getBody() {
        return body;
    }

    @Override
    public HttpRequestParser getParser() {
        return new ParametrizedHttpRequestParser();
    }

    public static ParametrizedHttpRequest fromRaw(HttpRequest request, Type type) {
        return new ParametrizedHttpRequest(
                request.getHead(),
                new ParametrizedHttpRequestBody(type, request.getBody().getStringRepresentation())
        );
    }
}
