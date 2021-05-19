package com.spring;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Currency;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class ZhouLeiApplicationContext {
    private Class appConfig;
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public ZhouLeiApplicationContext(Class appConfig) throws ClassNotFoundException {
        this.appConfig = appConfig;

        ComponentScan componentScanAnnotation = (ComponentScan) appConfig.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScanAnnotation.value();
        System.out.println(path);

        ClassLoader classLoader = ZhouLeiApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource("com/zhoulei/service");


        File file = new File(resource.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                System.out.println(file1);
                String absolutePath = file1.getAbsolutePath();
                String packageWithSlash = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.lastIndexOf("."));

                packageWithSlash = packageWithSlash.replace("\\", ".");
                System.out.println(packageWithSlash);
                Class<?> aClass = classLoader.loadClass(packageWithSlash);
                if (aClass.isAnnotationPresent(Component.class)) {
                    Component declaredAnnotation = aClass.getDeclaredAnnotation(Component.class);
                    String bean = declaredAnnotation.value();

                    BeanDefinition beanDefinition = new BeanDefinition();
                    if (aClass.isAnnotationPresent(Scope.class)) {
                        Scope scopeAnnotation = aClass.getDeclaredAnnotation(Scope.class);
                        beanDefinition.setScope(scopeAnnotation.value());
                    } else {
                        beanDefinition.setScope("singleton");
                    }
                    beanDefinitionMap.put(bean, beanDefinition);
                }
            }

        } else {

        }

    }

    public Object getBean(String baenName) {
        if (beanDefinitionMap.contains(baenName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(baenName);

            if (beanDefinition.getScope().equals("singleton")) {
                Object o = singletonObjects.get(baenName);
                return o;
            } else {

            }
        } else {
            throw new IllegalArgumentException("not found bean");
        }
        return null;
    }
}
