package filter;

import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.routing.HttpFilterResult;

public class DefaultHttpFilterResult implements HttpFilterResult {
    private final Boolean proceed;
    private final HttpResponse<?> errorResponse;

    public DefaultHttpFilterResult(Boolean proceed, HttpResponse<?> errorResponse) {
        this.proceed = proceed;
        this.errorResponse = errorResponse;
    }

    @Override
    public Boolean proceed() {
        return proceed;
    }

    @Override
    public HttpResponse<?> errorResponse() {
        return errorResponse;
    }
}