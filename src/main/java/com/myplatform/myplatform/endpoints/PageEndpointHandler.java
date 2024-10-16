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
        String pageId = HttpRequestHelper.getParams(request).get("id");
        try {
            PageDto page = pageService.getPageById(Integer.valueOf(pageId));
            builder.setStatus(new HttpResponseStatus(200));
            builder.setBody(page);
            return builder.build();
        } catch (Exception e) {
            builder.setStatus(new HttpResponseStatus(404)); // Not Found
            return builder.build();
        }
    }

    private HttpResponse<?> handleUpdatePage(ParametrizedHttpRequest request, HttpResponseBuilder builder) throws JsonProcessingException {
        PageDto body = request.getBody().parseContent();
        String pageId = HttpRequestHelper.getParams(request).get("id");
        try {
            pageService.updatePage(Integer.valueOf(pageId), body.getTitle(), body.getContent());
            builder.setStatus(new HttpResponseStatus(200)); // OK
            return builder.build();
        } catch (Exception e) {
            builder.setStatus(new HttpResponseStatus(400)); // Bad Request
            return builder.build();
        }
    }

    private HttpResponse<?> handleDeletePage(ParametrizedHttpRequest request, HttpResponseBuilder builder) {
        String pageId = HttpRequestHelper.getParams(request).get("id");
        try {
            pageService.deletePage(Integer.valueOf(pageId));
            builder.setStatus(new HttpResponseStatus(200)); // OK
            return builder.build();
        } catch (Exception e) {
            builder.setStatus(new HttpResponseStatus(400)); // Bad Request
            return builder.build();
        }
    }


    @Override
    public Type getExpectedBodyType() {
        return PageDto.class;
    }
}
