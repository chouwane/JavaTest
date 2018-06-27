package pers.wh.design.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz){

        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    /**
     *
     * @param o 目标类的实例
     * @param method 目标方法的反射对象
     * @param args 目标方法的参数
     * @param methodProxy 代理类的实例
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("日志记录开始");
        Object r = methodProxy.invokeSuper(o, args);
        System.out.println("日志记录结束");

        return r;
    }


    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        Train train = (Train)cglibProxy.getProxy(Train.class);
        train.move();
    }
}
