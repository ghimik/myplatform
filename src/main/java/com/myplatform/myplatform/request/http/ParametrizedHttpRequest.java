package com.myplatform.myplatform.request.http;

public class ParametrizedHttpRequest extends HttpRequest{

    private final ParametrizedHttpRequestBody body;

    public ParametrizedHttpRequest(HttpRequestHead head, ParametrizedHttpRequestBody body) {
        super(head);
        this.body = body;
    }

    @Override
    public HttpRequestBody getBody() {
        return body;
    }

    @Override
    public HttpRequestParser getParser() {
        return new ParametrizedHttpRequestParser();
    }
}
