package com.myplatform.myplatform.server;

import com.myplatform.myplatform.server.InternetConnection;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedTransferQueue;

public final class MyServer {

    private final static LinkedTransferQueue<InternetConnection> connections
            = new LinkedTransferQueue<>();

    public static void run(Integer port) {
        try (ServerSocket server = new ServerSocket(port)){
            System.out.println("Status: server started.");

            while (true) {
                Socket clientSocket = server.accept();
                connections.add(new HttpConnection(clientSocket));
                System.out.println("Status: accepted client.");
                connections.take().start();
                System.out.println("Status: served client.");
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

}
