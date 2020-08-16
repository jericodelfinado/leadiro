package com.leadiro.starter.config;

import com.leadiro.starter.controller.auth.AuthTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "auth")
public class AuthConfigProperties {

    AuthTypeEnum type;

    List<AuthUser> users;

    @Getter
    @Setter
    public static class AuthUser {
        String name;
        String password;
        List<String> roles;
    }
}
