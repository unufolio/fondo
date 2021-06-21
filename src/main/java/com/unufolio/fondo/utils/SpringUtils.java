package com.unufolio.fondo.utils;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

/**
 * @author unufolio unufolio@gmail.com
 * @since 2020/06/17
 */
public class SpringUtils {

    private static ApplicationContext applicationContext;
    private static ApplicationContext parentApplicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        Assert.notNull(context, "SpringUtils injection ApplicationContext is null");
        applicationContext = context;
        parentApplicationContext = context.getParent();
    }

    public static Object getBean(String name) {
        Assert.hasText(name, "SpringUtils bean name is null or empty");

        try {
            return applicationContext.getBean(name);
        } catch (Exception var2) {
            return parentApplicationContext.getBean(name);
        }
    }

    public static <T> T getBean(String name, Class<T> type) {
        Assert.hasText(name, "SpringUtils bean name is null or empty");
        Assert.notNull(type, "SpringUtils bean type is null");

        try {
            return applicationContext.getBean(name, type);
        } catch (Exception var3) {
            return parentApplicationContext.getBean(name, type);
        }
    }

    public static <T> T getBean(Class<T> type) {
        Assert.notNull(type, "SpringUtils bean type is null");

        try {
            return applicationContext.getBean(type);
        } catch (Exception var2) {
            return parentApplicationContext.getBean(type);
        }
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        Assert.notNull(type, "SpringUtils bean type is null");

        try {
            return applicationContext.getBeansOfType(type);
        } catch (Exception var2) {
            return parentApplicationContext.getBeansOfType(type);
        }
    }

    public static ApplicationContext publishEvent(Object event) {
        applicationContext.publishEvent(event);
        return applicationContext;
    }
}
