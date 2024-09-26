package com.myplatform.myplatform.embedded.routing;


public interface HttpRouterSegment {

    HttpRouterSegment addMapping(String pattern);

    HttpRouterSegment back();

    HttpEndpoint setupEndpoint();

    HttpFilterSegment setupFilters();

    HttpRouterNavigator getNavigator();

}
