package com.unufolio.fondo.controller.admin.user;

import com.unufolio.fondo.common.ResultEntity;
import com.unufolio.fondo.model.dataobject.User;
import com.unufolio.fondo.security.userdetails.ExtendedUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/05/25
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public AdminUserController(HttpServletRequest request,
                               HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @PostMapping("users")
    public ResultEntity<User> createUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        ExtendedUserDetails userDetails = (ExtendedUserDetails) principal;
        System.out.println(userDetails);
        return ResultEntity.error();
    }

    @GetMapping("users")
    public ResultEntity<User> getUser() {
        return ResultEntity.error();
    }

    @GetMapping("users/{id}")
    public ResultEntity<User> getUserById() {
        return ResultEntity.error();
    }

    @PutMapping("users/{id}")
    public ResultEntity<User> updateUserById() {
        return ResultEntity.error();
    }

    @PatchMapping("users/{id}")
    public ResultEntity<User> patchUserById() {
        return ResultEntity.error();
    }

    @DeleteMapping("users/{id}")
    public ResultEntity<User> deleteUserById(String id) {
        return ResultEntity.error();
    }

    @DeleteMapping("users/{ids[]}")
    public ResultEntity<User> deleteUserByIds(List<String> ids) {
        return ResultEntity.error();
    }

    @PostMapping("users/{id}/roles")
    public ResultEntity<User> assignRoles(List<String> roles) {
        return ResultEntity.error();
    }

    @GetMapping("users/{id}/sessions")
    public ResultEntity<User> getSessionsById() {
        return ResultEntity.error();
    }
}
