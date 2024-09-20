package com.myplatform.myplatform.embedded.routing;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HttpRouterNavigatorImpl implements HttpRouterNavigator {

        private final Map<String, HttpRouterSegment> routerMap = new HashMap<>();

        private HttpFilterSegment filterSegment;

        private HttpEndpoint httpEndpoint;

        private HttpRouterSegment root;

        void addRoot(HttpRouterSegment root) {
            this.root = root;
        }

        void addMapping(String pattern, HttpRouterSegment nextRouter) {
            System.out.println("Added mapping: " + pattern);
            routerMap.put(pattern, nextRouter);
        }

        HttpRouterSegment getRoot() {
            return this.root;
        }

        public void setHttpEndpoint(HttpEndpoint httpEndpoint) {
            this.httpEndpoint = httpEndpoint;
        }

        public void setFilterSegment(HttpFilterSegment filterSegment) {
            this.filterSegment = filterSegment;
        }

        @Override
        public HttpFilterSegment getFilters() {
            return this.filterSegment;
        }

        @Override
        public HttpEndpoint getEndpoint() {
            return Objects.isNull(this.httpEndpoint) ? HttpEndpoint.NO_ENDPOINT : this.httpEndpoint;
        }

        @Override
        public HttpRouterSegment navigate(String pattern)
            throws IllegalArgumentException {

            System.out.println(routerMap.values());
            HttpRouterSegment navigated = routerMap.get(pattern);
            if (navigated != null)
                return navigated;
            throw new IllegalArgumentException("Pattern[" + pattern + "] not found");
        }


}