package com.zhoulei.service;

import com.spring.*;

@Component("userService")
//@Scope("protoptye")
public class UserService implements InitializingBean {

    @AutoWired
    private OrderService orderService;

    private String beanName;

    public void test(){
        System.out.println(orderService);
        System.out.println("BeanName :" + beanName);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Hello");
    }
}
