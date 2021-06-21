package com.unufolio.fondo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/03/28
 */
@Configuration
@ConfigurationProperties(prefix = "fondo.security", ignoreInvalidFields = false, ignoreUnknownFields = true)
public class FondoSecurityProperties {
    private List<String> ignoreUris;

    public List<String> getIgnoreUris() {
        return ignoreUris;
    }

    public void setIgnoreUris(List<String> ignoreUris) {
        this.ignoreUris = ignoreUris;
    }
}