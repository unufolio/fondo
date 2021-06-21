package com.unufolio.fondo.security.handler;

import com.unufolio.fondo.common.ResultEntity;
import com.unufolio.fondo.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.unufolio.fondo.common.enums.ResultCodeEnum.UNAUTHORIZED;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/03/28
 */
@Service
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        Response.json(httpServletResponse, HttpStatus.UNAUTHORIZED.value(), ResultEntity.error(UNAUTHORIZED));
    }
}
