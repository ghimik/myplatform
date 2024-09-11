package com.myplatform.myplatform;

import java.util.Map;

public class MyHttpHeaders {

    private Map<String, String> content;

    public MyHttpHeaders(Map<String, String> headers) {
        this.content = headers;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }
}
