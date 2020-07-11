package fun.demo.pattern.factory.method;

import fun.demo.pattern.factory.entity.Mouse;
import fun.demo.pattern.factory.method.impl.HpMouseFactory;

/**
 * 工厂模式
 * @Author angel
 * @Date 2020/6/29
 */
public class Demo {
    public static void main(String[] args) {
        MouseFactory mouseFactory = new HpMouseFactory();
        Mouse mouse = mouseFactory.createMouse();
        mouse.sayHi();
    }
}
