package com.leadiro.starter.config;

import com.leadiro.starter.common.converter.PostCodeResponseConverter;
import com.leadiro.starter.common.converter.ValidationResultConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ValidationResultConverter());
        registry.addConverter(new PostCodeResponseConverter());
    }
}
