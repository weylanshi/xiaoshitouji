package com.weylan.blog.config.value;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class GlobalValueSetting {

    @Value("${mini-program-app-id}")
    private String miniProgramAppId;

    @Value("${mini-program-app-secret}")
    private String miniProgramAppSecret;
}
