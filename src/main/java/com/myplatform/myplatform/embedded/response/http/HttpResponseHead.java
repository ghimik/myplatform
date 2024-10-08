package com.myplatform.myplatform.embedded.response.http;

import com.myplatform.myplatform.embedded.MyHttpHeaders;

public class HttpResponseHead {

    private HttpResponseStatus status;

    private MyHttpHeaders headers;

    public HttpResponseHead(MyHttpHeaders headers, HttpResponseStatus status) {
        this.headers = headers;
        this.status = status;
    }

    public HttpResponseHead() {

    }

    public MyHttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(MyHttpHeaders headers) {
        this.headers = headers;
    }

    public HttpResponseStatus getStatus() {
        return status;
    }

    public void setStatus(HttpResponseStatus status) {
        this.status = status;
    }
}
