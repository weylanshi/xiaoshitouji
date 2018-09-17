package com.weylan.blog.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * jwt 配置信息
 *
 * @author shiweinan
 */
@Configuration
@ConfigurationProperties(prefix = "weylan.blog.jwt")
@Getter
@Setter
public class JwtSetting {

    private Integer tokenExpirationTime;
    private Integer refreshExpirationTime;
    private String tokenIssuer;
    private String tokenSigningKey;


}
