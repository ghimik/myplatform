package com.myplatform.myplatform.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myplatform.myplatform.dto.LoginDto;
import com.myplatform.myplatform.embedded.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.response.http.HttpResponseStatus;
import com.myplatform.myplatform.embedded.routing.HttpEndpointHandler;
import com.myplatform.myplatform.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.naming.AuthenticationException;
import java.lang.reflect.Type;

public class LoginEndpointHandler implements HttpEndpointHandler {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder)
            throws JsonProcessingException {

        LoginDto body = request.getBody().parseContent();
        try {
            String token = authenticationService.authenticate(body.getUsername(), body.getPassword());
            builder.setStatus(new HttpResponseStatus(200));
            builder.addHeader(AuthenticationService.AUTHORIZATION_HEADER, token);
            return builder.build();
        } catch (AuthenticationException e) {
            e.printStackTrace();
            builder.setStatus(new HttpResponseStatus(400));
            return builder.build();
        }

    }

    @Override
    public Type getExpectedBodyType() {
        return LoginDto.class;
    }
}
