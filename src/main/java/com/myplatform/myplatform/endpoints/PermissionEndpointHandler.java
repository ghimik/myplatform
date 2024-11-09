package com.myplatform.myplatform.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myplatform.myplatform.dto.PermissionDto;
import com.myplatform.myplatform.embedded.request.http.MyHttpRequestMethod;
import com.myplatform.myplatform.embedded.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.response.http.HttpResponseStatus;
import com.myplatform.myplatform.embedded.routing.HttpEndpointHandler;
import com.myplatform.myplatform.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;

public class PermissionEndpointHandler implements HttpEndpointHandler {

    @Autowired
    private PermissionService permissionService;

    @Override
    public HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder) throws JsonProcessingException {
        MyHttpRequestMethod method = request.getHead().getMethod();

        if (method == MyHttpRequestMethod.POST) {
            return handlePostPermission(request, builder);
        }

        return builder.setStatus(new HttpResponseStatus(405)).build();
    }

    private HttpResponse<?> handlePostPermission(ParametrizedHttpRequest request, HttpResponseBuilder builder) {
        PermissionDto permissionDto = null;
        try {
            permissionDto = request.getBody().parseContent();
        } catch (JsonProcessingException e) {
            return HttpResponse.badRequest("Your Dto is cringe");
        }
        try {
            permissionService.createPermission(permissionDto);
            builder.setStatus(new HttpResponseStatus(201));
            builder.setBody("{\"status\": \"Permission created successfully\"}");
            return builder.build();
        } catch (Exception e) {
            e.printStackTrace();
            builder.setStatus(new HttpResponseStatus(400));
            builder.setBody("{\"error\": \"Invalid permission data\"}");
            return builder.build();
        }
    }

    @Override
    public Type getExpectedBodyType() {
        return PermissionDto.class;
    }
}
