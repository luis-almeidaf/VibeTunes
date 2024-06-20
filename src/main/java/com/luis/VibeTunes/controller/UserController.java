package com.luis.VibeTunes.controller;

import com.luis.VibeTunes.dto.FindUserDto;
import com.luis.VibeTunes.dto.UpdateUserDto;
import com.luis.VibeTunes.model.User;
import com.luis.VibeTunes.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/username/{username}")
    public ResponseEntity<FindUserDto> findUserByUsername(@PathVariable String username) {
        FindUserDto findUserDto = userService.findUserByUsername(username);
        if (findUserDto != null){
            return ResponseEntity.ok().body(findUserDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "id/{id}")
    public ResponseEntity<?> updateUser (@PathVariable Long id, @RequestBody UpdateUserDto updateUser) throws Exception {
        userService.updateUser(id, updateUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    public ResponseEntity<List<User>> listUsers() {
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @GetMapping(value = "id/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) throws Exception {
        User user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @DeleteMapping(value = "id/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) throws Exception {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
