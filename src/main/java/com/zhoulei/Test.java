package com.zhoulei;

import com.spring.ZhouLeiApplicationContext;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        ZhouLeiApplicationContext zhouLeiApplicationContext = new ZhouLeiApplicationContext(AppConfig.class);

        System.out.println(zhouLeiApplicationContext.getBean("userService"));
        System.out.println(zhouLeiApplicationContext.getBean("userService"));
        System.out.println(zhouLeiApplicationContext.getBean("userService"));
    }
}
