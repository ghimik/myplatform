package com.myplatform.myplatform.request.http;

import java.util.Map;

public class MyHttpParams {

    private Map<String, String> params;

    public Map<String, String> getParams() {
        return params;
    }

    public MyHttpParams(Map<String, String> paramsMap) {
        this.params = paramsMap;
    }
}
