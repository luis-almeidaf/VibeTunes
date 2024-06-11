package com.luis.VibeTunes.controller;

import com.luis.VibeTunes.dto.CreateUserDto;
import com.luis.VibeTunes.model.User;
import com.luis.VibeTunes.model.UserRole;
import com.luis.VibeTunes.repository.UserRepository;
import com.luis.VibeTunes.repository.UserRoleRepository;
import com.luis.VibeTunes.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<User> newUser(@RequestBody CreateUserDto dto) {
        var basicRole = userRoleRepository.findByName(UserRole.values.BASIC.name());
        var userFromDB =userRepository.findUserByUsername(dto.username());
        if (userFromDB.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setEmail(dto.email());
        user.setRoles(basicRole!= null? Set.of(basicRole): Set.of());

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<Optional<User>> findUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findUserByUsername(username);
        return ResponseEntity.ok().body(user);
    }

}
