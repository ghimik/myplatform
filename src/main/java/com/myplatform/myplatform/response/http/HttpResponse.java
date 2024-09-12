package com.myplatform.myplatform.response.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myplatform.myplatform.response.AbstractResponse;

public class HttpResponse<T> extends AbstractResponse {

    private final HttpResponseHead head;

    private final HttpResponseBody<T> body;

    public HttpResponseHead getHead() {
        return head;
    }

    public HttpResponseBody<T> getBody() {
        return body;
    }

    public HttpResponse(HttpResponseHead head, HttpResponseBody<T> body) {
        this.head = head;
        this.body = body;
    }

    @Override
    public String getFormatted() {
        StringBuilder formattedResponse = new StringBuilder();
        ObjectMapper objectMapper = new ObjectMapper();

        formattedResponse.append("HTTP/1.1 ")
                         .append(head.getStatus().getCode()).append(" ")
                         .append(head.getStatus().getDescription()).append("\r\n");

        if (head.getHeaders() != null) {
            head.getHeaders().getContent().forEach((key, value) ->
                formattedResponse.append(key).append(": ").append(value).append("\r\n"));
        }

        if (body != null) {
            formattedResponse.append("\r\n");

            try {
                formattedResponse.append(objectMapper.writeValueAsString(body));
            } catch (JsonProcessingException ignored) {
                System.out.println("Error while serializing body");
            }
        }

        return formattedResponse.toString();
    }

    @Override
    public HttpResponseBuilder getBuilder() {
        return new HttpResponseBuilder();
    }
}
