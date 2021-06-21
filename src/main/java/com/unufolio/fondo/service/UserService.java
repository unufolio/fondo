package com.unufolio.fondo.service;

import com.unufolio.fondo.dal.repository.UserRepository;
import com.unufolio.fondo.model.dataobject.User;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/06/02
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        var user = new User();
        user.setUsername(username);
        var example = Example.of(user);
        return userRepository.findOne(example);
    }

    public Optional<User> findByPhone(String phone) {
        var user = new User();
        user.setPhone(phone);
        var example = Example.of(user);
        return userRepository.findOne(example);
    }

    public Optional<User> findByEmail(String email) {
        var user = new User();
        user.setEmail(email);
        var example = Example.of(user);
        return userRepository.findOne(example);
    }
}
