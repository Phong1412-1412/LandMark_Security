package com.phong1412.productsapi_security.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authenticate")
@RequiredArgsConstructor
public class JwtController {
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public String getTokenAuthenticatedUser(@RequestBody JwtAuthenticationRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.getGeneratedToken(authRequest.getUserName());
        }
        throw new UsernameNotFoundException("Invalid User Credentials!!!");
    }
}
