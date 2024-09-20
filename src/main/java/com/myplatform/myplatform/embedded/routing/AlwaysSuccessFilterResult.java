package com.myplatform.myplatform.embedded.routing;

import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBody;
import com.myplatform.myplatform.embedded.response.http.HttpResponseHead;

public class AlwaysSuccessFilterResult implements HttpFilterResult {

    @Override
    public Boolean proceed() {
        return true;
    }

    @Override
    public HttpResponse<?> errorResponse() {
        return new HttpResponse<>(new HttpResponseHead(), new HttpResponseBody<>(null));
    }
}
