package pers.wh.design.proxy.jdkproxy;

import pers.wh.design.proxy.Car;
import pers.wh.design.proxy.Moveable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CarTimeProxy implements InvocationHandler {

    private Object target;

    public CarTimeProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("开始记时");
        long s = System.currentTimeMillis();

        //执行被代理对象的方法
        Object r = method.invoke(target, args);

        long e = System.currentTimeMillis();
        System.out.println(String.format("结束记时，耗时：%s", (e-s)));
        return r;

    }


    public static void main(String[] args) {
        Car car = new Car();

        CarTimeProxy handler = new CarTimeProxy(car);

        //动态的生成代理对象
        Moveable m = (Moveable)Proxy.newProxyInstance(Car.class.getClassLoader(), Car.class.getInterfaces(), handler);
        m.move();

    }
}
