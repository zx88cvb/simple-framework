package fun.demo.pattern.factory.method.impl;

import fun.demo.pattern.factory.entity.DellMouse;
import fun.demo.pattern.factory.entity.Mouse;
import fun.demo.pattern.factory.method.MouseFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author angel
 * @Date 2020/6/29
 */
@Slf4j
public class DellMouseFactory implements MouseFactory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }
}
