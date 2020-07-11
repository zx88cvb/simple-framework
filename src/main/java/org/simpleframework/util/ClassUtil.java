package org.simpleframework.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * 类相关的工具类
 * @Author angel
 * @Date 2020/7/1
 */
@Slf4j
public class ClassUtil {

    public static final String FILE_PROTOCOL = "file";

    /**
     * 获取包下类的集合
     * @param packageName 包名
     * @return 类集合
     */
    public static Set<Class<?>> extractPackageClass(String packageName) {
        // 获取到类的加载器
        ClassLoader classLoader = ClassUtil.getClassLoader();
        // 通过类加载器获取到加载资源
        URL url = classLoader.getResource(packageName.replace(".", "/"));

        if (url == null) {
            log.error("unable to retrieve anything from package: {}", packageName);
            throw new RuntimeException("unable to retrieve anything from package: {}" + packageName);
        }
        // 根据不通资源类型，采用不同方式获取资源集合
        Set<Class<?>> classSet = null;

        // 过滤出文件类型的资源
        if (FILE_PROTOCOL.equalsIgnoreCase(url.getProtocol())) {
            classSet = new HashSet<>();

            // 实际路径
            File packageDir = new File(url.getPath());
            extractClassFile(classSet, packageDir, packageName);
        }

        // TODO 可以加入针对其他资源类型处理
        return classSet;
    }

    /**
     * 递归获取目标package里面的所有class文件(包括子package里的class文件)
     * @param classSet 装载目标类的集合
     * @param fileSource 文件或者目录
     * @param packageName 包名
     */
    private static void extractClassFile(Set<Class<?>> classSet, File fileSource, String packageName) {
        if (!fileSource.isDirectory()) {
            return;
        }

        // 如果是文件夹，调用listFile 获取文件夹下的文件或文件及
        File[] files = fileSource.listFiles((File pathname) -> {
            // 如果是文件夹 则为True
            if (pathname.isDirectory()) {
                return Boolean.TRUE;
            } else {
                // 获取文件的绝对值路径
                String absolutePath = pathname.getAbsolutePath();
                if (absolutePath.endsWith(".class")) {
                    // 若是class文件 则直接加载
                    addToClassSet(absolutePath, classSet, packageName);
                }
            }
            return Boolean.FALSE;
        });

        if (files != null) {
            for (File f: files) {
                // 递归调用
                extractClassFile(classSet, f, packageName);
            }
        }
    }

    /**
     * 根据class文件的绝对值路径，获取并生成class对象，并放入classSet中
     * @param absolutePath 文件的绝对值路径
     * @param classSet 类集合
     * @param packageName 包名
     */
    private static void addToClassSet(String absolutePath, Set<Class<?>> classSet, String packageName) {
        //1.从class文件的绝对值路径里提取出包含了package的类名
        //如/Users/baidu/imooc/springframework/sampleframework/target/classes/com/imooc/entity/dto/MainPageInfoDTO.class
        //需要弄成com.imooc.entity.dto.MainPageInfoDTO
        absolutePath = absolutePath.replace(File.separator, ".");
        String className = absolutePath.substring(absolutePath.indexOf(packageName));
        className = className.substring(0, className.lastIndexOf("."));

        //2.通过反射机制获取对应的Class对象并加入到classSet里
        Class<?> targetClass = loadClass(className);

        classSet.add(targetClass);

    }

    /**
     * 获取class对象
     * @param className class全名 = 包名 + 类名
     * @return
     */
    private static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("load class error: {}", e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取classLoader
     * @return 当前的classLoader
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 实例化class
     * @param clazz class
     * @param accessible 是否支持private
     * @param <T> class类型
     * @return 类的实例化
     */
    public static <T> T newInstance(Class<?> clazz, boolean accessible) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(accessible);
            return (T)constructor.newInstance();
        } catch (InstantiationException e) {
            log.error("InstantiationException new instance is error: {}", e);
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            log.error("IllegalAccessException new instance is error: {}", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            log.error("InvocationTargetException new instance is error: {}", e);
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            log.error("NoSuchMethodException new instance is error: {}", e);
            throw new RuntimeException(e);
        }
    }
}
