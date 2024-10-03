package com.myplatform.myplatform.embedded.util;

import com.myplatform.myplatform.embedded.request.http.HttpRequest;
import com.myplatform.myplatform.embedded.request.http.MyHttpParams;
import com.myplatform.myplatform.embedded.request.http.MyHttpRequestMethod;

import java.util.Map;

public class HttpRequestHelper {

    public static MyHttpRequestMethod getStatus(HttpRequest httpRequest) {
        return httpRequest.getHead().getMethod();
    }

    public static Map<String, String> getParams(HttpRequest httpRequest) {
        return httpRequest.getHead().getParams().getParams();
    }

}
