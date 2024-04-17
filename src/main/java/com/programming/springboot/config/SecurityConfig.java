package com.programming.springboot.config;

import com.programming.springboot.filter.JwtAuthenticationFilter;
import com.programming.springboot.service.UserService;
import com.programming.springboot.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req->req.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers(Constant.GET_ALL_EMPLOYEE_INFO_PATH).permitAll()
                                .requestMatchers(Constant.DELETE_EMPLOYEE_INFO_PATH + "/**").hasAuthority("ADMIN")
                                .requestMatchers(Constant.PATCH_EMPLOYEE_INFO_PATH + "/**").hasAuthority("ADMIN")
                                .requestMatchers(Constant.ADD_EMPLOYEE_INFO_PATH).hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(Constant.UPDATE_EMPLOYEE_INFO_PATH + "/**").hasAnyAuthority( "ADMIN", "USER")
                                .anyRequest().authenticated()
                )
                .userDetailsService(userService)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
