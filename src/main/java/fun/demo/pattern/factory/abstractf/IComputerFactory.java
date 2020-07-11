package fun.demo.pattern.factory.abstractf;

import fun.demo.pattern.factory.entity.Keyboard;
import fun.demo.pattern.factory.entity.Mouse;

/**
 * 抽象工厂  电脑
 * @Author angel
 * @Date 2020/6/29
 */
public interface IComputerFactory {
    /**
     * 创建鼠标
     * @return Mouse
     */
    Mouse createMouse();

    /**
     * 创建键盘
     * @return keyboard
     */
    Keyboard createBoard();
}
