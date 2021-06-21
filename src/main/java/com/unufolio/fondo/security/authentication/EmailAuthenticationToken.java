package com.unufolio.fondo.security.authentication;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author oktfolio oktfolio@gmail.com
 * @since 2021/05/31
 */
public class EmailAuthenticationToken extends AbstractCodeAuthenticationToken {

    public EmailAuthenticationToken(Object principal, Object credentials, String code) {
        super(principal, credentials, code);
    }

    public EmailAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
