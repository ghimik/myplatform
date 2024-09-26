package filter;

import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.routing.HttpFilterResult;

public class SecurityFilterResult implements HttpFilterResult {


    @Override
    public Boolean proceed() {
        return null;
    }

    @Override
    public HttpResponse<?> errorResponse() {
        return null;
    }
}
