package com.myplatform.myplatform;

import com.myplatform.myplatform.routing.*;

public final class HttpRouterConfig {
    static {
        HttpRouterSegment root = new HttpRouterSegmentImpl("/");
        root
                .addMapping("/api")
                    .setupFilters()
                        .addFilter(TestFilterHandler::new)
                    .endSetup()
                        .addMapping("/test")
                        .setupEndpoint()
                        .addEndpoint(TestEndpointHandler::new)
                    .back()
                        .addMapping("/test2")
                        .setupEndpoint()
                        .addEndpoint(TestEndpointHandler::new)
                ;
        router = new DefaultHttpRouter(root);
    }

    public static HttpRouter router;



}
