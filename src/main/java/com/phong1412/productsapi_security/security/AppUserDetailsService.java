package com.phong1412.productsapi_security.security;

import com.phong1412.productsapi_security.entities.User;
import com.phong1412.productsapi_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    //phương thức loadUserByUsername() để truy xuất thông tin người dùng dựa trên tên đăng nhập (username).
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUsersByUsername(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new AppUserDetails(user);
        //trả về một đối tượng UserDetails, đại diện cho thông tin người dùng
    }

}
