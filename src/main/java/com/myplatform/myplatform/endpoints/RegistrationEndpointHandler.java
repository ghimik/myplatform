package com.myplatform.myplatform.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myplatform.myplatform.dto.RegisterDto;
import com.myplatform.myplatform.embedded.request.http.HttpRequest;
import com.myplatform.myplatform.embedded.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.response.http.HttpResponseStatus;
import com.myplatform.myplatform.embedded.routing.HttpEndpointHandler;
import com.myplatform.myplatform.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class RegistrationEndpointHandler implements HttpEndpointHandler {

    @Autowired
    private AuthenticationService authenticationService;


    @Override
    public HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder) throws JsonProcessingException {
        RegisterDto body = request.getBody().parseContent();
        String username = body.getUsername();
        String password = body.getPassword();

        try {
            authenticationService.register(username, password);
            builder.setStatus(new HttpResponseStatus(200));
            return builder.build();
        } catch (Exception e) {
            builder.setStatus(new HttpResponseStatus(400));
            return builder.build();
        }
    }

    @Override
    public Type getExpectedBodyType() {
        return RegisterDto.class;
    }
}
