package com.myplatform.myplatform.request.http;


public class RawHttpRequest extends HttpRequest{

    private RawHttpRequestBody body;

    public RawHttpRequest(HttpRequestHead head, RawHttpRequestBody body) {
        super(head);
        this.body = body;
    }

    @Override
    public HttpRequestBody getBody() {
        return body;
    }

    @Override
    public HttpRequestParser getParser() {
        return new RawRequestParser();
    }

    public RawHttpRequest() {
        super();
    }


}
