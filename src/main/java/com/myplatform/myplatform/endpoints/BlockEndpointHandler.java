package com.myplatform.myplatform.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myplatform.myplatform.dto.BlockDto;
import com.myplatform.myplatform.embedded.request.http.MyHttpRequestMethod;
import com.myplatform.myplatform.embedded.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.response.http.HttpResponseStatus;
import com.myplatform.myplatform.embedded.routing.HttpEndpointHandler;
import com.myplatform.myplatform.embedded.util.HttpRequestHelper;
import com.myplatform.myplatform.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;

public class BlockEndpointHandler implements HttpEndpointHandler {

    @Autowired
    private BlockService blockService;

    @Override
    public HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder)
            throws JsonProcessingException {
        MyHttpRequestMethod method = HttpRequestHelper.getStatus(request);

        switch (method) {
            case GET:
                return handleGetBlock(request, builder);
            case PUT:
                return handleUpdateBlock(request, builder);
            case DELETE:
                return handleDeleteBlock(request, builder);
            default:
                return builder.setStatus(new HttpResponseStatus(405)).build(); // Method Not Allowed
        }
    }

    private HttpResponse<?> handleGetBlock(ParametrizedHttpRequest request, HttpResponseBuilder builder) {
        String blockId = HttpRequestHelper.getParams(request).get("id");
        try {
            BlockDto block = blockService.getBlockById(Integer.valueOf(blockId));
            builder.setStatus(new HttpResponseStatus(200));
            builder.setBody(block);
            return builder.build();
        } catch (Exception e) {
            builder.setStatus(new HttpResponseStatus(404)); // Not Found
            return builder.build();
        }
    }

    private HttpResponse<?> handleUpdateBlock(ParametrizedHttpRequest request, HttpResponseBuilder builder) throws JsonProcessingException {
        BlockDto body = request.getBody().parseContent();
        String blockId = HttpRequestHelper.getParams(request).get("id");
        try {
            blockService.updateBlock(Integer.valueOf(blockId), body.getContent());
            builder.setStatus(new HttpResponseStatus(200)); // OK
            return builder.build();
        } catch (Exception e) {
            builder.setStatus(new HttpResponseStatus(400)); // Bad Request
            return builder.build();
        }
    }

    private HttpResponse<?> handleDeleteBlock(ParametrizedHttpRequest request, HttpResponseBuilder builder) {
        String blockId = HttpRequestHelper.getParams(request).get("id");
        try {
            blockService.deleteBlock(Integer.valueOf(blockId));
            builder.setStatus(new HttpResponseStatus(200)); // OK
            return builder.build();
        } catch (Exception e) {
            builder.setStatus(new HttpResponseStatus(400)); // Bad Request
            return builder.build();
        }
    }


    @Override
    public Type getExpectedBodyType() {
        return BlockDto.class;
    }
}
