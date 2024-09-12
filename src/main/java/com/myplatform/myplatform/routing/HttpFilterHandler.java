package com.myplatform.myplatform.routing;

import com.myplatform.myplatform.request.http.HttpRequest;
import com.myplatform.myplatform.response.http.HttpResponseBuilder;

public interface HttpFilterHandler {

    HttpFilterResult filter(HttpRequest request, HttpResponseBuilder response);

}
