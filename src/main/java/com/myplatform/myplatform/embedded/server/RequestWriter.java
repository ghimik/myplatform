package com.myplatform.myplatform.embedded.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestWriter {

    public static void write(Socket socket, String response) throws IOException {
        PrintWriter output = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())));

        output.println(response);
        output.flush();
        output.close();
    }
}
