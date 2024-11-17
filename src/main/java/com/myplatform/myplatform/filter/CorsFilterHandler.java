package com.myplatform.myplatform.filter;


import com.myplatform.myplatform.embedded.request.http.HttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.response.http.HttpResponseStatus;
import com.myplatform.myplatform.embedded.routing.AlwaysSuccessFilterResult;
import com.myplatform.myplatform.embedded.routing.HttpFilterHandler;
import com.myplatform.myplatform.embedded.routing.HttpFilterResult;

public class CorsFilterHandler implements HttpFilterHandler {

    @Override
    public HttpFilterResult filter(HttpRequest request, HttpResponseBuilder response) {
        response.setStatus(new HttpResponseStatus(200));
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Custom-Header");
        response.addHeader("Access-Control-Allow-Credentials", "true");

        return new AlwaysSuccessFilterResult();
    }

}
