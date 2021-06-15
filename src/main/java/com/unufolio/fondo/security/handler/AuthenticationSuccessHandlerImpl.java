package com.unufolio.fondo.security.handler;

import com.unufolio.fondo.common.ResultEntity;
import com.unufolio.fondo.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        ResultEntity<Object> resultEntity = ResultEntity.success();
        Response.json(httpServletResponse, HttpStatus.OK.value(), resultEntity);
    }
}
