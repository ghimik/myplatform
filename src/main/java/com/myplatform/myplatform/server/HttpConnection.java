package com.myplatform.myplatform.server;


import com.myplatform.myplatform.request.http.HttpRequest;
import com.myplatform.myplatform.request.http.HttpRequestParser;
import com.myplatform.myplatform.request.http.RawHttpRequest;

import java.io.*;
import java.net.Socket;

public class HttpConnection extends InternetConnection {


    public HttpConnection(Socket socket) {
        super(socket);
    }

    @Override
    public void run() {
        try {
            HttpRequest request = new RawHttpRequest();
            HttpRequestParser parser = request.getParser();
            request = parser.parse(requestReader.read(socket));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
