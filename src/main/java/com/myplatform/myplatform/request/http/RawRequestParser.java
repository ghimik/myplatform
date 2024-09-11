package com.myplatform.myplatform.request.http;

public class RawRequestParser implements HttpRequestParser {

    @Override
    public RawHttpRequest parse(String request) {
        String[] parts = request.split("\r\n\r\n");
        return new RawHttpRequest(parseHead(parts[0]), parseBody(parts[1]));
    }

    @Override
    public RawHttpRequestBody parseBody(String body) {
        return new RawHttpRequestBody(body);
    }

}
