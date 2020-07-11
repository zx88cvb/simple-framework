package fun.demo.pattern.factory.method;

import fun.demo.pattern.factory.entity.Mouse;

/**
 * 工厂模式
 * @Author angel
 * @Date 2020/6/29
 */
public interface MouseFactory {
    /**
     * create mouse
     * @return mouse
     */
    Mouse createMouse();
}
