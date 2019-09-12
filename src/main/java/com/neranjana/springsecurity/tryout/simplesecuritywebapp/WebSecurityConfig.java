package com.neranjana.springsecurity.tryout.simplesecuritywebapp;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/greetings/morning").permitAll()
                .antMatchers("/greetings/evening").hasRole("EMPLOYEE")
                .antMatchers("/greetings/night").access("hasRole('EMPLOYEE') and hasRole('MANAGER')")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }


    @Bean
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withDefaultPasswordEncoder().username("john").password("johnpassword").roles("EMPLOYEE").build());
        userDetailsManager.createUser(User.withDefaultPasswordEncoder().username("sam").password("sampassword").roles("EMPLOYEE", "MANAGER").build());
        return userDetailsManager;
    }
}
