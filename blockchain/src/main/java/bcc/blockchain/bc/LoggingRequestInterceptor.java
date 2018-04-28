package bcc.blockchain.bc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    final static Logger log = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        log.debug("Request URI     : {}", request.getURI());
        log.debug("Request Method  : {}", request.getMethod());
        log.debug("Request Headers : {}", request.getHeaders() );
        log.debug("Request Body    : {}", new String(body, "UTF-8"));
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
    	StringBuilder bodyStringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
        String line = reader.readLine();
        while (line != null) {
            bodyStringBuilder.append(line);
            line = reader.readLine();
        }
        log.debug("Response Status code : {}", response.getStatusCode());
        log.debug("Response Status text : {}", response.getStatusText());
        log.debug("Response Headers     : {}", response.getHeaders());
        log.debug("Response Body        : {}", bodyStringBuilder.toString());
    }

}
