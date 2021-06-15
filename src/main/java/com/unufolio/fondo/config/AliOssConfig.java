package com.unufolio.fondo.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/03/28
 */
@Configuration
@ConfigurationProperties(prefix = "fondo.ali.oss", ignoreInvalidFields = false, ignoreUnknownFields = true)
public class AliOssConfig {

    private String endpoint;
    private String accessKeyId;
    private String secretAccessKey;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    @Bean
    public OSS oss() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, secretAccessKey);
    }
}
