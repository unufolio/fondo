package com.unufolio.fondo.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/03/28
 */
@Configuration
@ConfigurationProperties(prefix = "fondo.tencent.cos", ignoreInvalidFields = false, ignoreUnknownFields = true)
public class TencentCosConfig {

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

    @Bean
    public COSClient cosClient() {
        return new COSClient(cosCredentials(), clientConfig());
    }

    private COSCredentials cosCredentials() {
        return new BasicCOSCredentials(accessKey, secretKey);
    }

    private ClientConfig clientConfig() {
        return new ClientConfig();
    }
}
