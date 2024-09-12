package com.myplatform.myplatform.response;

public abstract class AbstractResponse {

    public abstract String getFormatted();

    public abstract AbstractResponseBuilder getBuilder();
}
