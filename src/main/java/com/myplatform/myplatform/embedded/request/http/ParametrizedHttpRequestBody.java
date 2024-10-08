package com.myplatform.myplatform.embedded.request.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myplatform.myplatform.embedded.util.TypeToJavaTypeAdapter;

import java.lang.reflect.Type;

public class ParametrizedHttpRequestBody extends HttpRequestBody{

    Type type;

    String content;

    final ObjectMapper objectMapper = new ObjectMapper();

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
            return objectMapper.readValue(content,
                    TypeToJavaTypeAdapter.toType(type));
    }


    @Override
    public String getStringRepresentation() {
        return content;
    }
}
