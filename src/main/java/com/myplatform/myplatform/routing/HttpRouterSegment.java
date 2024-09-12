package com.myplatform.myplatform.routing;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

public interface HttpRouterSegment {

    HttpRouterSegment addMapping(String pattern);

    HttpRouterSegment back();

    HttpEndpoint setupEndpoint();

    HttpFilterSegment setupFilters();

    HttpRouterNavigator getNavigator();

}
