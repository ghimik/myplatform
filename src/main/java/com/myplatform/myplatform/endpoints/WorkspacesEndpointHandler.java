package com.myplatform.myplatform.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myplatform.myplatform.dto.WorkspacesDto;
import com.myplatform.myplatform.embedded.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.routing.HttpEndpointHandler;
import com.myplatform.myplatform.embedded.security.SecurityContext;
import com.myplatform.myplatform.embedded.security.SecurityContextImpl;
import com.myplatform.myplatform.service.AuthenticationService;
import com.myplatform.myplatform.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;

public class WorkspacesEndpointHandler implements HttpEndpointHandler {

    @Autowired
    private WorkspaceService workspaceService;

    @Autowired
    private SecurityContext securityContext;

    @Override
    public HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder)
            throws JsonProcessingException {

        String uuid = request.getHead().getHeaders().getContent()
                .get(AuthenticationService.AUTHORIZATION_HEADER);
        String username = securityContext.getAuthentication(uuid).getPrincipal().getUsername();
        builder.setBody(workspaceService.getWorkspacesList(username));

        return builder.build();
    }

    @Override
    public Type getExpectedBodyType() {
        return WorkspacesDto.class;
    }
}
