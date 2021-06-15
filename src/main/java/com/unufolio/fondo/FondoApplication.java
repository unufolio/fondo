package com.unufolio.fondo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class FondoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FondoApplication.class, args);
    }

}
