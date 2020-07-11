package fun.demo.pattern.factory.abstractf.impl;

import fun.demo.pattern.factory.abstractf.IComputerFactory;
import fun.demo.pattern.factory.entity.HpKeyboard;
import fun.demo.pattern.factory.entity.HpMouse;
import fun.demo.pattern.factory.entity.Keyboard;
import fun.demo.pattern.factory.entity.Mouse;

/**
 * @Author angel
 * @Date 2020/6/29
 */
public class HpComputerFactory implements IComputerFactory {
    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }

    @Override
    public Keyboard createBoard() {
        return new HpKeyboard();
    }
}
