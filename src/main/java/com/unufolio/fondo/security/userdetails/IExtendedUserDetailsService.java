package com.unufolio.fondo.security.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/03/28
 */
public interface IExtendedUserDetailsService extends UserDetailsService {

    UserDetails loadUserByPhone(String phone);

    UserDetails loadUserByEmail(String email);
}
