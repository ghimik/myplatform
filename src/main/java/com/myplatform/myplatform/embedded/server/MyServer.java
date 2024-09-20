package com.myplatform.myplatform.embedded.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);


    public void run(Integer port) {
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Status: server started.");

            while (true) {
                Socket clientSocket = server.accept();
                System.out.println("Status: accepted client.");
                executorService.submit(() -> {
                    System.out.println("Status: started serving client.");
                    new HttpConnection(clientSocket).run();
                    System.out.println("Status: served client.");
                });
                System.out.println("Status: main thread is free.");

            }
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

}
