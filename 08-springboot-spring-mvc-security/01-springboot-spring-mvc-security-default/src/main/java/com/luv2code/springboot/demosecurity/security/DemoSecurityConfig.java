package com.luv2code.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {
    //add support for JDBC and no more hard coded users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        //tell Spring Security to use JDBC authentication with our data source
        return new JdbcUserDetailsManager(dataSource);
    }

 /*  just use context from sql
  @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }

  */


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //restrict access based on http request
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated()//any request to the app must be authenticated
        )
                .formLogin(form ->//we are customizing the form login process
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")//login form should POST data to this URL for processing
                                .permitAll()//allow everyone to see login page. No need to be logged in
                )
                .logout(logout -> logout.permitAll()//adds logout support
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied"));
        return http.build();

    }
}
