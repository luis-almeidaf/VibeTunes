package com.luis.VibeTunes.controller;

import com.luis.VibeTunes.model.User;
import com.luis.VibeTunes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> listUsers() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }


    @GetMapping(value = "/users/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
        var user = userService.findUserByUsername(username);
        return ResponseEntity.ok().body(user);
    }

}
