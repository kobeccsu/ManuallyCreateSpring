package com.zhoulei;

import com.spring.ZhouLeiApplicationContext;
import com.zhoulei.service.UserService;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        ZhouLeiApplicationContext zhouLeiApplicationContext = new ZhouLeiApplicationContext(AppConfig.class);

        UserService userService = (UserService) zhouLeiApplicationContext.getBean("userService");
        userService.test();

    }
}
