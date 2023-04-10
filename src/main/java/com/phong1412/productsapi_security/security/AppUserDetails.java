package com.phong1412.productsapi_security.security;

import com.phong1412.productsapi_security.entities.User;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Transactional
public class AppUserDetails implements UserDetails {

    private User user;
    private String userAccount;
    private String userName;
    private String password;
    private List<GrantedAuthority> authorities; // List of permissions of a user

    // method that creates a new UserDetails that is assigned a value by the user logged into the app.
    public AppUserDetails(User user) {
        this.userAccount = user.getUseraccount();
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.user = user;
        this.authorities = Arrays.stream(
                        user.getRole()
                                .replaceAll("\\s", "")
                                .split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    // Returns a list of GrantedAuthority objects representing the permissions granted to the user
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
