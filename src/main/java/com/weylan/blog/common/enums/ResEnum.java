package com.weylan.blog.common.enums;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ResEnum implements BaseEnum<Integer> {
    /**
     *
     */
    SUCCESS(200, ""),

    ERROR(500, "system error"),

    TOKEN_VALIDATE_FAILED(4001, "token vaidate failed");

    private Integer code;

    private String msg;

}
