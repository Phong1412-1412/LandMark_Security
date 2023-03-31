package com.phong1412.productsapi_security.controller;

import com.phong1412.productsapi_security.Dto.UserRecord;
import com.phong1412.productsapi_security.entities.User;
import com.phong1412.productsapi_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserRecord>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
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
    public ResponseEntity<User> addUser(@RequestBody User user ) {
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.UpdateUserById(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id ) {
        userService.deleteuser(id);
        return ResponseEntity.ok().body("delete user successfully");
    }
}
