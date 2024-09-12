package com.myplatform.myplatform.routing;

public class HttpRouterSegmentImpl implements HttpRouterSegment {

    private final String pattern;

    private final HttpRouterNavigatorImpl navigator = new HttpRouterNavigatorImpl();

    public HttpRouterSegmentImpl(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public HttpRouterSegment addMapping(String pattern) {
        HttpRouterSegmentImpl newSegment = new HttpRouterSegmentImpl(pattern);
        navigator.addMapping(pattern, newSegment);
        newSegment.navigator.addRoot(this);
        return newSegment;
    }

    @Override
    public HttpRouterSegment back() {
        return navigator.getRoot();
    }


    @Override
    public HttpEndpoint setupEndpoint() {
        HttpEndpoint endpoint =  new HttpEndpointImpl(this);
        navigator.setHttpEndpoint(endpoint);
        return endpoint;
    }

    @Override
    public HttpFilterSegment setupFilters() {
        HttpFilterSegment filterSegment = new HttpFilterSegmentImpl(this);
        navigator.setFilterSegment(filterSegment);
        return filterSegment;
    }

    @Override
    public HttpRouterNavigator getNavigator() {
        return navigator;
    }

    @Override
    public String toString() {
        return "HttpRouterSegmentImpl[pattern=" + pattern + "]";
    }


}


