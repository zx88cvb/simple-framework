package org.simpleframework.inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @Author angel
 * @Date 2020/7/11
 */
@Slf4j
public class DependencyInjector {

    private BeanContainer beanContainer;

    public DependencyInjector () {
        beanContainer = BeanContainer.getInstance();
    }

    /**
     * 执行IOC
     */
    public void doIoc() {
        // 遍历Bean容器中所有的Class对象
        Set<Class<?>> classSet = beanContainer.getClasses();
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("empty class set in BeanContainer");
            return;
        }
        for (Class<?> clazz: classSet) {
            // 遍历Class对象的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)) {
                continue;
            }
            for (Field field: fields) {
                // 找出被Autowired标记的成员变量
                if (field.isAnnotationPresent(Autowired.class)) {
                    // 获取autowired实例
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    // 获取value
                    String autowiredValue = autowired.value();
                    // 获取这些成员变量的类型
                    Class<?> fieldClass = field.getType();

                    // 获取成员变量类型在容器里对应的实例
                    Object fieldValue = getFieldInstance(fieldClass, autowiredValue);
                    if (fieldValue == null) {
                        throw new RuntimeException("unable to inject relevant type，target fieldClass is:" +
                                fieldClass.getName() + " autowiredValue is : " + autowiredValue);
                    } else {
                        // 通过反射将对应的成员变量实例注入到成员变量所在的类实例中
                        // 类实例
                        Object targetBean = beanContainer.getBean(clazz);
                        ClassUtil.setField(field, targetBean, fieldValue, true);
                    }
                }
            }
        }
    }

    /**
     * 根据Class在beanContainer里获取其实例或实现类
     * @param fieldClass 成员变量类型
     * @param autowiredValue autowiredValue属性值
     * @return 成员变量Value
     */
    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
        Object fieldValue = beanContainer.getBean(fieldClass);

        // 如果不为空，则表示成员变量并非接口而是类
        if (fieldValue != null) {
            return fieldValue;
        } else {
            Class<?> implementedClass = getImplemengtClass(fieldClass, autowiredValue);
            if (implementedClass != null) {
                return beanContainer.getBean(implementedClass);
            }
        }
        return null;
    }

    /**
     * 获取接口对应的实现类
     * @param fieldClass 接口类型或父类
     * @param autowiredValue autowired属性值
     * @return 对应子类或实现类成员变量的类型
     */
    private Class<?> getImplemengtClass(Class<?> fieldClass, String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if (ValidationUtil.isEmpty(classSet)) {
            return null;
        }

        if (ValidationUtil.isEmpty(autowiredValue)) {
            // 代表只有一个子类直接返回第一个
            if (classSet.size() == 1) {
                return classSet.iterator().next();
            } else {
                // 大于1个实现类且用户未指定其中一个实现类，抛出异常
                throw new RuntimeException("multiple implemented classes for " + fieldClass.getName() +
                        " please set @Autowired's value to pick one");
            }
        } else {
            // 遍历Set集合 判断autowired的值是否和类名相等
            for (Class clazz: classSet) {
                if (autowiredValue.equals(clazz.getSimpleName())) {
                    return clazz;
                }
            }
        }

        return null;

    }
}
