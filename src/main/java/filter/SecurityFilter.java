package filter;

import com.myplatform.myplatform.PseudoBeanConfig;
import com.myplatform.myplatform.embedded.request.http.HttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.routing.HttpFilterHandler;
import com.myplatform.myplatform.embedded.routing.HttpFilterResult;
import com.myplatform.myplatform.embedded.security.SecurityContext;

import javax.crypto.SecretKey;
import java.util.Map;

public class SecurityFilter implements HttpFilterHandler {


    @Override
    public HttpFilterResult filter(HttpRequest request, HttpResponseBuilder response) {
        return null;
    }
}
