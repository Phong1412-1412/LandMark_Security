package com.phong1412.productsapi_security.security;

import jakarta.security.auth.message.config.AuthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class AppSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final String[] UN_SECURED_URLs = {
            "api/v1/users/all",
            //"api/v1/users/authentication",
            "api/v1/users/search/**",
            "api/v1/famousplace/all",
            "api/v1/famousplace/search/**"
    };
    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests(
                        (author) -> author.requestMatchers(UN_SECURED_URLs).permitAll()
                                .requestMatchers("/api/v1/users/**", "/api/v1/famousplace/**").hasAuthority("ROLE_ADMIN").anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    //Sau khi thông qua filterChain một đối tượng Authentication Object sẽ được tạo ra.
    //Sau khi Authentication Object được tạo ra thì filter sẽ gọi đến AuthenticationManager để xác thực người dùng
    //ProviderManager là một Iterface của AuthenticationManager, Và các phương thức xác thưc được lấy từ nó.
    @Autowired
    private AppUserDetailsService appUserDetailsService;
    // Tìm kiếm user thông qua loadUserByUserName() từ database
    @Bean
    //
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        //được sử dụng để mã hóa mật khẩu người dùng trước khi so sánh với mật khẩu đã lưu trong cơ sở dữ liệu.
        return provider;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(AuthenticationManagerBuilder aumanager) throws Exception {
        aumanager.authenticationProvider(daoAuthenticationProvider());
        //để đăng ký DaoAuthenticationProvider đã được định nghĩa trong phương thức daoAuthenticationProvider().
        return daoAuthenticationProvider();
        //Bạn cũng cần trả về đối tượng DaoAuthenticationProvider để Spring có thể quản lý đối tượng này.
    }

}
