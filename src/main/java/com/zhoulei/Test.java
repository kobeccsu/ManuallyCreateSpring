package com.zhoulei;

import com.spring.ZhouLeiApplicationContext;
import com.zhoulei.service.UserService;

import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ZhouLeiApplicationContext zhouLeiApplicationContext = new ZhouLeiApplicationContext(AppConfig.class);

        UserService userService = (UserService) zhouLeiApplicationContext.getBean("userService");
        userService.test();

    }
}
