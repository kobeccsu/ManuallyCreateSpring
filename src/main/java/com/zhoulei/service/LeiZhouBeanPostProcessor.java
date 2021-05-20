package com.zhoulei.service;

import com.spring.BeanPostProcessor;
import com.spring.Component;
import com.spring.ZhouLeiApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class LeiZhouBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("run before");

        if (beanName.equals("userService")){
            ((UserServiceImpl)bean).setBeanName("lei lei bean");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("run after");

        if (beanName.equals("userService")){
            Object proxyInstance = Proxy.newProxyInstance(LeiZhouBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(),
                new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("I can, trust");
                    return method.invoke(bean, args);
                }
            });

            return proxyInstance;
        }

        return bean;
    }
}
