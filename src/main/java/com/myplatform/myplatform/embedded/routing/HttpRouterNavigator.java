package com.myplatform.myplatform.embedded.routing;

public interface HttpRouterNavigator {

    HttpEndpoint getEndpoint();

    HttpFilterSegment getFilters();

    HttpRouterSegment navigate(String pattern);

}
