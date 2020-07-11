package fun.demo.pattern.factory.abstractf.impl;

import fun.demo.pattern.factory.abstractf.IComputerFactory;
import fun.demo.pattern.factory.entity.DellKeyboard;
import fun.demo.pattern.factory.entity.DellMouse;
import fun.demo.pattern.factory.entity.Keyboard;
import fun.demo.pattern.factory.entity.Mouse;

/**
 * @Author angel
 * @Date 2020/6/29
 */
public class DellComputerFactory implements IComputerFactory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public Keyboard createBoard() {
        return new DellKeyboard();
    }
}
