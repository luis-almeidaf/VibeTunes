package com.luis.VibeTunes.controller;

import com.luis.VibeTunes.model.User;
import com.luis.VibeTunes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> listUsers() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/username/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
        var user = userService.findUserByUsername(username);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "id/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) throws Exception {
        var user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

}
