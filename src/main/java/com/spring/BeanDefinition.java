package com.spring;

public class BeanDefinition {
    Class aclass;
    String scope;

    public BeanDefinition(){

    }

    public BeanDefinition(Class aclass, String scope) {
        this.aclass = aclass;
        this.scope = scope;
    }

    public Class getAclass() {
        return aclass;
    }

    public void setAclass(Class aclass) {
        this.aclass = aclass;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
