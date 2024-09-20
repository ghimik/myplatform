package com.myplatform.myplatform.embedded.routing;


import com.myplatform.myplatform.embedded.response.http.HttpResponse;

public interface HttpFilterResult {

    Boolean proceed();

    HttpResponse<?> errorResponse();

}
