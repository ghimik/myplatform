package com.myplatform.myplatform.response.http;

import com.myplatform.myplatform.MyHttpHeaders;
import com.myplatform.myplatform.response.AbstractResponse;
import com.myplatform.myplatform.response.AbstractResponseBuilder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HttpResponseBuilder extends AbstractResponseBuilder {

    private HttpResponseStatus status = new HttpResponseStatus(500);

    private final Map<String, String> headers = new HashMap<>();

    private Type bodyType;

    private Object body;

    public HttpResponseBuilder addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public HttpResponseBuilder setStatus(HttpResponseStatus status) {
        this.status = status;
        return this;
    }

    public <T> HttpResponseBuilder setBody(T body) {
        this.body = body;
        this.bodyType = body.getClass();
        return this;
    }


    @Override
    public HttpResponse<?> build() {
        var head = new HttpResponseHead(
                        new MyHttpHeaders(headers),
                        status
                );
        if (Objects.isNull(body))
            return new HttpResponse<>(head, HttpResponseBody.EMPTY_BODY);
        else
            return new HttpResponse<>(head, HttpResponseBody.fromType(body));
    }
}
