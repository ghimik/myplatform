package com.myplatform.myplatform.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myplatform.myplatform.dto.WorkspaceCreationDto;
import com.myplatform.myplatform.dto.WorkspaceDto;
import com.myplatform.myplatform.embedded.request.http.MyHttpRequestMethod;
import com.myplatform.myplatform.embedded.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.response.http.HttpResponseStatus;
import com.myplatform.myplatform.embedded.routing.HttpEndpointHandler;
import com.myplatform.myplatform.embedded.security.SecurityContext;
import com.myplatform.myplatform.embedded.util.HttpRequestHelper;
import com.myplatform.myplatform.service.AuthenticationService;
import com.myplatform.myplatform.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.List;

public class WorkspaceEndpointHandler implements HttpEndpointHandler {

    @Autowired
    private WorkspaceService workspaceService;

    @Autowired
    private SecurityContext securityContext;

    @Override
    public HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder) throws JsonProcessingException {
        MyHttpRequestMethod method = HttpRequestHelper.getStatus(request);

        switch (method) {
            case GET:
                return handleGetWorkspaces(request, builder);
            case POST:
                return handleCreateWorkspace(request, builder);
            default:
                builder.setStatus(new HttpResponseStatus(405)); // Method Not Allowed
                return builder.build();
        }
    }

    private HttpResponse<?> handleGetWorkspaces(ParametrizedHttpRequest request, HttpResponseBuilder builder) {
        String workspaceIdString = HttpRequestHelper.getParams(request).get("id");
        try {
            Integer workspaceId = Integer.valueOf(workspaceIdString);
            WorkspaceDto workspaces = workspaceService.getWorkspacesByWorkspaceId(workspaceId);
            builder.setStatus(new HttpResponseStatus(200));
            builder.setBody(workspaces);
            return builder.build();
        }
        catch (EmptyRequestException ex) {
            builder.setBody("Empty request!");
            return builder.build();
        }
        catch (Exception e) {
            builder.setStatus(new HttpResponseStatus(500)); // Internal Server Error
            builder.setBody("{\"error\": \"Internal Server Error\"}");
            return builder.build();
        }
    }

    private HttpResponse<?> handleCreateWorkspace(ParametrizedHttpRequest request, HttpResponseBuilder builder) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        WorkspaceCreationDto body = mapper.readValue(request.getBody().getStringRepresentation(),
                WorkspaceCreationDto.class);
        try {
            String uuid = request.getHead().getHeaders().getContent()
                .get(AuthenticationService.AUTHORIZATION_HEADER);
            String username = securityContext.getAuthentication(uuid).getPrincipal().getUsername();

            workspaceService.createWorkspace(username, body.getName());
            builder.setStatus(new HttpResponseStatus(201)); // Created
            builder.setBody("{\"status\": \"Workspace created successfully\"}");
            return builder.build();
        } catch (Exception e) {
            builder.setStatus(new HttpResponseStatus(400)); // Bad Request
            builder.setBody("{\"error\": \"Invalid data for workspace creation\"}");
            return builder.build();
        }
    }

    @Override
    public Type getExpectedBodyType() {
        return WorkspaceDto.class;
    }
}
