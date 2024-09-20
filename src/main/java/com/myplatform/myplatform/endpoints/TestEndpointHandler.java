package com.myplatform.myplatform.endpoints;


import com.myplatform.myplatform.embedded.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.routing.HttpEndpointHandler;

import java.lang.reflect.Type;

public class TestEndpointHandler implements HttpEndpointHandler {

    @Override
    public HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder) {
        System.out.println("Test endpoint is working");
        return builder.setBody(request.getBody()).build();
    }

    @Override
    public Type getExpectedBodyType() {
        return String.class;
    }

}
