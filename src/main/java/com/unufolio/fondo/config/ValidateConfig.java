package com.unufolio.fondo.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/03/28
 */
@Configuration
public class ValidateConfig {

    @Bean
    public Validator validator() {
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                // 开启快速校验--默认校验所有参数，false校验全部
                .addProperty("hibernate.validator.fail_fast", "false")
                .buildValidatorFactory().getValidator();
    }
}
