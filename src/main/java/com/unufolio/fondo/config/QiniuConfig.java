package com.unufolio.fondo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/03/28
 */
@Configuration
@ConfigurationProperties(prefix = "fondo.qiniu", ignoreInvalidFields = false, ignoreUnknownFields = true)
public class QiniuConfig {

    private String accessKey;
    private String secretKey;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
