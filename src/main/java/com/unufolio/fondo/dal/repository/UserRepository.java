package com.unufolio.fondo.dal.repository;

import com.unufolio.fondo.model.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/05/25
 */
public interface UserRepository extends JpaRepository<User, String> {
}
