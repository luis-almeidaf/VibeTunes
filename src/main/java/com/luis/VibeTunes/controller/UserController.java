package com.luis.VibeTunes.controller;

import com.luis.VibeTunes.dto.UserResponseDto;
import com.luis.VibeTunes.model.User;
import com.luis.VibeTunes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    public ResponseEntity<List<User>> listUsers() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/username/{username}")
    public ResponseEntity<UserResponseDto> findUserByUsername(@PathVariable String username) {
        UserResponseDto userResponseDto = userService.findUserByUsername(username);
        if (userResponseDto != null){
            return ResponseEntity.ok().body(userResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @GetMapping(value = "id/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) throws Exception {
        var user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

}
