package com.dubbo.provider;

import com.dubbo.framework.protocal.http.HttpServer;

public class Provider {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8081);

    }
}
