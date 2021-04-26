package com.spring;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Enumeration;

public class ZhouLeiApplicationContext {
    private Class appConfig;

    public ZhouLeiApplicationContext(Class appConfig) throws ClassNotFoundException {
        this.appConfig = appConfig;

        ComponentScan componentScanAnnotation = (ComponentScan) appConfig.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScanAnnotation.value();
        System.out.println(path);

        ClassLoader classLoader = ZhouLeiApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource("com/zhoulei/service");


        File file = new File(resource.getFile()) ;
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

                }
            }

        }else{

        }

    }

    public Object getBean(String baenName){
        return null;
    }
}
