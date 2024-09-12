package com.myplatform.myplatform.routing;

import com.myplatform.myplatform.request.http.HttpRequest;
import com.myplatform.myplatform.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.response.http.HttpResponseStatus;

public class TestFilterHandler implements HttpFilterHandler {

    @Override
    public HttpFilterResult filter(HttpRequest request, HttpResponseBuilder response) {
        System.out.println("Test filter is working");
        response.setStatus(new HttpResponseStatus(200));
        response.addHeader("Test-Header", "Value");
        return new AlwaysSuccessFilterResult();
    }

}
