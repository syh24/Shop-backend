package com.shop.mew.config.auth;

import com.shop.mew.domain.user.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
                        "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v?/user/**").permitAll()
                .antMatchers( HttpMethod.GET,"/api/v?/user").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST,"/api/v?/item/add").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.PUT,"/api/v?/item/**").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.DELETE,"/api/v?/item/**").hasRole(Role.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin().disable();
    }

    @Override
    public void configure (WebSecurity web) throws Exception{
        web
                .ignoring()
                .antMatchers("/h2-console/**",
                        "/favicon.ico", "/swagger-ui.html/**", "/swagger-resources/**",
                        "/v?/api-docs", "/webjars/**", "/static/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
