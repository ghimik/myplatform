package com.myplatform.myplatform.embedded.routing;

import com.myplatform.myplatform.embedded.request.http.HttpRequest;
import com.myplatform.myplatform.embedded.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

public class DefaultHttpRouter implements HttpRouter {

    private final HttpRouterSegment root;

    public DefaultHttpRouter(HttpRouterSegment root) {
        this.root = root;
    }

    public HttpResponse<?> handle(HttpRequest request) {
        HttpResponseBuilder builder = new HttpResponseBuilder();

        HttpRouterSegment current = root;
        Iterator<String> urls = Arrays
                .stream(request.getHead().getUrl().split("/"))
                .map(DefaultHttpRouter::removeQueryString)
                .iterator();

        String url = "/" + urls.next();
        try {
            while (current != null) {
                System.out.println("Current handling " + current);

                HttpFilterSegment currentFilterSegment = current.getNavigator().getFilters();
                if (!Objects.isNull(currentFilterSegment))
                    for (HttpFilterHandler filter : current.getNavigator().getFilters().getFilters()) {
                        System.out.println("Filter " + filter + " - working!");

                        HttpFilterResult result = filter.filter(request, builder);
                        if (!result.proceed())
                            return result.errorResponse();

                        System.out.println("Filter " + filter + " - OK!");
                    }

                Optional<? extends HttpEndpointHandler> endpoint
                        = current.getNavigator().getEndpoint().getEndpoint();
                if (endpoint.isPresent())
                    return endpoint.get().handle(
                            ParametrizedHttpRequest.fromRaw(request,
                                    endpoint.get().getExpectedBodyType()),
                            builder
                    );

                System.out.println("Handled url: " + url);
                url = "/" + urls.next();
                current = current.getNavigator().navigate(url);

            }
            if (urls.hasNext())
                return HttpResponse.badRequest("Invalid request data");

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return HttpResponse.badRequest("Malformed URL");
        } catch (Exception ex) {
            ex.printStackTrace();
            return HttpResponse.internalServerError("Unexpected error occurred");

        }

        return builder.build();
    }

     private static String removeQueryString(String url) {
        int queryIndex = url.indexOf('?');
        if (queryIndex != -1) {
            return url.substring(0, queryIndex);
        }
        return url;
    }
}
