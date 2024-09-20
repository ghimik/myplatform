package com.myplatform.myplatform.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myplatform.myplatform.dto.TestDto;
import com.myplatform.myplatform.embedded.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.routing.HttpEndpointHandler;

import java.lang.reflect.Type;
import java.util.Arrays;

public class TestBodyEndpoint implements HttpEndpointHandler {
    @Override
    public HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder) {
        try {
            TestDto body = request.getBody().parseContent();
            System.out.println("Test Body endpoint is working! parsed:");
            System.out.println(body.getStringTest());
            System.out.println(Arrays.toString(body.getArrTest()));
            return builder.setBody(body.getStringTest()).build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Type getExpectedBodyType() {
        return TestDto.class;
    }
}
