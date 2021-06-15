package com.unufolio.fondo.security.config;

import com.unufolio.fondo.security.authentication.provider.EmailAuthenticationProvider;
import com.unufolio.fondo.security.authentication.provider.PhoneAuthenticationProvider;
import com.unufolio.fondo.security.filter.*;
import com.unufolio.fondo.security.userdetails.IExtendedUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.web.cors.CorsUtils;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/03/27
 */
@Configuration
public class MultiWebSecurityConfig {

    @Configuration
    @Order(Ordered.LOWEST_PRECEDENCE - 100)
    @EnableRedisHttpSession
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    protected static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private final PasswordEncoder passwordEncoder;
        private final IExtendedUserDetailsService userDetailsService;
        private final AccessDeniedHandler accessDeniedHandler;
        private final AuthenticationFailureHandler authenticationFailureHandler;
        private final AuthenticationSuccessHandler authenticationSuccessHandler;
        private final LogoutSuccessHandler logoutSuccessHandler;
        private final AuthenticationEntryPoint authenticationEntryPoint;
        private final CsrfTokenRepository csrfTokenRepository;
        private final FindByIndexNameSessionRepository<? extends Session> sessionRepository;
        private final SessionRegistry sessionRegistry;
        private final SessionAuthenticationStrategy sessionAuthenticationStrategy;

        public WebSecurityConfig(IExtendedUserDetailsService userDetailsService,
                                 AccessDeniedHandler accessDeniedHandler,
                                 AuthenticationFailureHandler authenticationFailureHandler,
                                 AuthenticationSuccessHandler authenticationSuccessHandler,
                                 PasswordEncoder passwordEncoder,
                                 LogoutSuccessHandler logoutSuccessHandler,
                                 AuthenticationEntryPoint authenticationEntryPoint,
                                 CsrfTokenRepository csrfTokenRepository,
                                 FindByIndexNameSessionRepository<? extends Session> sessionRepository) {
            this.userDetailsService = userDetailsService;
            this.accessDeniedHandler = accessDeniedHandler;
            this.authenticationFailureHandler = authenticationFailureHandler;
            this.authenticationSuccessHandler = authenticationSuccessHandler;
            this.passwordEncoder = passwordEncoder;
            this.logoutSuccessHandler = logoutSuccessHandler;
            this.authenticationEntryPoint = authenticationEntryPoint;
            this.csrfTokenRepository = csrfTokenRepository;
            this.sessionRepository = sessionRepository;
            this.sessionRegistry = springSessionBackedSessionRegistry();
            this.sessionAuthenticationStrategy = sessionAuthenticationStrategy(sessionRegistry);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // csrf
            http.csrf().csrfTokenRepository(csrfTokenRepository);
            http.csrf().disable();

            // session management
            // default migrateSession
            http.sessionManagement()
                    .sessionFixation()
                    .migrateSession()
                    // user max session num, -1 no limit
                    .maximumSessions(1)
                    .sessionRegistry(sessionRegistry)
                    // 达到 max 会话，true 阻止登录， false 踢掉之前的会话
                    .maxSessionsPreventsLogin(false);

            http.userDetailsService(userDetailsService);

            // grant all preflight request
            http.authorizeRequests()
                    .requestMatchers(CorsUtils::isPreFlightRequest)
                    .permitAll();

//            http.authorizeRequests()
//                    .antMatchers("remember-me")
//                    .authenticated();

            http.rememberMe();

            http.authorizeRequests()
                    .anyRequest()
                    .authenticated();

//             http.oauth2Login();

            // access denied handler
            http.exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler);

            // json username password authentication filter
            http.addFilterAt(
                    jsonUsernamePasswordAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);

            // json authentication filter
            http.addFilterAt(
                    jsonEmailAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);

            // email authentication filter
            http.addFilterAt(
                    emailAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);

            // json phone authentication filter
            http.addFilterAt(
                    jsonPhoneAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);

            // phone authentication filter
            http.addFilterAt(
                    phoneAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);

            http.formLogin().permitAll()
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler);

            http.exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint);

            http.logout()
                    .logoutSuccessHandler(logoutSuccessHandler)
                    .invalidateHttpSession(true);
        }

        public DaoAuthenticationProvider daoAuthenticationProvider() {
            var provider = new DaoAuthenticationProvider();
            // passwordEncoder
            provider.setPasswordEncoder(passwordEncoder);
            // username UserDetailsService
            provider.setUserDetailsService(userDetailsService);
            // setHideUserNotFoundExceptions
            provider.setHideUserNotFoundExceptions(false);
            return provider;
        }

        public EmailAuthenticationProvider emailAuthenticationProvider() {
            var provider = new EmailAuthenticationProvider();
            // email UserDetailsService
            provider.setUserDetailsService(userDetailsService);
            // setHideUserNotFoundExceptions
            provider.setHideUserNotFoundExceptions(false);
            return provider;
        }

        public PhoneAuthenticationProvider phoneAuthenticationProvider() {
            var provider = new PhoneAuthenticationProvider();
            // username UserDetailsService
            provider.setUserDetailsService(userDetailsService);
            // setHideUserNotFoundExceptions
            provider.setHideUserNotFoundExceptions(false);
            return provider;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(daoAuthenticationProvider());
            auth.authenticationProvider(phoneAuthenticationProvider());
            auth.authenticationProvider(emailAuthenticationProvider());
        }

        public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() throws Exception {
            var filter = new JsonUsernamePasswordAuthenticationFilter();
            filter.setAuthenticationManager(authenticationManagerBean());
            filter.setFilterProcessesUrl("/login");
            filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
            filter.setAuthenticationFailureHandler(authenticationFailureHandler);
            filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
            return filter;
        }

        public JsonEmailAuthenticationFilter jsonEmailAuthenticationFilter() throws Exception {
            var filter = new JsonEmailAuthenticationFilter();
            filter.setAuthenticationManager(authenticationManagerBean());
            filter.setFilterProcessesUrl("/login/email");
            filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
            filter.setAuthenticationFailureHandler(authenticationFailureHandler);
            filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
            return filter;
        }

        public EmailAuthenticationFilter emailAuthenticationFilter() throws Exception {
            var filter = new EmailAuthenticationFilter();
            filter.setAuthenticationManager(authenticationManagerBean());
            filter.setFilterProcessesUrl("/login/email");
            filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
            filter.setAuthenticationFailureHandler(authenticationFailureHandler);
            filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
            return filter;
        }

        public JsonPhoneAuthenticationFilter jsonPhoneAuthenticationFilter() throws Exception {
            var filter = new JsonPhoneAuthenticationFilter();
            filter.setAuthenticationManager(authenticationManagerBean());
            filter.setFilterProcessesUrl("/login/phone");
            filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
            filter.setAuthenticationFailureHandler(authenticationFailureHandler);
            filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
            return filter;
        }

        public PhoneAuthenticationFilter phoneAuthenticationFilter() throws Exception {
            var filter = new PhoneAuthenticationFilter();
            filter.setAuthenticationManager(authenticationManagerBean());
            filter.setFilterProcessesUrl("/login/phone");
            filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
            filter.setAuthenticationFailureHandler(authenticationFailureHandler);
            filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
            return filter;
        }

        public SessionAuthenticationStrategy sessionAuthenticationStrategy(SessionRegistry sessionRegistry) {
            return new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
        }

        public SpringSessionBackedSessionRegistry<? extends Session> springSessionBackedSessionRegistry() {
            return new SpringSessionBackedSessionRegistry<>(sessionRepository);
        }
    }
}