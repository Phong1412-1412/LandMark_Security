package com.phong1412.productsapi_security.controller;

import com.phong1412.productsapi_security.Dto.UserRecord;
import com.phong1412.productsapi_security.Dto.UserUpdate;
import com.phong1412.productsapi_security.entities.User;
import com.phong1412.productsapi_security.security.jwt.JwtService;
import com.phong1412.productsapi_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserRecord>> getAllUsers() {
        List<UserRecord> users = userService.getAllUsers();
        return new ResponseEntity<List<UserRecord>>(users, HttpStatus.OK);
    }

    @GetMapping("/authentication")
    public ResponseEntity<String> getAuthentication() {
        return ResponseEntity.ok().body(userService.getAuthentication());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return ResponseEntity.ok().body(userService.findUserById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<User> getUserByName(@RequestParam String username) {
        return ResponseEntity.ok().body(userService.findUserByName(username));
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.UpdateUserById(user));
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        String userAccount = SecurityContextHolder.getContext().getAuthentication().getName();
        if (SecurityContextHolder.getContext().getAuthentication().getDetails() != null) {
            return ResponseEntity.ok().body(userService.findUserByName(userAccount));
        }
        return ResponseEntity.ok().body(userService.findUserByAccount(userAccount));
    }

    @PutMapping("/profile/update")
    public ResponseEntity<User> updateUserAuthenticate(@RequestBody UserUpdate userUpdate) {
        String nameAccount = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.status(HttpStatus.valueOf(201)).body(userService.updateUserByName(nameAccount, userUpdate));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteuser(id);
        return ResponseEntity.ok().body("delete user successfully");
    }

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

}
