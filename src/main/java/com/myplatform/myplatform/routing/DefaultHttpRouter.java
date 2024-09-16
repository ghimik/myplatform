package com.myplatform.myplatform.routing;

import com.myplatform.myplatform.request.http.HttpRequest;
import com.myplatform.myplatform.request.http.ParametrizedHttpRequest;
import com.myplatform.myplatform.response.http.HttpResponse;
import com.myplatform.myplatform.response.http.HttpResponseBuilder;

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
                .iterator();

        String url = "/" + urls.next();
        try {
            while(current != null) {
                System.out.println("Current handling " + current);

                HttpFilterSegment currentFilterSegment = current.getNavigator().getFilters();
                if (!Objects.isNull(currentFilterSegment))
                    for (HttpFilterHandler filter: current.getNavigator().getFilters().getFilters()) {
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

                System.out.println("Handled url: " +url);
                url = "/" + urls.next();
                current = current.getNavigator().navigate(url);

            }
        }
        catch (Exception ex) {
            // RETURN badrequest
            ex.printStackTrace();
        }

        return builder.build();
    }
}
