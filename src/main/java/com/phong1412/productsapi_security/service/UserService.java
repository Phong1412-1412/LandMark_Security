package com.phong1412.productsapi_security.service;

import com.phong1412.productsapi_security.Dto.UserRecord;
import com.phong1412.productsapi_security.entities.User;
import com.phong1412.productsapi_security.exception.BadException;
import com.phong1412.productsapi_security.exception.NotFoundException;
import com.phong1412.productsapi_security.iservice.IUserService;
import com.phong1412.productsapi_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserRecord> getAllUsers() {
        return userRepository.findAll().stream().map(user -> new UserRecord(
                user.getId(), user.getUsername(), user.getEmail(), user.getRole()
        )).toList();
    }

    @Override
    public User createUser(User newuser) {
        Optional<User> user = userRepository.findUsersByUsername(newuser.getUsername());
        if (!user.isPresent()) {
            newuser.setId(UUID.randomUUID().toString());
            newuser.setPassword(passwordEncoder.encode(newuser.getPassword()));
            userRepository.save(newuser);
            return newuser;
        }
        throw new BadException("User already exists in the database!");
    }

    @Override
    public User findUserById(String id) {
        if (userRepository.findUsersById(id).isPresent()) {
            return userRepository.findUsersById(id).get();
        }
        throw new NotFoundException("Can't find user with id: " + id);
    }

    @Override
    public User findUserByName(String name) {
        if (userRepository.findUsersByUsername(name).isPresent()) {
            return userRepository.findUsersByUsername(name).get();
        }
        throw new NotFoundException("Can't find user with name: " + name);
    }

    @Override
    public User UpdateUserById(User user) {
        if (userRepository.findUsersById(user.getId()).isPresent()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        throw new BadException("Can't find user with id " + user.getId() + " to update");
    }

    @Override
    public void deleteuser(String id) {
        if (userRepository.findUsersById(id).isPresent()) {
            userRepository.delete(userRepository.findUsersById(id).get());
        }
        throw new BadException("The object to be deleted could not be found: " + id);
    }

    public String getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println("Authentication Is: " + authentication);
            return authentication.toString();
        } else {
            System.out.println("Authentication Is NULL");
            return "Authentication Is NULL";
        }
    }

    public List<User> getAllUsersDetails() {
        return userRepository.findAll().stream().map(user -> new User(
                user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getRole()
        )).toList();
    }

}
