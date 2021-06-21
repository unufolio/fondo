package com.unufolio.fondo.security.userdetails;

import com.google.common.collect.Sets;
import com.unufolio.fondo.model.dataobject.User;
import com.unufolio.fondo.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/03/28
 */
@Service
public class ExtendedUserDetailsServiceImpl implements IExtendedUserDetailsService {

    private final UserService userService;

    public ExtendedUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByPhone(String phone) {
        Optional<User> optionalUser = userService.findByPhone(phone);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ExtendedUserDetails
                    .withUsername(user.getUsername())
                    .phone(user.getPhone())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .authorities(Sets.newHashSet())
                    .build();
        } else {
            return ExtendedUserDetails.builder().build();
        }
    }

    @Override
    public UserDetails loadUserByEmail(String email) {
        Optional<User> optionalUser = userService.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ExtendedUserDetails
                    .withUsername(user.getUsername())
                    .phone(user.getPhone())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .authorities(Sets.newHashSet())
                    .build();
        } else {
            return ExtendedUserDetails.builder().build();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> optionalUser = userService.findByUsername(s);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ExtendedUserDetails
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(Sets.newHashSet())
                    .build();
        } else {
            return ExtendedUserDetails.builder().build();
        }
    }
}
