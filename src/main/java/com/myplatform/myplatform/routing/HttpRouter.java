package com.myplatform.myplatform.routing;

import com.myplatform.myplatform.request.http.HttpRequest;
import com.myplatform.myplatform.response.http.HttpResponse;

public interface HttpRouter {

    HttpResponse<?> handle(HttpRequest request);

}
