package com.myplatform.myplatform.routing;

import com.myplatform.myplatform.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.response.http.HttpResponse;
import com.myplatform.myplatform.response.http.HttpResponseBuilder;

import java.lang.reflect.Type;

public interface HttpEndpointHandler {

    HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder);

    Type getExpectedBodyType();

}
