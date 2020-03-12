package com.iffy.mianshi.pattern.proxy.dongtaidaili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author : iffy
 * time   : 2020/03/04
 * https://www.jianshu.com/p/9bcac608c714
 */
public class DongTaiDaiLi {
    public static void main(String[] args) {
        //代理Student对象
        IStudy_service mProxy = test_interface_proxy(new Student());

        mProxy.study("学语文");
        mProxy.sing("我爱我的祖国");
        System.out.println("他的名字是" + mProxy.whoAreU());
    }

    public static IStudy_service test_interface_proxy(Student student) {
        return (IStudy_service) Proxy.newProxyInstance(IStudy_service.class.getClassLoader(),
                new Class<?>[]{IStudy_service.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("method" + method);
                        //System.out.println("args[0]" + args[0]);
                        if ("study".equals(method.getName())) {
                            System.out.println("study");
                        } else if ("sing".equals(method.getName())) {
                            System.out.println("sing");
                        } else if ("whoAreU".equals(method.getName())) {
                            String result = (String) method.invoke(student, null);
                            System.out.println("这个学生真正的名字是" + result);
                            return "李四";
                        }
                        //method.invoke(proxy,args);
                        return null;
                    }
                }
        );
    }
}
