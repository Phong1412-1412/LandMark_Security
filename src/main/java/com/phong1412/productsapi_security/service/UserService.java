package com.phong1412.productsapi_security.service;

import com.phong1412.productsapi_security.Dto.UserRecord;
import com.phong1412.productsapi_security.Dto.UserUpdate;
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
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserRecord> getAllUsers() {
        return userRepository.findAll().stream().map(user -> new UserRecord(
                user.getId(), user.getUseraccount(), user.getUsername(), user.getEmail(), user.getRole()
        )).toList();
    }

    @Override
    public User createUser(User newuser) {
        User user = userRepository.findUsersByUsername(newuser.getUsername()).orElse(null);
        if (user == null && userRepository.findUserByUseraccount(newuser.getUseraccount()).isEmpty()) {
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

    public User findUserByAccount(String account) {
        if (userRepository.findUserByUseraccount(account).isPresent()) {
            return userRepository.findUserByUseraccount(account).get();
        }
        throw new NotFoundException("Can't find user with account name: " + account);
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
    public User updateUserByName(UserUpdate userUpdate) {
        User user = userRepository.findUserByUseraccount(userUpdate.useraccount()).orElse(null);
        if (user != null) {
            user.setUsername(userUpdate.username());
            user.setEmail(userUpdate.email());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        throw new BadException("Can't find user with name " + userUpdate.username() + " to update");
    }

    @Override
    public void deleteuser(String id) {
        if (userRepository.findUsersById(id).isPresent()) {
            userRepository.delete(userRepository.findUsersById(id).get());
            return;
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
                user.getId(), user.getUseraccount(), user.getUsername(), user.getPassword(), user.getEmail(), user.getRole()
        )).toList();
    }

}
