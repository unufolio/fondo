package com.unufolio.fondo.security.handler;

import com.unufolio.fondo.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/03/28
 */
@Service
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
        Response.json(httpServletResponse, HttpStatus.FORBIDDEN.value(), "forbidden");
        System.out.println("forbidden");
    }
}
