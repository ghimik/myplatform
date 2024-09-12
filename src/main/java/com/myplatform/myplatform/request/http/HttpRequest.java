package com.myplatform.myplatform.request.http;

import com.myplatform.myplatform.request.AbstractRequest;

public abstract class HttpRequest extends AbstractRequest {

    private HttpRequestHead head;

    public abstract HttpRequestBody getBody();

    public HttpRequestHead getHead() {return head;}

    @Override
    public abstract HttpRequestParser getParser();

    public HttpRequest(HttpRequestHead head) {
        this.head = head;
    }

    public HttpRequest() {

    }

}
