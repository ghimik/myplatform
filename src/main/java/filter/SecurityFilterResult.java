package filter;

import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.routing.HttpFilterResult;

public class SecurityFilterResult implements HttpFilterResult {

    private final Boolean status;

    public SecurityFilterResult(Boolean status) {
        this.status = status;
    }

    @Override
    public Boolean proceed() {
        return status;
    }

    @Override
    public HttpResponse<?> errorResponse() {
        return HttpResponse.badRequest("Unauthorized");
    }
}
