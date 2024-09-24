package filter;

import com.myplatform.myplatform.PseudoBeanConfig;
import com.myplatform.myplatform.embedded.request.http.HttpRequest;
import com.myplatform.myplatform.embedded.response.http.HttpResponse;
import com.myplatform.myplatform.embedded.response.http.HttpResponseBuilder;
import com.myplatform.myplatform.embedded.routing.HttpFilterHandler;
import com.myplatform.myplatform.embedded.routing.HttpFilterResult;
import com.myplatform.myplatform.embedded.security.SecurityContext;
import com.myplatform.myplatform.embedded.security.TokenDecryptor;

import javax.crypto.SecretKey;
import java.util.Map;

public class SecurityFilter implements HttpFilterHandler {

    private final SecurityContext securityContextHolder
            = PseudoBeanConfig.securityContextHolder;

    public final SecretKey secretKey =
            PseudoBeanConfig.secretKey;


    @Override
    public HttpFilterResult filter(HttpRequest request, HttpResponseBuilder response) {
        try {
            Map<String, String> headers = request.getHead().getHeaders().getContent();
            String authHeader = headers.get("Authorization");
            if (securityContextHolder.verify(TokenDecryptor.decrypt(authHeader, secretKey)))
                return new SecurityFilterResult(true);
        }
        catch (Exception e) {
            return new SecurityFilterResult(false);
        }
        return new SecurityFilterResult(false);
    }
}
