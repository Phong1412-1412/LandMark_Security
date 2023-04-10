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

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = userRepository.findUserByUseraccount(account).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(account);
        }
        return new AppUserDetails(user);
        //returns a UserDetails object, representing user information
    }

}
