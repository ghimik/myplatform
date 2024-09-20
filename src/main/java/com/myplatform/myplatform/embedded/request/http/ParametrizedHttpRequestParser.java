package com.myplatform.myplatform.embedded.request.http;

public class ParametrizedHttpRequestParser implements HttpRequestParser{


    @Override
    public ParametrizedHttpRequest parse(String request) {
        String[] parts = request.split("\r\n\r\n");
        if (parts.length < 2)
            return new ParametrizedHttpRequest(parseHead(parts[0]), parseBody());
        return new ParametrizedHttpRequest(parseHead(parts[0]), parseBody(parts[1]));
    }

    @Override
    public ParametrizedHttpRequestBody parseBody(String body) {
        return new ParametrizedHttpRequestBody(body);
    }

    @Override
    public ParametrizedHttpRequestBody parseBody() {
        return new ParametrizedHttpRequestBody("");
    }
}
