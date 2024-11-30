package com.myplatform.myplatform.embedded.request.http;

import com.myplatform.myplatform.embedded.MyHttpHeaders;
import com.myplatform.myplatform.embedded.request.RequestParser;
import com.myplatform.myplatform.endpoints.EmptyRequestException;

import java.util.HashMap;
import java.util.Map;

public interface HttpRequestParser extends RequestParser {

    default HttpRequestHead parseHead(String requestHead) {
        HttpRequestHead head = new HttpRequestHead();

        String[] lines = requestHead.split("\r\n");
        if (lines.length == 0) {
            throw new EmptyRequestException(requestHead);
        }
        String[] requestLine = lines[0].split(" ");

        MyHttpRequestMethod method = MyHttpRequestMethod.valueOf(requestLine[0]);
        head.setMethod(method);

        String url = requestLine[1];
        String[] urlParts = url.split("\\?");
        head.setUrl(url);

        if (urlParts.length > 1) {
            head.setParams(parseParams(urlParts[1]));
        }

        Map<String, String> headers = new HashMap<>();
        int i = 1;
        while (i < lines.length && !lines[i].isEmpty()) {
            String[] header = lines[i].split(": ");
            headers.put(header[0], header[1]);
            i++;
        }
        head.setHeaders(new MyHttpHeaders(headers));
        return head;
    }

    HttpRequest parse(String request);

    HttpRequestBody parseBody(String body);

    HttpRequestBody parseBody();

    static MyHttpParams parseParams(String paramString) {
        Map<String, String> params = new HashMap<>();
        String[] paramPairs = paramString.split("&");
        for (String pair : paramPairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                params.put(keyValue[0], keyValue[1]);
            } else if (keyValue.length == 1) {
                params.put(keyValue[0], "");
            }
        }
        return new MyHttpParams(params);
    }


}
