package com.weylan.blog.common.enums;

/**
 * 枚举基类
 *
 * @param <T>
 */
public interface BaseEnum<T> {

    T getCode();

    String getMsg();

}
