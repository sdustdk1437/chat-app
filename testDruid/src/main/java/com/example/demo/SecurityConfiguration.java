package com.example.demo;

import com.sun.deploy.config.SecuritySettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecuritySettings securitySettings;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler()).
                and().authorizeRequests()
        .antMatchers("/images","checkcode","/scripts/**","/styles/**").permitAll()
        .antMatchers();
    }
}
