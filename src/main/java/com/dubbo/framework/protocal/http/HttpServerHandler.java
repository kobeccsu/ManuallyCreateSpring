package com.dubbo.framework.protocal.http;

import com.alibaba.fastjson.JSONObject;
import com.dubbo.framework.Invocation;
import com.dubbo.framework.registry.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp){
        try {
            Invocation invocation = JSONObject.parseObject(String.valueOf(req.getInputStream()), Invocation.class);

            String interfaceName = invocation.getInterfaceName();
            String methodName = invocation.getMethodName();
            Class[] paramType = invocation.getParamType();

            Class clazz = LocalRegister.get(interfaceName);
            Method method = clazz.getMethod(methodName, paramType);
            String result = (String) method.invoke(clazz.newInstance(), invocation.getParams());

            IOUtils.write(result, resp.getOutputStream(), Charset.forName("utf-8"));


        } catch (IOException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
