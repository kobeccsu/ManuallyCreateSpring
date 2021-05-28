package com.dubbo.provider;

import com.dubbo.framework.protocal.http.HttpServer;
import com.dubbo.framework.registry.LocalRegister;

public class Provider {
    public static void main(String[] args) {
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);

        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8081);

    }
}
