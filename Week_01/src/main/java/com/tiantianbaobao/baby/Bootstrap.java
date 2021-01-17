package com.tiantianbaobao.baby;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/***
 * @description bootstrap
 * @author <h>cuitao@aixuexi.com</h>
 * @date 2021-01-17 16:12
 * @since V1.0.0
 */
public class Bootstrap {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        final String classFile = "com.tiantianbaobao.baby.Hello";
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try{
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        return super.loadClass(name);
                    }
                    byte[] resource = new byte[inputStream.available()];
                    inputStream.read(resource);
                    return defineClass(name, resource, 0, resource.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException();
                }
            }
        };
        Class<?> helloClass = classLoader.loadClass(classFile);
        Object instance = helloClass.newInstance();
        Method[] methods = instance.getClass().getMethods();
        Arrays.stream(methods).forEach(method -> {
            System.err.println("current method is : " + method.getName());
            if ("hello".equals(method.getName())) {
                try {
                    method.invoke(instance, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
        System.err.println(instance.getClass());
        boolean result = instance instanceof Hello;
        System.err.println("custome class isEquals from jvm classLoader? " + result);
    }
}
