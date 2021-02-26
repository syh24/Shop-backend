package com.shop.mew.config.auth;

import com.shop.mew.domain.user.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/", "/css/**",
                        "/images/**", "/js/**", "/h2-console/**", "/api/v1/category").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/user", "/api/v1/authentication").permitAll()
                .antMatchers("/api/v1/profile/**", "/api/v1/user/**").permitAll()//hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
