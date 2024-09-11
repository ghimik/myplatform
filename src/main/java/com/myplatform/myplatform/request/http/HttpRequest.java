package com.myplatform.myplatform.request.http;

import com.myplatform.myplatform.request.AbstractRequest;
import com.myplatform.myplatform.request.RequestParser;

public abstract class HttpRequest extends AbstractRequest {

    private HttpRequestHead head;

    public abstract HttpRequestBody getBody();

    public HttpRequestHead getHead() {return head;}

    public abstract HttpRequestParser getParser();

    public HttpRequest(HttpRequestHead head) {
        this.head = head;
    }

    public HttpRequest() {

    }

}
