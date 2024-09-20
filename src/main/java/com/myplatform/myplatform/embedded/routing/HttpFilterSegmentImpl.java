package com.myplatform.myplatform.embedded.routing;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Supplier;

public class HttpFilterSegmentImpl implements HttpFilterSegment {

    private final Collection<HttpFilterHandler> filters =
            new LinkedList<>();

    private final HttpRouterSegment bound;

    public HttpFilterSegmentImpl(HttpRouterSegment bound) {
        this.bound = bound;
    }

    @Override
    public HttpFilterSegment addFilter(Supplier<? extends HttpFilterHandler> filterProd) {
        filters.add(filterProd.get());
        return this;
    }

    @Override
    public Collection<? extends HttpFilterHandler> getFilters() {
        return filters;
    }

    @Override
    public HttpRouterSegment endSetup() {
        return bound;
    }
}
