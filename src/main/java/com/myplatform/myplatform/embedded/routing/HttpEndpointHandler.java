package com.myplatform.myplatform.embedded.routing;

import com.myplatform.myplatform.embedded.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;

import java.lang.reflect.Type;

public interface HttpEndpointHandler {

    HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder);

    Type getExpectedBodyType();

}
