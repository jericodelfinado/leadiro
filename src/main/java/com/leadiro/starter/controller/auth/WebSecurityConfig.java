package com.leadiro.starter.controller.auth;

import com.leadiro.starter.config.AuthConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtBearerTokenAuthenticationFilter jwtFilter;

    @Autowired
    private AuthConfigProperties authConfigProperties;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authManager = auth.inMemoryAuthentication();
        authConfigProperties.getUsers().forEach(user ->
                authManager.withUser(user.getName())
                        .password(passwordEncoder().encode(user.getPassword()))
                        .authorities(user.getRoles().toArray(new String[0]))
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.debug("Configuring web security");
        http
                //Disable CSRF
                .csrf().disable()
                //Disable sessions
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //Configure URLs
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll() //Allow all CORS OPTIONS request
                .antMatchers("/public/**").permitAll() //Allow access to URLS marked as public
                .antMatchers("/ping", "health", "/starter/health").permitAll() //Allow access to the health pings
                .antMatchers("/index.html", "/img/*.png", "/js/*.js", "/css/*.css")
                .permitAll() //Allow access static Vue resources
                .anyRequest().authenticated(); //Force auth for everything else

        switch (authConfigProperties.getType()) {
            case BASIC:
                configBasicAuth(http);
                break;
            case JWT:
                configJwt(http);
                break;
        }
    }

    private void configJwt(HttpSecurity http) {
        http.addFilterAfter(jwtFilter, BasicAuthenticationFilter.class);
    }

    private void configBasicAuth(HttpSecurity http) throws Exception {
        http.httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}