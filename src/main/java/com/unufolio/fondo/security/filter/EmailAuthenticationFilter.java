package com.unufolio.fondo.security.filter;

import com.unufolio.fondo.security.authentication.EmailAuthenticationToken;
import com.unufolio.fondo.security.exception.MethodNotSupportedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/03/28
 */
public class EmailAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final static Logger logger = LoggerFactory.getLogger(EmailAuthenticationFilter.class);
    public static final String SPRING_SECURITY_FORM_EMAIL_KEY = "email";
    public static final String SPRING_SECURITY_FORM_VERIFICATION_CODE_KEY = "code";
    protected final String emailParameter = "email";
    protected final String codeParameter = "code";
    private boolean postOnly = true;

    public EmailAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login/email", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !HttpMethod.POST.name().equals(request.getMethod())) {
            throw new MethodNotSupportedException(
                    "Authentication method not supported: " + request.getMethod());
        } else {
            String email = this.obtainEmail(request);
            String code = this.obtainCode(request);
            if (email == null) {
                email = "";
            }
            if (code == null) {
                code = "";
            }
            EmailAuthenticationToken authRequest = new EmailAuthenticationToken(email, "", code);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    @Nullable
    protected String obtainEmail(HttpServletRequest request) {
        return request.getParameter(this.emailParameter);
    }

    @Nullable
    protected String obtainCode(HttpServletRequest request) {
        return request.getParameter(this.codeParameter);
    }

    protected void setDetails(HttpServletRequest request, EmailAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getEmailParameter() {
        return this.emailParameter;
    }

    public final String getCodeParameter() {
        return this.codeParameter;
    }
}
