package com.intermap.content.audit.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Project war-content-audit
 * @Package com.intermap.content.audit.utils
 * @Author：zouxiaodong
 * @Description:
 * @Date:Created in 9:58 2019/3/19.
 */
public class SpringBeanUtil implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return (T) applicationContext.getBean(clazz);
    }

    public static Object getBean(String name) throws BeansException {

        return applicationContext.getBean(name);
    }
}
