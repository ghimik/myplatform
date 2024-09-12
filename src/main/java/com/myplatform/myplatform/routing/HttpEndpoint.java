package com.myplatform.myplatform.routing;

import java.util.Optional;
import java.util.function.Supplier;

public interface HttpEndpoint {

    HttpRouterSegment addEndpoint(Supplier<? extends HttpEndpointHandler> endpointHandler);

    Optional<? extends HttpEndpointHandler> getEndpoint();

    HttpEndpoint NO_ENDPOINT = new HttpEndpoint() {
        @Override
        public HttpRouterSegment addEndpoint(Supplier<? extends HttpEndpointHandler> endpointHandler) {
            return null;
        }

        @Override
        public Optional<? extends HttpEndpointHandler> getEndpoint() {
            return Optional.empty();
        }
    };

}
