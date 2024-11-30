package com.myplatform.myplatform.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Map;

public class BlockEndpointHandler implements HttpEndpointHandler {

    @Autowired
    private BlockService blockService;

    @Override
    public HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder)
            throws JsonProcessingException {
        MyHttpRequestMethod method = HttpRequestHelper.getStatus(request);

        if (method == MyHttpRequestMethod.PUT) {
            return handleUpdateBlock(request, builder);
        }

        return builder.setStatus(new HttpResponseStatus(405)).build(); // Method Not Allowed
    }

    private HttpResponse<?> handleUpdateBlock(ParametrizedHttpRequest request, HttpResponseBuilder builder)
            throws JsonProcessingException {
        BlockDto body = request.getBody().parseContent();
        Integer blockId = Integer.parseInt(HttpRequestHelper.getParams(request).get("blockId"));
        Integer pageId = Integer.parseInt(HttpRequestHelper.getParams(request).get("pageId"));
        Integer workspaceId = Integer.parseInt(HttpRequestHelper.getParams(request).get("workspaceId"));


        try {
            blockService.updateBlock(blockId, pageId, workspaceId, body);
            builder.setStatus(new HttpResponseStatus(200));
            builder.setBody(Map.of("status", "Block updated successfully"));
            return builder.build();
        } catch (Exception e) {
            e.printStackTrace();
            builder.setStatus(new HttpResponseStatus(400));
            builder.setBody(Map.of("error", "Invalid block data"));
            return builder.build();
        }
    }

    @Override
    public Type getExpectedBodyType() {
        return BlockDto.class;
    }
}
