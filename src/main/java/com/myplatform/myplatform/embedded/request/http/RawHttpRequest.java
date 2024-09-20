package com.myplatform.myplatform.embedded.request.http;


public class RawHttpRequest extends HttpRequest{

    private RawHttpRequestBody body;

    public RawHttpRequest(HttpRequestHead head, RawHttpRequestBody body) {
        super(head);
        this.body = body;
    }

    @Override
    public RawHttpRequestBody getBody() {
        return body;
    }

    @Override
    public HttpRequestParser getParser() {
        return new RawHttpRequestParser();
    }

    public RawHttpRequest() {
        super();
    }


}
