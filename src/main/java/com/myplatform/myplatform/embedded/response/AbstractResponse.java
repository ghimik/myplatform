package com.myplatform.myplatform.embedded.response;

public abstract class AbstractResponse {

    public abstract String getFormatted();

    public abstract AbstractResponseBuilder getBuilder();
}
