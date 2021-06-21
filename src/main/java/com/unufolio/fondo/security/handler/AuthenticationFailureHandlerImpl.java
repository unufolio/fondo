package com.unufolio.fondo.security.handler;

import com.unufolio.fondo.common.ResultEntity;
import com.unufolio.fondo.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.unufolio.fondo.common.enums.ResultCodeEnum.LOGIN_FAILED;
import static com.unufolio.fondo.common.enums.ResultCodeEnum.UNAUTHORIZED;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/03/28
 */
@Service
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        e.printStackTrace();
        Response.json(httpServletResponse, HttpStatus.UNAUTHORIZED.value(), ResultEntity.error(LOGIN_FAILED));
    }
}
