package com.myplatform.myplatform;


import com.myplatform.myplatform.embedded.routing.DefaultHttpRouter;
import com.myplatform.myplatform.embedded.routing.HttpRouter;
import com.myplatform.myplatform.embedded.routing.HttpRouterSegment;
import com.myplatform.myplatform.embedded.routing.HttpRouterSegmentImpl;
import com.myplatform.myplatform.endpoints.LoginEndpointHandler;
import com.myplatform.myplatform.endpoints.RegistrationEndpointHandler;
import com.myplatform.myplatform.endpoints.TestBodyEndpoint;
import com.myplatform.myplatform.endpoints.TestEndpointHandler;
import com.myplatform.myplatform.filter.AuthenticationHttpFilter;
import com.myplatform.myplatform.filter.TestFilterHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
public class HttpRouterConfig {

    @Bean
    public HttpRouter router() {
        HttpRouterSegment root = new HttpRouterSegmentImpl("/");
        root
                .addMapping("/api")
                    .setupFilters()
                        .addFilter(TestFilterHandler::new)
                    .endSetup()
                    .addMapping("/auth")
                        .addMapping("/register")
                        .setupEndpoint()
                            .addEndpoint(RegistrationEndpointHandler::new)
                        .back()
                        .addMapping("/login")
                        .setupEndpoint()
                            .addEndpoint(LoginEndpointHandler::new)
                        .back()
                .addMapping("/sec")
                    .setupFilters()
                        .addFilter(AuthenticationHttpFilter::new)
                    .endSetup()
                    .setupEndpoint()
                        .addEndpoint(TestEndpointHandler::new);

        return new DefaultHttpRouter(root);
    }

}
