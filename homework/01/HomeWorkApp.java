package com;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class HomeWorkApp extends ClassLoader {
    public static void main(String[] args){
        try {
            Object hello = new HomeWorkApp().findClass("Hello.xlass").newInstance();
            Class clazz = hello.getClass();
            for(Method method :clazz.getMethods()) {
                if("hello".equals(method.getName())) {
                    method.invoke(hello);//
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = FileUtils.readFileToByteArray(new File(name));
            byte[] classBytes = new byte[bytes.length];
            for(int i =0; i< bytes.length;i++){
                classBytes[i] = (byte)(255-bytes[i]);
            }
            return defineClass("Hello",classBytes,0,classBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
