package com.dubbo.framework;

import javax.print.DocFlavor;
import java.io.Serializable;

public class Invocation implements Serializable {
    private String interfaceName;
    private String methodName;
    private Class[] paramType;
    private Object[] params;


    public String getInterfaceName() {
        return "";
    }

    public String getMethodName() {
        return "";
    }

    public Class[] getParamType() {
        return null;
    }

    public Object getParams() {
        return null;
    }
}
