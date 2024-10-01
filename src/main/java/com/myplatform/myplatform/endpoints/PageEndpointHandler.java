package com.myplatform.myplatform.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myplatform.myplatform.dto.PageDto;
import com.myplatform.myplatform.embedded.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.response.http.HttpResponseStatus;
import com.myplatform.myplatform.embedded.routing.HttpEndpointHandler;
import com.myplatform.myplatform.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;

public class PageEndpointHandler implements HttpEndpointHandler {

    @Autowired
    private PageService pageService;

    @Override
    public HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder)
            throws JsonProcessingException {

        PageDto body = request.getBody().parseContent();
        try {
            pageService.createPage(body.getWorkspaceId(), body.getParentPageId(), body.getTitle(), body.getContent());
            builder.setStatus(new HttpResponseStatus(201)); // Created
            return builder.build();
        } catch (Exception e) {
            e.printStackTrace();
            builder.setStatus(new HttpResponseStatus(400)); // Bad Request
            return builder.build();
        }
    }

    @Override
    public Type getExpectedBodyType() {
        return PageDto.class;
    }
}
