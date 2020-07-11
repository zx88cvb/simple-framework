package fun.demo.pattern.factory.abstractf;

import fun.demo.pattern.factory.abstractf.impl.HpComputerFactory;
import fun.demo.pattern.factory.entity.Keyboard;
import fun.demo.pattern.factory.entity.Mouse;

/**
 * @Author angel
 * @Date 2020/6/29
 */
public class Demo {
    public static void main(String[] args) {
        IComputerFactory iComputerFactory = new HpComputerFactory();
        Keyboard board = iComputerFactory.createBoard();
        Mouse mouse = iComputerFactory.createMouse();

        board.sayHello();
        mouse.sayHi();
    }
}
