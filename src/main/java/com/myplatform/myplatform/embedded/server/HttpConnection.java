package com.myplatform.myplatform.embedded.server;


import com.myplatform.myplatform.HttpRouterConfig;
import com.myplatform.myplatform.embedded.request.http.HttpRequest;
import com.myplatform.myplatform.embedded.request.http.HttpRequestParser;
import com.myplatform.myplatform.embedded.request.http.RawHttpRequest;
import com.myplatform.myplatform.embedded.routing.HttpRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;

public class HttpConnection extends InternetConnection {

    private final HttpRouter router;

    public HttpConnection(Socket socket, HttpRouter router) {
        super(socket);
        this.router = router;
    }

    @Override
    public void run() {
        System.out.println("Status: New connection started");
        try {
            HttpRequest request = new RawHttpRequest();
            HttpRequestParser parser = request.getParser();
            System.out.println("Status: Started parsing");
            request = parser.parse(requestReader.read(socket));
            System.out.println("Status: raw request parsed");

            RequestWriter.write(socket, router.handle(request).getFormatted());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally {
            try {
                requestReader.close();
                socket.close();
                System.out.println("Status: closed connection");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
