package com.myplatform.myplatform.request.http;

public class RawHttpRequestParser implements HttpRequestParser {

    @Override
    public RawHttpRequest parse(String request) {
        String[] parts = request.split("\r\n\r\n");
        if (parts.length < 2)
            return new RawHttpRequest(parseHead(parts[0]), parseBody());
        return new RawHttpRequest(parseHead(parts[0]), parseBody(parts[1]));
    }

    @Override
    public RawHttpRequestBody parseBody(String body) {
        return new RawHttpRequestBody(body);
    }

    @Override
    public RawHttpRequestBody parseBody() {
        return new RawHttpRequestBody("");
    }

}
