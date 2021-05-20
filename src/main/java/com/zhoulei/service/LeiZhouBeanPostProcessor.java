package com.zhoulei.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;

@Component
public class LeiZhouBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("run before");

        if (beanName.equals("userService")){
            ((UserService)bean).setBeanName("lei lei bean");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("run after");

        return bean;
    }
}
