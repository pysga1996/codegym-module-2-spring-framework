package com.codegym.blog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationSuccessHandler customSuccessHandler;

    @Autowired
    public SecurityConfiguration(
        AuthenticationSuccessHandler customSuccessHandler) {
        this.customSuccessHandler = customSuccessHandler;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("root123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN", "DBA");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/user/**", "/home/").access("hasRole('USER')")
            .antMatchers("/admin/**", "/home/").access("hasRole('ADMIN')")
            .antMatchers("/dba/**", "/home/").access("hasRole('ADMIN') and hasRole('DBA')")
            .and().formLogin().successHandler(customSuccessHandler)
            .usernameParameter("ssoId").passwordParameter("password")
            .and().csrf()
            .and().exceptionHandling().accessDeniedPage("/access-denied")
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        ;
    }
}