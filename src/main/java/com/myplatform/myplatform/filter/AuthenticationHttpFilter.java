package com.myplatform.myplatform.filter;

import com.myplatform.myplatform.embedded.request.http.HttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.response.http.HttpResponseStatus;
import com.myplatform.myplatform.embedded.routing.HttpFilterHandler;
import com.myplatform.myplatform.embedded.routing.HttpFilterResult;
import com.myplatform.myplatform.embedded.security.Authentication;
import com.myplatform.myplatform.embedded.security.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHttpFilter implements HttpFilterHandler {

    @Autowired
    private SecurityContext securityContext;

    @Override
    public HttpFilterResult filter(HttpRequest request, HttpResponseBuilder response) {
        Authentication authentication = securityContext.getAuthentication();

        if (authentication == null) {
            HttpResponse<?> errorResponse = response
                    .setStatus(new HttpResponseStatus(401))
                    .setBody("Unauthorized")
                    .build();

            return new DefaultHttpFilterResult(false, errorResponse);
        }

        return new DefaultHttpFilterResult(true, null);
    }
}
