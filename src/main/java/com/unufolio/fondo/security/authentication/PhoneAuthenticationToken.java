package com.unufolio.fondo.security.authentication;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/05/31
 */
public class PhoneAuthenticationToken extends AbstractCodeAuthenticationToken {
    public PhoneAuthenticationToken(Object principal, Object credentials, String code) {
        super(principal, credentials, code);
    }

    public PhoneAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
