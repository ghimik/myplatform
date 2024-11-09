package com.myplatform.myplatform.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myplatform.myplatform.dto.PageDto;
import com.myplatform.myplatform.embedded.request.http.MyHttpRequestMethod;
import com.myplatform.myplatform.embedded.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.response.http.HttpResponseStatus;
import com.myplatform.myplatform.embedded.routing.HttpEndpointHandler;
import com.myplatform.myplatform.embedded.util.HttpRequestHelper;
import com.myplatform.myplatform.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.Map;

public class PageEndpointHandler implements HttpEndpointHandler {

    @Autowired
    private PageService pageService;

    @Override
    public HttpResponse<?> handle(ParametrizedHttpRequest request, HttpResponseBuilder builder)
            throws JsonProcessingException {
        MyHttpRequestMethod method = HttpRequestHelper.getStatus(request);

        switch (method) {
            case GET:
                return handleGetPage(request, builder);
            case PUT:
                return handleUpdatePage(request, builder);
            case DELETE:
                return handleDeletePage(request, builder);
            default:
                return builder.setStatus(new HttpResponseStatus(405)).build(); // Method Not Allowed
        }
    }

    private HttpResponse<?> handleGetPage(ParametrizedHttpRequest request, HttpResponseBuilder builder) {
        Integer pageId = Integer.parseInt(HttpRequestHelper.getParams(request).get("pageId"));
        Integer workspaceId = Integer.parseInt(HttpRequestHelper.getParams(request).get("workspaceId"));

        try {
            PageDto pageDto = pageService.getPageByIdAndWorkspace(pageId, workspaceId);
            builder.setStatus(new HttpResponseStatus(200));
            builder.setBody(pageDto);
            return builder.build();
        } catch (Exception e) {
            builder.setStatus(new HttpResponseStatus(404));
            builder.setBody(Map.of("error", "Page not found"));
            return builder.build();
        }
    }

    private HttpResponse<?> handleUpdatePage(ParametrizedHttpRequest request, HttpResponseBuilder builder) throws JsonProcessingException {
        PageDto body = request.getBody().parseContent();
        Integer pageId = Integer.parseInt(HttpRequestHelper.getParams(request).get("pageId"));
        Integer workspaceId = Integer.parseInt(HttpRequestHelper.getParams(request).get("workspaceId"));

        try {
            pageService.updatePage(pageId, workspaceId, body);
            builder.setStatus(new HttpResponseStatus(200));
            builder.setBody(Map.of("status", "Page updated successfully"));
            return builder.build();
        } catch (Exception e) {
            builder.setStatus(new HttpResponseStatus(400));
            builder.setBody(Map.of("error", "Invalid page data"));
            return builder.build();
        }
    }

    private HttpResponse<?> handleDeletePage(ParametrizedHttpRequest request, HttpResponseBuilder builder) {
        Integer pageId = Integer.parseInt(HttpRequestHelper.getParams(request).get("pageId"));
        Integer workspaceId = Integer.parseInt(HttpRequestHelper.getParams(request).get("workspaceId"));

        try {
            pageService.deletePage(pageId, workspaceId);
            builder.setStatus(new HttpResponseStatus(200));
            builder.setBody(Map.of("status", "Page deleted successfully"));
            return builder.build();
        } catch (Exception e) {
            builder.setStatus(new HttpResponseStatus(400));
            builder.setBody(Map.of("error", "Invalid page ID"));
            return builder.build();
        }
    }


    @Override
    public Type getExpectedBodyType() {
        return PageDto.class;
    }
}
