package com.unufolio.fondo.security.userdetails;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/03/28
 */
public interface IExtendedUserDetails extends UserDetails {

    String getEmail();

    String getPhone();
}