package com.llvvlv00.finance.biz.enums;

import lombok.Getter;

@Getter
public enum SmsCodeTypeEnum {
    REG("REG", "注册"),
    LOGIN("LOGIN", "登录");
    private final String code;
    private final String message;

    SmsCodeTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
