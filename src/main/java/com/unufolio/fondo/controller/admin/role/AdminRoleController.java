package com.unufolio.fondo.controller.admin.role;

import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/06/11
 */
@RestController
@RequestMapping("/admin")
public class AdminRoleController {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final FindByIndexNameSessionRepository<? extends Session> sessionRepository;

    public AdminRoleController(HttpServletRequest request,
                               HttpServletResponse response,
                               FindByIndexNameSessionRepository<? extends Session> sessionRepository) {
        this.request = request;
        this.response = response;
        this.sessionRepository = sessionRepository;
    }
}
