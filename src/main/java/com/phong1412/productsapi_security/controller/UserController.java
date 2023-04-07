package com.phong1412.productsapi_security.controller;

import com.phong1412.productsapi_security.Dto.UserRecord;
import com.phong1412.productsapi_security.entities.User;
import com.phong1412.productsapi_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.UpdateUserById(user));
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok().body(userService.findUserByName(userName));
    }

    @PutMapping("/profile/update")
    public ResponseEntity<User> updateUserAuthenticate(@RequestBody UserRecord user) {
        User newUser = userService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        newUser.setUsername(user.name());
        newUser.setPassword(user.email());
        return ResponseEntity.status(HttpStatus.valueOf(201)).body(userService.UpdateUserById(newUser));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteuser(id);
        return ResponseEntity.ok().body("delete user successfully");
    }

    @GetMapping("/index")
    public String getAllUsersIndexPage(Model model) {
        List<User> users = userService.getAllUsersDetails();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/details/{id}")
    public String userDetails(Model model, @PathVariable String id) {
        User users = userService.findUserById(id);
        model.addAttribute("users", users);
        return "userDetails";
    }

}
