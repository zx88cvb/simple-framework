package org.simpleframework.core;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器
 * @Author angel
 * @Date 2020/7/9
 */
@Slf4j
public class BeanContainer {
    /**
     * 存放所有被配置标记目标对象的Map
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap();

    /**
     * 加载bean的注解列表对象
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class, Controller.class, Repository.class, Service.class);

    private BeanContainer() {

    }

    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.beanContainer;
    }

    /**
     * 使用枚举防止反射破坏单例
     */
    private enum ContainerHolder {
        HOLDER;
        private BeanContainer beanContainer;

        ContainerHolder() {
            beanContainer = new BeanContainer();
        }
    }

    /**
     * 容器是否被加载过bean
     */
    private boolean loaded = false;

    /**
     * 是否已经加载过bean
     * @return true/false
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * 获取beanMap的数量
     * @return 个数
     */
    public int size() {
        return beanMap.size();
    }

    /**
     * 扫描加载所有的Bean
     * @param packageName 包名
     */
    public synchronized void loadBeans(String packageName) {
        // 判断是否加载过bean
        if (isLoaded()) {
            log.warn("BeanContainer has been loaded");
            return;
        }

        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);

        // 判断Set集合是否为空
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("extract nothing from packageName: {}", packageName);
            return;
        }

        for (Class<?> clazz: classSet) {
            for (Class<? extends Annotation> annotation: BEAN_ANNOTATION) {
                // 类上是否包含自定义注解
                if (clazz.isAnnotationPresent(annotation)) {
                    beanMap.put(clazz, ClassUtil.newInstance(clazz, true));
                }
            }
        }
        loaded = true;
    }

    /**
     * 添加一个class对象及其bean实例
     * @param clazz class对象
     * @param bean bean实例
     * @return 原有bean实例 没有则返回null
     */
    public Object addBean(Class<?> clazz, Object bean) {
        return beanMap.put(clazz, bean);
    }

    /**
     * 移除一个IOC容器管理对象
     * @param clazz bean实例
     * @return 删除bean实例 没有则为null
     */
    public Object removeBean(Class<?> clazz) {
        return beanMap.remove(clazz);
    }

    /**
     * 根据Class对象获取bean实例
     * @param clazz Class对象
     * @return Bean实例
     */
    public Object getBean(Class<?> clazz) {
        return beanMap.get(clazz);
    }

    /**
     * 获取容器管理的所有Class对象集合
     * @return Class集合
     */
    public Set<Class<?>> getClasses() {
        return beanMap.keySet();
    }

    /**
     * 获取所有Bean集合
     * @return Bean集合
     */
    public Set<Object> getBeans() {
        return new HashSet<>(beanMap.values());
    }

    /**
     * 根据注解筛选Bean的Class集合
     * @param annotation 注解
     * @return Class集合
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
        // 获取beanMap获取所有class对象
        Set<Class<?>> keySet = getClasses();

        if (ValidationUtil.isEmpty(keySet)) {
            log.warn("nothing is beanMap");
            return null;
        }
        // 筛选被注解标记的class对象放入Set
        HashSet<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz: keySet) {
            // 类是否有相关注解标记
            if (clazz.isAnnotationPresent(annotation)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0? classSet: null;
    }

    /**
     * 通过接口或者父类获取实现类或子类的Class集合,不包括其本身
     * @param interfaceOrClass 接口class或者父类Class
     * @return Class集合
     */
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass) {
        // 获取beanMap获取所有class对象
        Set<Class<?>> keySet = getClasses();

        if (ValidationUtil.isEmpty(keySet)) {
            log.warn("nothing is beanMap");
            return null;
        }
        // 判断keySet元素是否为传入接口或类的子类,对象放入Set
        HashSet<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz: keySet) {
            // 类是否有相关注解标记
            if (interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0? classSet: null;
    }
}
