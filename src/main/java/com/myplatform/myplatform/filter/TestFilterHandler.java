package com.myplatform.myplatform.filter;


import com.myplatform.myplatform.embedded.request.http.HttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.response.http.HttpResponseStatus;
import com.myplatform.myplatform.embedded.routing.AlwaysSuccessFilterResult;
import com.myplatform.myplatform.embedded.routing.HttpFilterHandler;
import com.myplatform.myplatform.embedded.routing.HttpFilterResult;

public class TestFilterHandler implements HttpFilterHandler {

    @Override
    public HttpFilterResult filter(HttpRequest request, HttpResponseBuilder response) {
        System.out.println("Test filter is working");
        response.setStatus(new HttpResponseStatus(200));
        response.addHeader("Test-Header", "Value");
        return new AlwaysSuccessFilterResult();
    }

}
