package com.zhoulei;

import com.spring.ZhouLeiApplicationContext;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        ZhouLeiApplicationContext zhouLeiApplicationContext = new ZhouLeiApplicationContext(AppConfig.class);

        Object userService = zhouLeiApplicationContext.getBean("userService");

    }
}
