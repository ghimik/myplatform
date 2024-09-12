package com.myplatform.myplatform.routing;

import com.myplatform.myplatform.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.response.http.HttpResponse;
import com.myplatform.myplatform.response.http.HttpResponseBuilder;

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
