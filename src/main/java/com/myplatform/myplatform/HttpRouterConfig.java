package com.myplatform.myplatform;


import com.myplatform.myplatform.embedded.routing.DefaultHttpRouter;
import com.myplatform.myplatform.embedded.routing.HttpRouter;
import com.myplatform.myplatform.embedded.routing.HttpRouterSegment;
import com.myplatform.myplatform.embedded.routing.HttpRouterSegmentImpl;
import com.myplatform.myplatform.endpoints.TestBodyEndpoint;
import com.myplatform.myplatform.endpoints.TestEndpointHandler;
import filter.AuthenticationHttpFilter;
import filter.TestFilterHandler;

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
                            .addEndpoint(TestBodyEndpoint::new)
                    .back()
                .addMapping("/sec")
                    .setupFilters()
                        .addFilter(AuthenticationHttpFilter::new)
                    .endSetup()
                    .setupEndpoint()
                        .addEndpoint(TestEndpointHandler::new)



                ;
        router = new DefaultHttpRouter(root);
    }

    public static HttpRouter router;



}
