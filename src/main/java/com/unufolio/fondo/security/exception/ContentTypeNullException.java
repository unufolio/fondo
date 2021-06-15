package com.unufolio.fondo.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2021/05/31
 */
public class ContentTypeNullException extends AuthenticationException {
    public ContentTypeNullException(String msg, Throwable t) {
        super(msg, t);
    }

    public ContentTypeNullException(String msg) {
        super(msg);
    }
}
