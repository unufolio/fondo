package com.unufolio.fondo.common.enums;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/03/28
 */
public enum ResultCodeEnum implements IResultCode {

    // Success
    SUCCESS("20000", "Success"),
    ERROR("40000", "failure"),
    UNAUTHORIZED("40100", "UNAUTHORIZED"),
    LOGIN_FAILED("40110", "UNAUTHORIZED"),
    ;

    private final String value;
    private final String message;

    ResultCodeEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public String message() {
        return this.message;
    }
}
