package pers.wh.design.proxy.jdkproxy;

import pers.wh.design.proxy.Car;
import pers.wh.design.proxy.Moveable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CarLogProxy implements InvocationHandler {
    /**
     * 被代理的对象
     */
    private Object target;

    public CarLogProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("开始记录日志");

        //执行被代理对象的方法
        Object r = method.invoke(target, args);

        System.out.println("结束记录日志");

        return r;
    }

    public static void main(String[] args) {
        Car car = new Car();
        CarLogProxy logProxy = new CarLogProxy(car);

       Moveable m = (Moveable)Proxy.newProxyInstance(Car.class.getClassLoader(), Car.class.getInterfaces(), logProxy);
       m.move();

    }
}
