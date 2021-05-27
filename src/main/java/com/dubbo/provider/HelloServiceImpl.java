package com.dubbo.provider;

public class HelloServiceImpl implements HelloService {

    public String sayHello(String username){
        return "Hello: " + username;
    }
}
