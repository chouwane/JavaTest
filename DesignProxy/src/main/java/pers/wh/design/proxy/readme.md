# 代理模式

## jdk动态代理
1. 只能代理实现接口的类
2. 没有实现接口的类，是不能使用jdk动态代理的

## cglib动态代理
1. 针对类来实现代理的
2. 对指定的目标类产生一个子类，通过方法拦截技术来拦截所有父类方法的调用