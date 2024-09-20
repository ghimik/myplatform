package com.myplatform.myplatform.embedded.routing;

import com.myplatform.myplatform.embedded.request.http.HttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;

public interface HttpFilterHandler {

    HttpFilterResult filter(HttpRequest request, HttpResponseBuilder response);

}
