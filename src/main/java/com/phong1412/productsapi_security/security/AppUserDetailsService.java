package com.phong1412.productsapi_security.security;

import com.phong1412.productsapi_security.entities.User;
import com.phong1412.productsapi_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    //phương thức loadUserByUsername() để truy xuất thông tin người dùng dựa trên tên đăng nhập (username).
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUsersByUsername(username)
                .map(AppUserDetails::new)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Không tìm thấy user có name: "+username)
                ); //trả về một đối tượng UserDetails, đại diện cho thông tin người dùng
    }
}
