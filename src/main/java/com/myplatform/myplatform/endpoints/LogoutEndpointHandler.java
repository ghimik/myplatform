package com.myplatform.myplatform.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myplatform.myplatform.dto.LoginDto;
import com.myplatform.myplatform.embedded.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.routing.HttpEndpointHandler;
import com.myplatform.myplatform.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;

import static com.myplatform.myplatform.service.AuthenticationService.AUTHORIZATION_HEADER;

public class LogoutEndpointHandler implements HttpEndpointHandler {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder)
            throws JsonProcessingException {
        String token = request.getHead().getHeaders().getContent().get(AUTHORIZATION_HEADER);
        authenticationService.logout(token);
        return builder.build();
    }

    @Override
    public Type getExpectedBodyType() {
        return Void.TYPE;
    }
}
