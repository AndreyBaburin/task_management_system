package com.andrey_baburin.security;

import com.andrey_baburin.services.UserProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 private final  UserProcessor userProcessor;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userProcessor);
    }

    @Bean
    PasswordEncoder getPasswordEncoder () {
        return NoOpPasswordEncoder.getInstance();
    }
}
