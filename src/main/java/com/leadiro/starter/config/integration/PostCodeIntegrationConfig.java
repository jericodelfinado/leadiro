package com.leadiro.starter.config.integration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("leadiro.integration.postcode")
public class PostCodeIntegrationConfig {

    private String basePath;
}
