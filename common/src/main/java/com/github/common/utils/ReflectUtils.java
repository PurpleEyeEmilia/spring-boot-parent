package com.github.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public class ReflectUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReflectUtils.class);

    public static Class getSuperClassGenricType(final Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    public static Class getSuperClassGenricType(final Class clazz, final int index) {
        Type type = clazz.getGenericSuperclass();

        if (!(type instanceof ParameterizedType)) {
            LOGGER.warn("{}'s superclass is not ParameterizedType", clazz.getSimpleName());
            return Object.class;
        }

        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();

        if (actualTypeArguments.length <= 0 || index < 0 || index > actualTypeArguments.length - 1) {
            LOGGER.warn("Index: {}, Size of {}'s Parameterized Type: {}", index, clazz.getSimpleName(), actualTypeArguments.length);
            return Object.class;
        }

        if (!(actualTypeArguments[index] instanceof Class)) {
            LOGGER.warn("{} not set the actual class on superclass generic parameter", clazz.getSimpleName());
            return Object.class;
        }

        return (Class) actualTypeArguments[index];
    }

}
