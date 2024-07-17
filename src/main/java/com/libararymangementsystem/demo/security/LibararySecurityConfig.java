package com.libararymangementsystem.demo.security;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class LibararySecurityConfig {

    //ADD MODEL MAPPER BEAN
    //@Bean
    //public ModelMapper modelMapper(){
        //return new ModelMapper();
    //}


    //adding support for JDBC authentication
    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    //restricting access to url based on roles
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         http.authorizeHttpRequests(configurer->
                 configurer
                         .requestMatchers(HttpMethod.GET,"/api/authors/**","/api/books/**","/api/languages/**","/api/barrowers/**","/api/booksAuthors/**","/api/AuthorBooks/**")
                         .hasRole("ADMIN")
                         .requestMatchers(HttpMethod.POST,"/api/authors/**","/api/books","/api/languages/**","/api/barrowers","/api/borrowers/**","/api/borrowerss","/api/exborrowers/**","/api/borrower/**","/api/borrowBook/**")
                         .hasRole("AUTHOR")
                         .requestMatchers(HttpMethod.PUT,"/api/authors","/api/books","/api/languages","/api/borrowers")
                         .hasRole("ADMIN")
                         .requestMatchers(HttpMethod.DELETE,"/api/authors/**","/api/books/**","/api/languages/**","/api/barrowers/**")
                         .hasRole("ADMIN")


                 );

http.httpBasic();
http.csrf().disable();
return http.build();

    }
}
