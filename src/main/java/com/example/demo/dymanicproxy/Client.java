package com.example.demo.dymanicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args){
        //      要代理的真实对象
        Subject realSubject = new RealSubject();
        //    我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new DynamicProxy(realSubject);

        /**
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象
         * 第一个参数handler.getClass().getClassLoader(),使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces(),为代理对象提供的接口是真实对象所实行的接口，表示代理的是该真实对象
         * 第三个参数handler，将这个代理对象关联到上方InvocationHandler这个对象上
         */
        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),realSubject.getClass().getInterfaces(),handler);

        System.out.println(subject.getClass().getName());
        subject.rent();
        subject.hello("world");
    }
}
