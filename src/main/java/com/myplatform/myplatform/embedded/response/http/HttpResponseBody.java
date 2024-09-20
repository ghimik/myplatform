package com.myplatform.myplatform.embedded.response.http;

import java.lang.reflect.Type;
import java.util.Objects;

public class HttpResponseBody<T> {

    public static final HttpResponseBody<String> EMPTY_BODY = new HttpResponseBody<>("");

    private final T content;

    public HttpResponseBody(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public static <T> HttpResponseBody<T> fromType(T value) {
        if (value == null) {
            return new HttpResponseBody<>(null);
        }
        return new HttpResponseBody<>(value);
    }

}
