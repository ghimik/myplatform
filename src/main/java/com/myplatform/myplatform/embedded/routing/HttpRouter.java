package com.myplatform.myplatform.embedded.routing;


import com.myplatform.myplatform.embedded.request.http.HttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;

public interface HttpRouter {

    HttpResponse<?> handle(HttpRequest request);

}
