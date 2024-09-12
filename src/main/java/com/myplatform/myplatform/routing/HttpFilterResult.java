package com.myplatform.myplatform.routing;

import com.myplatform.myplatform.response.http.HttpResponse;

public interface HttpFilterResult {

    Boolean proceed();

    HttpResponse<?> errorResponse();

}
