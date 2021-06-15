package com.unufolio.fondo.security.verification;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/06/09
 */
@FunctionalInterface
public interface CodeVerifier {
    void verify(String key, String code);
}
