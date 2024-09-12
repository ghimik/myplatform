package com.myplatform.myplatform.routing;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public interface HttpFilterSegment {

    HttpFilterSegment addFilter(Supplier<? extends HttpFilterHandler> filterProd);

    Collection<? extends HttpFilterHandler> getFilters();

    HttpRouterSegment endSetup();

    HttpFilterSegment EMPTY = new HttpFilterSegment() {
        @Override
        public HttpFilterSegment addFilter(Supplier<? extends HttpFilterHandler> filterProd) {
            return null;
        }

        @Override
        public Collection<? extends HttpFilterHandler> getFilters() {
            return List.of();
        }

        @Override
        public HttpRouterSegment endSetup() {
            return null;
        }
    };

}
