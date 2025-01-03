package com.myplatform.myplatform;

import com.myplatform.myplatform.embedded.routing.DefaultHttpRouter;
import com.myplatform.myplatform.embedded.routing.HttpRouter;
import com.myplatform.myplatform.embedded.routing.HttpRouterSegment;
import com.myplatform.myplatform.embedded.routing.HttpRouterSegmentImpl;
import com.myplatform.myplatform.endpoints.*;
import com.myplatform.myplatform.filter.AuthenticationHttpFilter;
import com.myplatform.myplatform.filter.CorsFilterHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class HttpRouterConfig {

    @Bean
    public BlockEndpointHandler blockEndpointHandler() {return new BlockEndpointHandler();}

    @Bean
    public RegistrationEndpointHandler registrationEndpointHandler() {
        return new RegistrationEndpointHandler();
    }

    @Bean
    public LoginEndpointHandler loginEndpointHandler() {
        return new LoginEndpointHandler();
    }

    @Bean
    public WorkspaceEndpointHandler workspaceEndpointHandler() {
        return new WorkspaceEndpointHandler();
    }

    @Bean
    public PageEndpointHandler pageEndpointHandler() {
        return new PageEndpointHandler();
    }


    @Bean
    public PermissionEndpointHandler permissionEndpointHandler() {
        return new PermissionEndpointHandler();
    }

    @Bean
    public AuthenticationHttpFilter authenticationHttpFilter() {
        return new AuthenticationHttpFilter();
    }

    @Bean
    public LogoutEndpointHandler logoutEndpointHandler() {
        return new LogoutEndpointHandler();
    }

    @Bean
    public WorkspacesEndpointHandler workspacesEndpointHandler() {return new WorkspacesEndpointHandler();}

    @Bean
    public HttpRouter router() {
        HttpRouterSegment root = new HttpRouterSegmentImpl("/");
        root
                .addMapping("/api")
                    .setupFilters()
                        .addFilter(CorsFilterHandler::new)
                    .endSetup()
                    .addMapping("/auth")
                        .addMapping("/register")
                        .setupEndpoint()
                            .addEndpoint(this::registrationEndpointHandler)
                        .back()
                        .addMapping("/login")
                        .setupEndpoint()
                            .addEndpoint(this::loginEndpointHandler)
                        .back()
                        .addMapping("/logout")
                        .setupEndpoint()
                            .addEndpoint(this::logoutEndpointHandler)
                        .back()
                    .back()
                    .addMapping("/data")
                        .setupFilters()
                            .addFilter(this::authenticationHttpFilter)
                        .endSetup()
                            .addMapping("/workspacesList")
                                .setupEndpoint()
                                .addEndpoint(this::workspacesEndpointHandler)
                            .back()
                            .addMapping("/workspaces")
                                .setupEndpoint()
                                    .addEndpoint(this::workspaceEndpointHandler)
                            .back()
                            .addMapping("/pages")
                                .setupEndpoint()
                                    .addEndpoint(this::pageEndpointHandler)
                            .back()
                            .addMapping("/permissions")
                                .setupEndpoint()
                                    .addEndpoint(this::permissionEndpointHandler)
                            .back()
                            .addMapping("/blocks")
                                .setupEndpoint()
                            .       addEndpoint(this::blockEndpointHandler)
                            .back();

        return new DefaultHttpRouter(root);
    }
}
