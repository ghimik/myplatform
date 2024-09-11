package com.myplatform.myplatform.request.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myplatform.myplatform.util.TypeToJavaTypeAdapter;

import java.lang.reflect.Type;

public class ParametrizedHttpRequestBody extends HttpRequestBody{

    Type type;

    String content;

    public ParametrizedHttpRequestBody(Type type, String content) {
        this(content);
        this.type = type;
    }

    public ParametrizedHttpRequestBody(String content) {
        this.content = content;
    }

     public <T> T parseContent() throws JsonProcessingException {
            if (type == null)
                throw new NullPointerException("Type is not defined");
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(content,
                    TypeToJavaTypeAdapter.toType(type));
        }
}
