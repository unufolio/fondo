package com.unufolio.fondo.security.filter;

import com.unufolio.fondo.security.authentication.PhoneAuthenticationToken;
import com.unufolio.fondo.security.exception.MethodNotSupportedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/03/28
 */
public class PhoneAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final static Logger logger = LoggerFactory.getLogger(PhoneAuthenticationFilter.class);
    public static final String SPRING_SECURITY_FORM_PHONE_KEY = "phone";
    public static final String SPRING_SECURITY_FORM_CODE_KEY = "code";
    protected final String phoneParameter = "phone";
    protected final String codeParameter = "code";
    private boolean postOnly = true;

    public PhoneAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login/phone", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !HttpMethod.POST.name().equals(request.getMethod())) {
            throw new MethodNotSupportedException(
                    "Authentication method not supported: " + request.getMethod());
        } else {
            String phone = this.obtainPhone(request);
            String code = this.obtainCode(request);
            if (phone == null) {
                phone = "";
            }
            if (code == null) {
                code = "";
            }
            PhoneAuthenticationToken authRequest = new PhoneAuthenticationToken(phone, "", code);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    @Nullable
    protected String obtainPhone(HttpServletRequest request) {
        return request.getParameter(this.phoneParameter);
    }

    @Nullable
    protected String obtainCode(HttpServletRequest request) {
        return request.getParameter(this.codeParameter);
    }

    protected void setDetails(HttpServletRequest request, PhoneAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getPhoneParameter() {
        return this.phoneParameter;
    }

    public final String getCodeParameter() {
        return this.codeParameter;
    }

}
