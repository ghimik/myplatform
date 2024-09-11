package com.myplatform.myplatform.server;

import java.net.Socket;

public abstract class InternetConnection extends Thread{
    protected final Socket socket;

    protected final RequestReader requestReader = new RequestReader();

    public InternetConnection(Socket socket) {
        this.socket = socket;
    }

}
