package com.myplatform.myplatform.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myplatform.myplatform.dto.BlockDto;
import com.myplatform.myplatform.embedded.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.response.http.HttpResponseStatus;
import com.myplatform.myplatform.embedded.routing.HttpEndpointHandler;
import com.myplatform.myplatform.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;

public class BlockEndpointHandler implements HttpEndpointHandler {

    @Autowired
    private BlockService blockService;

    @Override
    public HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder)
            throws JsonProcessingException {

        BlockDto body = request.getBody().parseContent();
        try {
            blockService.createBlock(body.getPageId(), body.getType(), body.getContent());
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
        return BlockDto.class;
    }
}
