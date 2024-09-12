package com.myplatform.myplatform.routing;

public interface HttpRouterNavigator {

    HttpEndpoint getEndpoint();

    HttpFilterSegment getFilters();

    HttpRouterSegment navigate(String pattern);

}
