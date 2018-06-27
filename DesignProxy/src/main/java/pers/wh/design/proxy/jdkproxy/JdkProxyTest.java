package pers.wh.design.proxy.jdkproxy;

import pers.wh.design.proxy.Car;
import pers.wh.design.proxy.Moveable;

import java.lang.reflect.Proxy;

public class JdkProxyTest {

    public static void main(String[] args) {
        Car car = new Car();

        CarLogProxy logProxy = new CarLogProxy(car);
        Moveable m1 = (Moveable)Proxy.newProxyInstance(Car.class.getClassLoader(), Car.class.getInterfaces(), logProxy);

        CarTimeProxy timeProxy = new CarTimeProxy(m1);
        Moveable m2 = (Moveable)Proxy.newProxyInstance(Car.class.getClassLoader(), Car.class.getInterfaces(), timeProxy);

        m2.move();

    }

}
