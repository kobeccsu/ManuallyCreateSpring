package com.spring;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Currency;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ZhouLeiApplicationContext {
    private Class appConfig;
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public ZhouLeiApplicationContext(Class appConfig) throws ClassNotFoundException {
        this.appConfig = appConfig;

        scan(appConfig);

        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();

            if (beanDefinition.getScope().equals("singleton")){
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }

    }

    private void scan(Class appConfig) throws ClassNotFoundException {
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

                    beanDefinition.setAclass(aClass);

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

    public Object getBean(String beanName) {
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

            if (beanDefinition.getScope().equals("singleton")) {
                Object o = singletonObjects.get(beanName);
                return o;
            } else {
                Object bean = createBean(beanName,beanDefinition);
                return bean;
            }
        } else {
            throw new IllegalArgumentException("not found bean");
        }
    }

    public Object createBean(String beanName, BeanDefinition beanDefinition){
        Class aclass = beanDefinition.getAclass();
        try {
            Object instance = aclass.getDeclaredConstructor().newInstance();

            if (instance instanceof BeanNameAware){
                ((BeanNameAware)instance).SetBeanName(beanName);
            }
            if (instance instanceof InitializingBean){
                ((InitializingBean)instance).afterPropertiesSet();
            }


            for (Field declaredField : aclass.getDeclaredFields()) {
                if (declaredField.isAnnotationPresent(AutoWired.class)){
                    Object bean = getBean(declaredField.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(instance, bean);
                }
            }
            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
