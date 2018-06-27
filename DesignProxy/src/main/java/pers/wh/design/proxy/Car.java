package pers.wh.design.proxy;

public class Car implements Moveable {
    @Override
    public void move() {
        System.out.println("小汽开始启动");
        System.out.println("小汽车行驶中 ...");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("停车");
    }
}
