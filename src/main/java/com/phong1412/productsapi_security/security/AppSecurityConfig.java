package com.phong1412.productsapi_security.security;

import com.phong1412.productsapi_security.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class AppSecurityConfig {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    private CustomBasicAuthenticationFilter customBasicAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final String[] UN_SECURED_URLs = {
            "/api/v1/provinces/all",
            "/api/v1/provinces/search/**",
            "/api/v1/famousPlace/details/**",
            "/api/v1/famousPlace/all",
            "/api/v1/famousPlace/search/**",
            "/api/v1/authenticate/**"
    };

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(UN_SECURED_URLs).permitAll()
                .requestMatchers("/api/v1/users/profile/**")
                .hasAuthority("ROLE_USER")
                .requestMatchers("/api/v1/users/**", "/api/v1/famousPlace/**", "/api/v1/provinces/**")
                .hasAuthority("ROLE_ADMIN")
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(customBasicAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    //After passing filterChain an Authentication Object will be created.
    //Sau khi Authentication Object được tạo ra thì filter sẽ gọi đến AuthenticationManager để xác thực người dùng
    //ProviderManager là một Iterface của AuthenticationManager, Và các phương thức xác thưc được lấy từ nó.


    // Tìm kiếm user thông qua loadUserByUserName() từ database

    @Bean
    public AuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

}
