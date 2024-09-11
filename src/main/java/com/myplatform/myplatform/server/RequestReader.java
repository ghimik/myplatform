package com.myplatform.myplatform.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestReader {

    private BufferedReader input;

    public String read(Socket socket) throws IOException, InterruptedException {

        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        StringBuilder stringRequest = new StringBuilder();
        while (!input.ready()) ;
        while (input.ready()) {
            String line = input.readLine();
            stringRequest.append(line).append("\r\n");
        }
        System.out.println("Status: request get");
        System.out.println(stringRequest.toString());
        return stringRequest.toString();
    }

    public void close() throws IOException {
        input.close();
    }

}
