package com.myplatform.myplatform.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;

public class TypeToJavaTypeAdapter {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static JavaType toType(Type type) {
        return objectMapper.constructType(type);
    }

}
