package com.myplatform.myplatform.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestReader {

    private BufferedReader input;

    public String read(Socket socket) throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        StringBuilder stringRequest = new StringBuilder();

        String line;
        while ((line = input.readLine()) != null && !line.isEmpty()) {
            stringRequest.append(line).append("\r\n");
            System.out.println("Read line: " + line);
        }
        stringRequest.append("\r\n");

        int contentLength = getContentLength(stringRequest.toString());
        if (contentLength > 0) {
            char[] body = new char[contentLength];
            input.read(body, 0, contentLength);
            stringRequest.append(body);
            System.out.println("Read body: " + new String(body));
        }

        System.out.println("Status: request read");
        return stringRequest.toString();
    }

    private int getContentLength(String request) {
        String[] lines = request.split("\r\n");
        for (String line : lines) {
            if (line.startsWith("Content-Length:")) {
                return Integer.parseInt(line.split(":")[1].trim());
            }
        }
        return 0;
    }

    public void close() throws IOException {
        input.close();
    }
}
