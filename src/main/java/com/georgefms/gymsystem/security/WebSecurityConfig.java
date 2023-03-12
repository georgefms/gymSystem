package com.georgefms.gymsystem.security;

import com.georgefms.gymsystem.services.SecurityDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    //     auth.inMemoryAuthentication()
    //             .withUser("develop")
    //             .password("{noop}dev123")
    //             .roles("USERS")
    //             .and()
    //             .withUser("admin")
    //             .password("{noop}senhasegura")
    //             .roles("ADMIN");
    // }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/users").hasAnyRole("USERS", "ADMIN")
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .antMatchers("/any").hasAnyRole("USERS")
                .anyRequest().authenticated().and().httpBasic();
    }

    @Autowired
    private SecurityDatabaseService securityDatabaseService;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityDatabaseService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}