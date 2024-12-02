package com.myplatform.myplatform.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myplatform.myplatform.dto.PageDto;
import com.myplatform.myplatform.dto.PagesDto;
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
            case POST:
                return handleAddPage(request, builder);

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
        ObjectMapper objectMapper = new ObjectMapper();
        PageDto body = objectMapper.readValue(request.getBody().getStringRepresentation(), PageDto.class);
        Integer pageId = Integer.parseInt(HttpRequestHelper.getParams(request).get("pageId"));
        Integer workspaceId = Integer.parseInt(HttpRequestHelper.getParams(request).get("workspaceId"));

        try {
            pageService.updatePage(pageId, workspaceId, body);
            builder.setStatus(new HttpResponseStatus(200));
            builder.setBody(Map.of("status", "Page updated successfully"));
            return builder.build();
        } catch (Exception e) {
            e.printStackTrace();
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

    private HttpResponse<?> handleAddPage(ParametrizedHttpRequest request, HttpResponseBuilder builder) throws JsonProcessingException {
        PagesDto body = request.getBody().parseContent();
        Integer workspaceId = Integer.parseInt(HttpRequestHelper.getParams(request).get("workspaceId"));

        try {
            pageService.addPages(workspaceId, body);
            builder.setStatus(new HttpResponseStatus(201)); // Created
            return builder.build();
        } catch (Exception e) {
            e.printStackTrace();
            builder.setStatus(new HttpResponseStatus(400));
            builder.setBody(Map.of("error", "Failed to create page"));
            return builder.build();
        }
    }


    @Override
    public Type getExpectedBodyType() {
        return PagesDto.class;
    }
}
