package com.myplatform.myplatform.embedded.routing;

import java.util.Optional;
import java.util.function.Supplier;

public class HttpEndpointImpl implements HttpEndpoint {

    private final HttpRouterSegment bound;

    private Optional<HttpEndpointHandler> endpoint = Optional.empty();

    public HttpEndpointImpl(HttpRouterSegment bound) {
        this.bound = bound;
    }

    public HttpEndpointImpl(HttpRouterSegment bound, HttpEndpointHandler handler) {
        this(bound);
        endpoint = Optional.of(handler);
    }

    @Override
    public HttpRouterSegment addEndpoint(Supplier<? extends HttpEndpointHandler> endpointHandler) {
        endpoint = Optional.of(endpointHandler.get());
        return bound;
    }

    @Override
    public Optional<? extends HttpEndpointHandler> getEndpoint() {
        return endpoint;
    }
}
