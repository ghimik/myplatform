package com.myplatform.myplatform.embedded.server;

import java.net.Socket;

public abstract class InternetConnection implements Runnable{
    protected final Socket socket;

    protected final RequestReader requestReader = new RequestReader();

    public InternetConnection(Socket socket) {
        this.socket = socket;
    }

}
