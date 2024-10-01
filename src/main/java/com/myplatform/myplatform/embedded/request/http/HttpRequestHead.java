package com.myplatform.myplatform.embedded.request.http;

import com.myplatform.myplatform.embedded.MyHttpHeaders;

public class HttpRequestHead {

    private MyHttpRequestMethod method;

    private String url;

    private MyHttpHeaders headers;

    private MyHttpParams params;

    public MyHttpRequestMethod getMethod() {
        return method;
    }

    public void setMethod(MyHttpRequestMethod method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MyHttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(MyHttpHeaders headers) {
        this.headers = headers;
    }


    public MyHttpParams getParams() {
        return params;
    }

    public void setParams(MyHttpParams params) {
        this.params = params;
    }


}
